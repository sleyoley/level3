package com.fullstack.batch3;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fullstack.batch3.common.DataUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APITest extends BaseAPITest {

	String token = "token not found";

	@Test(priority = 1)
	public void testLoginAPI() {
		Response response1 = RestAssured.given().spec(commonSpec).when()
				.body(DataUtils.getDataFromExcel("APIData", "LoginAPI")).post("users/sign_in");

		JsonPath jsonpath = response1.jsonPath();

		String actualEmail = jsonpath.getString("user.email");
		token = jsonpath.getString("user.authtoken");

		assertEquals(response1.getStatusCode(), 200);
		assertEquals(actualEmail, DataUtils.getDataFromExcel("APIData", "Username"));
		assertEquals(token.length() >= 20, true);

	}

	@Test(priority = 2)
	public void testDashboardAPI() {
		Response response1 = RestAssured.given().spec(commonSpec).header("authtoken", token).when()
				.formParam("status", "card_completed").get("build_cards");

		JsonPath jsonpath = response1.jsonPath();

		int cardCount = jsonpath.getInt("build_card_count.completed");

		assertEquals(response1.getStatusCode(), 200);
		assertEquals(cardCount, 6219);

	}
}
