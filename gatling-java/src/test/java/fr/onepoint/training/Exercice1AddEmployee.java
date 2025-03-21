package fr.onepoint.training;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Exercice1AddEmployee extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("http://localhost:8060")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"),
    Map.entry("Priority", "u=0, i"),
    Map.entry("Upgrade-Insecure-Requests", "1")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("If-None-Match", "\"BY94ts6Fi+1Yi8fPMZszFQOk+IVVK7mRggQqqOjvOzs=\""),
    Map.entry("contentType", "application/json")
  );
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"),
    Map.entry("Origin", "http://localhost:8060"),
    Map.entry("Priority", "u=0, i"),
    Map.entry("Upgrade-Insecure-Requests", "1")
  );
  
  private Map<CharSequence, String> headers_4 = Map.of("Origin", "http://localhost:8060");
  
  private Map<CharSequence, String> headers_5 = Map.ofEntries(
    Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
    Map.entry("Priority", "u=4, i")
  );
  
  private Map<CharSequence, String> headers_6 = Map.of("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
  
  private Map<CharSequence, String> headers_12 = Map.of("Accept", "application/json, text/plain, */*");
  
  private Map<CharSequence, String> headers_15 = Map.ofEntries(
    Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
    Map.entry("If-Modified-Since", "Fri, 19 Jul 2024 04:22:38 GMT"),
    Map.entry("If-None-Match", "\"eSWfz0aTMRL2qx3Xp+IS4qMVAXpmiSacwlj2vIk2pmo=\""),
    Map.entry("Priority", "u=5")
  );
  
  private Map<CharSequence, String> headers_26 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "http://localhost:8060")
  );
  
  private Map<CharSequence, String> headers_40 = Map.ofEntries(
    Map.entry("Accept", "application/json, text/plain, */*"),
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "http://localhost:8060")
  );
  
  private Map<CharSequence, String> headers_81 = Map.ofEntries(
    Map.entry("Accept", "application/json, text/plain, */*"),
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "http://localhost:8060"),
    Map.entry("Priority", "u=0")
  );

  ChainBuilder  login()  {
    return (
      exec(
      // HOME,
      http("request_0")
        .get("/")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1))
    )
    .pause(11)
      // LOGIN,
    .exec(http("request_2")
        .post("/web/index.php/auth/validate")
        .headers(headers_2)
        .formParam("_token", "2d.QamKI6jipajHkDwVGLVje5X5KW4gxZt4uGciWLWs4QE.d8a-T5yw9t-u6UVsLYACNNyqfiVRtNcflQB9G9blhTIx5r5xxNL0xYz2DA")
        .formParam("username", "Admin")
        .formParam("password", "Onepoint$2025")
        .resources(
          http("request_3")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_4")
            .post("/web/index.php/events/push")
            .headers(headers_4),
          http("request_5")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_5),
          http("request_6")
            .get("/web/index.php/api/v2/dashboard/employees/action-summary")
            .headers(headers_6),
          http("request_7")
            .get("/web/index.php/api/v2/dashboard/employees/subunit")
            .headers(headers_6),
          http("request_8")
            .get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=1&currentDate=2025-03-21&currentTime=22:02")
            .headers(headers_6),
          http("request_9")
            .get("/web/index.php/api/v2/dashboard/employees/leaves?date=2025-03-21")
            .headers(headers_6),
          http("request_10")
            .get("/web/index.php/api/v2/dashboard/employees/locations")
            .headers(headers_6),
          http("request_11")
            .get("/web/index.php/api/v2/dashboard/shortcuts")
            .headers(headers_6),
          http("request_12")
            .get("/web/index.php/api/v2/buzz/feed?limit=5&offset=0&sortOrder=DESC&sortField=share.createdAtUtc")
            .headers(headers_12)
        )
     )
    .pause(15)
    );
  }
  ChainBuilder  logout() {
    return (
      exec(http("request_83")
      .get("/web/index.php/auth/logout")
      .headers(headers_0)
      .resources(
        http("request_84")
          .get("/web/index.php/core/i18n/messages")
          .headers(headers_1)
        )
      )
    );
  }

  ChainBuilder  employee_actions () {
    // ADD_EMPLOYEE,
    return (
      exec(http("request_13")
        .get("/web/index.php/pim/viewPimModule")
        .headers(headers_0)
        .resources(
          http("request_14")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_15")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_16")
            .get("/web/index.php/api/v2/admin/employment-statuses?limit=0")
            .headers(headers_6),
          http("request_17")
            .get("/web/index.php/api/v2/admin/subunits")
            .headers(headers_6),
          http("request_18")
            .get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
            .headers(headers_6),
          http("request_19")
            .get("/web/index.php/api/v2/admin/job-titles?limit=0")
            .headers(headers_6)
        ))
        .pause(1)
     .exec(http("request_20")
        .get("/web/index.php/pim/addEmployee")
        .headers(headers_0)
        .resources(
          http("request_21")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_22")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_23")
            .get("/web/index.php/api/v2/pim/employees")
            .headers(headers_6)
        ))
     .pause(11)
     .exec(http("request_24")
        .get("/web/index.php/api/v2/core/validation/unique?value=456987&entityName=Employee&attributeName=employeeId")
        .headers(headers_12)
     )
        .pause(5)
        .exec(
      http("request_25")
        .get("/web/index.php/api/v2/core/validation/unique?value=456987&entityName=Employee&attributeName=employeeId")
        .headers(headers_12)
        .resources(
          http("request_26")
            .post("/web/index.php/api/v2/pim/employees")
            .headers(headers_26)
            .body(RawFileBody("fr/onepoint/training/addemployee/0026_request.json"))
        ))
        .pause(2)
        .exec(
      http("request_27")
        .get("/web/index.php/pim/viewPersonalDetails/empNumber/2")
        .headers(headers_0)
        .resources(
          http("request_28")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_29")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_30")
            .get("/web/index.php/pim/viewPhoto/empNumber/2")
            .headers(headers_5),
          http("request_31")
            .get("/web/index.php/api/v2/pim/employees/2/screen/personal/attachments?limit=50&offset=0")
            .headers(headers_6),
          http("request_32")
            .get("/web/index.php/api/v2/leave/workweek?model=indexed")
            .headers(headers_12),
          http("request_33")
            .get("/web/index.php/api/v2/pim/employees/2")
            .headers(headers_12),
          http("request_34")
            .get("/web/index.php/api/v2/leave/workweek?model=indexed")
            .headers(headers_12),
          http("request_35")
            .get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31")
            .headers(headers_12),
          http("request_36")
            .get("/web/index.php/api/v2/pim/employees/2/personal-details")
            .headers(headers_6),
          http("request_37")
            .get("/web/index.php/api/v2/pim/employees/2/custom-fields?screen=personal")
            .headers(headers_6),
          http("request_38")
            .get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31")
            .headers(headers_12),
          http("request_39")
            .get("/web/index.php/api/v2/pim/employees")
            .headers(headers_12)
        )
        )

        .pause(39)
      // EMPLOYEE_DETAILS,

        .exec(
      http("request_40")
        .put("/web/index.php/api/v2/pim/employees/2/personal-details")
        .headers(headers_40)
        .body(RawFileBody("fr/onepoint/training/addemployee/0040_request.json"))
        )

        .pause(7)

        .exec(http("request_41")
        .get("/web/index.php/pim/viewPimModule")
        .headers(headers_0)
        .resources(
          http("request_42")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_43")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_44")
            .get("/web/index.php/api/v2/admin/job-titles?limit=0")
            .headers(headers_6),
          http("request_45")
            .get("/web/index.php/api/v2/admin/subunits")
            .headers(headers_6),
          http("request_46")
            .get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
            .headers(headers_6),
          http("request_47")
            .get("/web/index.php/api/v2/admin/employment-statuses?limit=0")
            .headers(headers_6)
        ))


        .pause(15)
        .exec(
      // SEARCH_EMPLOYEE,
      http("request_48")
        .get("/web/index.php/api/v2/pim/employees?nameOrId=IMHAH&includeEmployees=onlyCurrent")
        .headers(headers_6))

        .pause(2)

        .exec(
          http("request_49")
        .get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&nameOrId=IMHAH&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
        .headers(headers_6))
        .pause(15)
      // EMPLOYEE_CONTACTS,

        .exec(http("request_50")
        .get("/web/index.php/pim/viewPersonalDetails/empNumber/2")
        .headers(headers_0)
        .resources(
          http("request_51")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_52")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_53")
            .get("/web/index.php/pim/viewPhoto/empNumber/2")
            .headers(headers_15),
          http("request_54")
            .get("/web/index.php/api/v2/pim/employees/2/custom-fields?screen=personal")
            .headers(headers_6),
          http("request_55")
            .get("/web/index.php/api/v2/leave/workweek?model=indexed")
            .headers(headers_12),
          http("request_56")
            .get("/web/index.php/api/v2/pim/employees/2")
            .headers(headers_12),
          http("request_57")
            .get("/web/index.php/api/v2/pim/employees/2/personal-details")
            .headers(headers_6),
          http("request_58")
            .get("/web/index.php/api/v2/leave/workweek?model=indexed")
            .headers(headers_12),
          http("request_59")
            .get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31")
            .headers(headers_12),
          http("request_60")
            .get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31")
            .headers(headers_12),
          http("request_61")
            .get("/web/index.php/api/v2/pim/employees/2/screen/personal/attachments?limit=50&offset=0")
            .headers(headers_6),
          http("request_62")
            .get("/web/index.php/api/v2/pim/employees")
            .headers(headers_12)
        ))
        .pause(2)

        .exec(
      http("request_63")
        .get("/web/index.php/pim/contactDetails/empNumber/2")
        .headers(headers_0)
        .resources(
          http("request_64")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_65")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_66")
            .get("/web/index.php/pim/viewPhoto/empNumber/2")
            .headers(headers_15),
          http("request_67")
            .get("/web/index.php/api/v2/pim/employee/2/contact-details")
            .headers(headers_6),
          http("request_68")
            .get("/web/index.php/api/v2/pim/employees/2/custom-fields?screen=contact")
            .headers(headers_6),
          http("request_69")
            .get("/web/index.php/api/v2/pim/employees/2")
            .headers(headers_12),
          http("request_70")
            .get("/web/index.php/api/v2/pim/employees/2/screen/contact/attachments?limit=50&offset=0")
            .headers(headers_6)
        ))
        .pause(37)

        .exec(http("request_71")
        .get("/web/index.php/api/v2/pim/employees/2/contact-details/validation/work-emails?workEmail=hassan.imhah%40gatling.test")
        .headers(headers_12))
        .pause(3)

        .exec(http("request_72")
        .get("/web/index.php/api/v2/pim/employees/2/contact-details/validation/work-emails?workEmail=hassan.imhah%40gatling.test")
        .headers(headers_12)
        .resources(
          http("request_73")
            .put("/web/index.php/api/v2/pim/employee/2/contact-details")
            .headers(headers_40)
            .body(RawFileBody("fr/onepoint/training/addemployee/0073_request.json"))
        ))
        .pause(12)
      // DELETE_EMPLOYEE,

        .exec(http("request_74")
        .get("/web/index.php/pim/viewPimModule")
        .headers(headers_0)
        .resources(
          http("request_75")
            .get("/web/index.php/core/i18n/messages")
            .headers(headers_1),
          http("request_76")
            .get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
            .headers(headers_6),
          http("request_77")
            .get("/web/index.php/api/v2/admin/subunits")
            .headers(headers_6),
          http("request_78")
            .get("/web/index.php/api/v2/admin/job-titles?limit=0")
            .headers(headers_6),
          http("request_79")
            .get("/web/index.php/pim/viewPhoto/empNumber/1")
            .headers(headers_15),
          http("request_80")
            .get("/web/index.php/api/v2/admin/employment-statuses?limit=0")
            .headers(headers_6)
        ))
        .pause(5)
        .exec(
      http("request_81")
        .delete("/web/index.php/api/v2/pim/employees")
        .headers(headers_81)
        .body(RawFileBody("fr/onepoint/training/addemployee/0081_request.json")))
        .pause(2)

        .exec(http("request_82")
        .get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
        .headers(headers_6)
        )
        .pause(11)
      );
    }
  ScenarioBuilder scn = scenario("Users").exec(login(), logout());

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
