package fr.onepoint.training;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AddEmployee extends Simulation {
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("http://localhost:8060")
		.silentResources()
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0");



	private final ScenarioBuilder scn = scenario("OrangeHRM")
		.during(Duration.ofMinutes(30)).on(
			pace(Duration.ofSeconds(2))
				.randomSwitch().on(
					percent(10.0).then (exec (OrangeHRM.vu_add_employee())),
					percent(90.0).then (exec (OrangeHRM.vu_search_employee()))
				));
	{
		setUp(
			scn.injectOpen(rampUsers(20).during(Duration.ofMinutes(20)))
		).protocols(httpProtocol);
	}



//	private final ScenarioBuilder scn = scenario("OrangeHRM Debug")
//		//.exec(OrangeHRM.vu_add_employee())
//		.exec(OrangeHRM.vu_search_employee());
//	{
//		setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
//	}


}
