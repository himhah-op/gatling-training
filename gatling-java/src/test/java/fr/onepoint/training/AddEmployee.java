package fr.onepoint.training;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import java.time.Duration;

public class AddEmployee extends Simulation {
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("http://localhost:8060")
		.silentResources()
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0");

  ScenarioBuilder scn = scenario("Users")
													.exec(OrangeHRM.login(),
																// OrangeHRM.add_employee (),
																OrangeHRM.search_employee ("Dupont"),
																//OrangeHRM.employee_details(),
																// OrangeHRM.employee_contact(),
														    OrangeHRM.delete_employee(),
																OrangeHRM.logout());

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
