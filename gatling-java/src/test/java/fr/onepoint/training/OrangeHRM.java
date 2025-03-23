package fr.onepoint.training;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.Session;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class OrangeHRM {
	private static FeederBuilder.Batchable employees = csv("data/employees.csv").random();
	private static FeederBuilder.Batchable search_employee = csv("data/employee_created.csv").random();

	public static Duration thinkTime() {
		Random rnd = new Random();
		int thinkTimeMillis = rnd.nextInt(400) + 100;
		return Duration.ofMillis(thinkTimeMillis);
	}

	public static String CurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return (currentDate.format(dateFormatter));
	}

	public static String CurrentTime() {
		// Generate current time in format HH:mm
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		return (currentTime.format(timeFormatter));
	}

	public static void writeFile(String filename, String s) throws IOException {
		File file = new File(filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.write(s + "\n");
		bw.close();
	}

	private static Map<CharSequence, String> headers_0 = Map.ofEntries(Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"), Map.entry("Priority", "u=0, i"), Map.entry("Upgrade-Insecure-Requests", "1"));

	private static Map<CharSequence, String> headers_1 = Map.ofEntries(Map.entry("If-None-Match", "\"BY94ts6Fi+1Yi8fPMZszFQOk+IVVK7mRggQqqOjvOzs=\""), Map.entry("contentType", "application/json"));

	private static Map<CharSequence, String> headers_2 = Map.ofEntries(Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"), Map.entry("Origin", "http://localhost:8060"), Map.entry("Priority", "u=0, i"), Map.entry("Upgrade-Insecure-Requests", "1"));

	private static Map<CharSequence, String> headers_4 = Map.of("Origin", "http://localhost:8060");

	private static Map<CharSequence, String> headers_5 = Map.ofEntries(Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"), Map.entry("Priority", "u=4, i"));

	private static Map<CharSequence, String> headers_6 = Map.of("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");

	private static Map<CharSequence, String> headers_12 = Map.of("Accept", "application/json, text/plain, */*");

	private static Map<CharSequence, String> headers_15 = Map.ofEntries(Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"), Map.entry("If-Modified-Since", "Fri, 19 Jul 2024 04:22:38 GMT"), Map.entry("If-None-Match", "\"eSWfz0aTMRL2qx3Xp+IS4qMVAXpmiSacwlj2vIk2pmo=\""), Map.entry("Priority", "u=5"));

	private static Map<CharSequence, String> headers_26 = Map.ofEntries(Map.entry("Content-Type", "application/json"), Map.entry("Origin", "http://localhost:8060"));

	private static Map<CharSequence, String> headers_40 = Map.ofEntries(Map.entry("Accept", "application/json, text/plain, */*"), Map.entry("Content-Type", "application/json"), Map.entry("Origin", "http://localhost:8060"));

	private static Map<CharSequence, String> headers_81 = Map.ofEntries(Map.entry("Accept", "application/json, text/plain, */*"), Map.entry("Content-Type", "application/json"), Map.entry("Origin", "http://localhost:8060"), Map.entry("Priority", "u=0"));

	public static ChainBuilder login() {
		return (
			group ("Login").on (
			exec(
				// HOME,
				http("000:GET Home").get("/").headers(headers_0)
					// .check(bodyString().saveAs("body"))
					.check(regex(":token=\"&quot;(.*?)&quot;").saveAs("csrf_token")).resources(http("001:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1)

					)).exec(session -> {
					//System.out.println(session.getString("body"));
					System.out.println(session.getString("csrf_token"));
					return session;
				}).pause(thinkTime())
				// LOGIN,
				.exec(session -> {
					Session newSession = session.set("currentDate", CurrentDate()).set("currentTime", CurrentTime());
					return (newSession);
				}).exec(http("002:POST index.php/auth/validate").post("/web/index.php/auth/validate").headers(headers_2).formParam("_token", "#{csrf_token}").formParam("username", "Admin").formParam("password", "Onepoint$2025").resources(http("003:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("004:POST index.php/events/push").post("/web/index.php/events/push").headers(headers_4), http("005:GET viewPhoto/empNumber/1").get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_5), http("006:GET dashboard/employees/action-summary").get("/web/index.php/api/v2/dashboard/employees/action-summary").headers(headers_6), http("007:GET dashboard/employees/subunit")

					.get("/web/index.php/api/v2/dashboard/employees/subunit").headers(headers_6), http("008:GET dashboard/employees/time-at-work")

					.get("/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=1&currentDate=#{currentDate}&currentTime=#{currentTime}").headers(headers_6), http("009:GET dashboard/employees/leaves")

					.get("/web/index.php/api/v2/dashboard/employees/leaves?date=#{currentDate}").headers(headers_6), http("010:GET dashboard/employees/locations")

					.get("/web/index.php/api/v2/dashboard/employees/locations").headers(headers_6), http("011:GET api/dashboard/shortcuts")

					.get("/web/index.php/api/v2/dashboard/shortcuts").headers(headers_6), http("012:GET api/buzz/feed")

					.get("/web/index.php/api/v2/buzz/feed?limit=5&offset=0&sortOrder=DESC&sortField=share.createdAtUtc").headers(headers_12))).pause(thinkTime())
		)
		);
	}

	public static ChainBuilder logout() {
		return (
			group ("logout").on (

				exec(http("013:GET index.php/auth/logout").get("/web/index.php/auth/logout").headers(headers_0).resources(http("014:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1)))
			));
	}

	public static ChainBuilder add_employee() {
		// ADD_EMPLOYEE,
		return (
			group ("AddEmployee").on (

				feed(employees).exec(http("015:GET index.php/pim/viewPimModule").get("/web/index.php/pim/viewPimModule").headers(headers_0).resources(http("016:GET core/i18n/messages")
					.get("/web/index.php/core/i18n/messages").headers(headers_1), http("017:GET viewPhoto/empNumber/1")
					.get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("018:GET api/admin/employment-statuses")
					.get("/web/index.php/api/v2/admin/employment-statuses?limit=0").headers(headers_6), http("019:GET api/admin/subunits")
					.get("/web/index.php/api/v2/admin/subunits").headers(headers_6), http("020:GET api/pim/employees")
					.get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC").headers(headers_6), http("021:GET api/admin/job-titles")
					.get("/web/index.php/api/v2/admin/job-titles?limit=0").headers(headers_6))).pause(thinkTime()).exec(http("022:GET index.php/pim/addEmployee").get("/web/index.php/pim/addEmployee").headers(headers_0).resources(http("023:GET core/i18n/messages")
					.get("/web/index.php/core/i18n/messages").headers(headers_1), http("024:GET viewPhoto/empNumber/1")
					.get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("025:GET api/pim/employees")
					.get("/web/index.php/api/v2/pim/employees").headers(headers_6))).pause(thinkTime()).exec(http("026:GET core/validation/unique").get("/web/index.php/api/v2/core/validation/unique?value=#{matricule}&entityName=Employee&attributeName=employeeId").headers(headers_12)).pause(thinkTime()).exec(http("027:GET core/validation/unique").get("/web/index.php/api/v2/core/validation/unique?value=#{matricule}&entityName=Employee&attributeName=employeeId").headers(headers_12).resources(http("028:POST api/pim/employees").post("/web/index.php/api/v2/pim/employees").headers(headers_26).body(ElFileBody("AddEmployee.json")).check(jsonPath("$..data.empNumber").saveAs("employeeId"))))

				.exec(session -> {
					System.out.println("employeeId : " + session.getString("employeeId"));
					try {
						writeFile("C:\\github_himhah-op\\gatling-training\\gatling-java\\src\\test\\resources\\data\\employee_created.csv", session.getString("employeeId") + "," + session.getString("nom"));
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					return session;
				})


				.pause(thinkTime()).exec(http("029:GET viewPersonalDetails/empNumber/{employeeId}").get("/web/index.php/pim/viewPersonalDetails/empNumber/#{employeeId}").headers(headers_0).resources(http("030:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("031:GET viewPhoto/empNumber/1")
					.get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("032:GET viewPhoto/empNumber/{employeeId}")
					.get("/web/index.php/pim/viewPhoto/empNumber/#{employeeId}").headers(headers_5), http("033:GET screen/personal/attachments")
					.get("/web/index.php/api/v2/pim/employees/#{employeeId}/screen/personal/attachments?limit=50&offset=0").headers(headers_6), http("034:GET api/leave/workweek")
					.get("/web/index.php/api/v2/leave/workweek?model=indexed").headers(headers_12), http("035:GET pim/employees/{employeeId}")
					.get("/web/index.php/api/v2/pim/employees/#{employeeId}").headers(headers_12), http("036:GET api/leave/workweek")
					.get("/web/index.php/api/v2/leave/workweek?model=indexed").headers(headers_12), http("037:GET api/leave/holidays")
					.get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31").headers(headers_12), http("038:GET employees/{employeeId}personal-details")
					.get("/web/index.php/api/v2/pim/employees/#{employeeId}/personal-details").headers(headers_6), http("039:GET employees/{employeeId}custom-fields")
					.get("/web/index.php/api/v2/pim/employees/#{employeeId}/custom-fields?screen=personal").headers(headers_6), http("040:GET api/leave/holidays")
					.get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31").headers(headers_12), http("041:GET api/pim/employees")
					.get("/web/index.php/api/v2/pim/employees").headers(headers_12))).pause(thinkTime())
		)
		);
	}

	public static ChainBuilder search_employee(String nom) {
		// SEARCH_EMPLOYEE,
		return (
			group ("SearchEmployee").on (

					exec(http("050:GET api/pim/employees")
						.get("/web/index.php/api/v2/pim/employees?nameOrId=#{nom}&includeEmployees=onlyCurrent")
						.headers(headers_6))
					.pause(thinkTime())
					.exec(http("051:GET api/pim/employees")
						.get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&nameOrId=#{nom}&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC")
						.headers(headers_6)
						.check(jsonPath("$..data[0].empNumber").saveAs("employeeId")))

					.exec(session -> {
						System.out.println("051:GET api/pim/employees : employeeId = " + session.getString("employeeId"));
						return session;
					})
					.pause(thinkTime())
			));
	}

	public static ChainBuilder employee_details() {
		// ADD_EMPLOYEE,
		return (
			group ("EmployeeDetails").on (
				// EMPLOYEE_DETAILS,
				exec(http("042:PUT employees/{employeeId}personal-details").put("/web/index.php/api/v2/pim/employees/#{employeeId}/personal-details").headers(headers_40).body(ElFileBody("EmployeeDetails.json"))).pause(thinkTime()).exec(http("043:GET index.php/pim/viewPimModule").get("/web/index.php/pim/viewPimModule").headers(headers_0).resources(http("044:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("045:GET viewPhoto/empNumber/1").get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("046:GET api/admin/job-titles").get("/web/index.php/api/v2/admin/job-titles?limit=0").headers(headers_6), http("047:GET api/admin/subunits").get("/web/index.php/api/v2/admin/subunits").headers(headers_6), http("048:GET api/pim/employees").get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC").headers(headers_6), http("049:GET api/admin/employment-statuses").get("/web/index.php/api/v2/admin/employment-statuses?limit=0").headers(headers_6))).pause(thinkTime())
		)
		);
	}

	public static ChainBuilder employee_contact() {
		// ADD_EMPLOYEE,
		return (
			group ("EmployeeContact").on (

				// EMPLOYEE_CONTACTS,
			exec(http("052:GET viewPersonalDetails/empNumber/{employeeId}").get("/web/index.php/pim/viewPersonalDetails/empNumber/#{employeeId}").headers(headers_0).resources(http("053:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("054:GET viewPhoto/empNumber/1").get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("055:GET viewPhoto/empNumber/{employeeId}").get("/web/index.php/pim/viewPhoto/empNumber/#{employeeId}").headers(headers_15), http("056:GET employees/{employeeId}custom-fields").get("/web/index.php/api/v2/pim/employees/#{employeeId}/custom-fields?screen=personal").headers(headers_6), http("057:GET api/leave/workweek").get("/web/index.php/api/v2/leave/workweek?model=indexed").headers(headers_12), http("058:GET pim/employees/{employeeId}").get("/web/index.php/api/v2/pim/employees/#{employeeId}").headers(headers_12), http("059:GET employees/{employeeId}personal-details").get("/web/index.php/api/v2/pim/employees/#{employeeId}/personal-details").headers(headers_6), http("060:GET api/leave/workweek").get("/web/index.php/api/v2/leave/workweek?model=indexed").headers(headers_12), http("061:GET api/leave/holidays").get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31").headers(headers_12), http("062:GET api/leave/holidays").get("/web/index.php/api/v2/leave/holidays?fromDate=2025-01-01&toDate=2025-12-31").headers(headers_12), http("063:GET screen/personal/attachments").get("/web/index.php/api/v2/pim/employees/#{employeeId}/screen/personal/attachments?limit=50&offset=0").headers(headers_6), http("064:GET api/pim/employees").get("/web/index.php/api/v2/pim/employees").headers(headers_12))).pause(thinkTime())
				.exec(http("065:GET contactDetails/empNumber/{employeeId}").get("/web/index.php/pim/contactDetails/empNumber/#{employeeId}").headers(headers_0).resources(http("066:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("067:GET viewPhoto/empNumber/1").get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("068:GET viewPhoto/empNumber/{employeeId}").get("/web/index.php/pim/viewPhoto/empNumber/#{employeeId}").headers(headers_15), http("069:GET employee/{employeeId}contact-details").get("/web/index.php/api/v2/pim/employee/#{employeeId}contact-details").headers(headers_6), http("070:GET employees/{employeeId}custom-fields").get("/web/index.php/api/v2/pim/employees/#{employeeId}/custom-fields?screen=contact").headers(headers_6), http("071:GET pim/employees/{employeeId}").get("/web/index.php/api/v2/pim/employees/#{employeeId}").headers(headers_12), http("072:GET screen/contact/attachments").get("/web/index.php/api/v2/pim/employees/#{employeeId}/screen/contact/attachments?limit=50&offset=0").headers(headers_6))).pause(thinkTime())
				.exec(http("073:GET contact-details/validation/work-emails").get("/web/index.php/api/v2/pim/employees/#{employeeId}/contact-details/validation/work-emails?workEmail=hassan.dupont%40gatling.test").headers(headers_12)).pause(thinkTime())
				.exec(http("074:GET contact-details/validation/work-emails").get("/web/index.php/api/v2/pim/employees/#{employeeId}/contact-details/validation/work-emails?workEmail=hassan.dupont%40gatling.test").headers(headers_12).resources(http("075:PUT employee/{employeeId}contact-details").put("/web/index.php/api/v2/pim/employee/#{employeeId}/contact-details").headers(headers_40).body(ElFileBody("EmployeeContactDetails.json"))).check(bodyString().saveAs("body"))
				).exec(session -> {
					System.out.println("075:PUT employee : " + session.getString("body"));
					return session;
				}).pause(thinkTime())
		)
		);
	}

	public static ChainBuilder delete_employee() {
		return (
			// DELETE_EMPLOYEE,
			group ("DeleteEmployee").on (
				exec(http("076:GET index.php/pim/viewPimModule").get("/web/index.php/pim/viewPimModule").headers(headers_0).resources(http("077:GET core/i18n/messages").get("/web/index.php/core/i18n/messages").headers(headers_1), http("078:GET api/pim/employees").get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC").headers(headers_6), http("079:GET api/admin/subunits").get("/web/index.php/api/v2/admin/subunits").headers(headers_6), http("080:GET api/admin/job-titles").get("/web/index.php/api/v2/admin/job-titles?limit=0").headers(headers_6), http("081:GET viewPhoto/empNumber/1").get("/web/index.php/pim/viewPhoto/empNumber/1").headers(headers_15), http("082:GET api/admin/employment-statuses").get("/web/index.php/api/v2/admin/employment-statuses?limit=0").headers(headers_6))).pause(thinkTime()).exec(http("083:DELETE api/pim/employees").delete("/web/index.php/api/v2/pim/employees").headers(headers_81).body(ElFileBody("DeleteEmployee.json"))).pause(thinkTime())
				.exec(http("084:GET api/pim/employees").get("/web/index.php/api/v2/pim/employees?limit=50&offset=0&model=detailed&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC").headers(headers_6)).pause(thinkTime())
			)
		);
	}

	public static ChainBuilder vu_add_employee() {
		return (
			exec(login()).exec(add_employee()).exec(employee_details()).exec(employee_contact()).exec(logout())
		);
	}

	public static ChainBuilder vu_search_employee() {
		return (
			feed (search_employee)
				.exec(login())
				.exec(session -> {
					System.out.println("Search : " + session.getString("nom"));
					return session;
				})
				.exec(search_employee("#{nom}"))
				.exec(logout())
		);
	}
}
