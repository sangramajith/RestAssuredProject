package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.RequestDataBuild;
import resources.utils;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class stepDefinition extends utils {
	 RequestSpecification res;
	 ResponseSpecification resSpec;
	 Response response;
	  
	@Given("Add Place playload")
	public void add_place_playload() throws IOException {
		  res=given().spec(requestSpec())
				  .body(RequestDataBuild.addPlaceData()); 
	}
	@When("user calls {string} api with post call")
	public void user_calls_api_with_post_call(String string) {
		
		 response=res.when().post("/maps/api/place/add/json")
				  .then().spec(responseSpec()).extract().response();
	}
	@Then("verify status code {int}")
	public void verify_status_code(int statusCodeExpected) {
	
		assertEquals(response.statusCode(),statusCodeExpected);
		
	}
	@Then("verify {string} in response body is {string}")
	public void verify_in_response_body_is_ok(String item,String expectedValue) {
		 JsonPath js=new JsonPath(response.asString());
		 String status=js.getString(""+item+"");
		 System.out.println(status);
		 assertEquals(status,expectedValue);
	}
	
}
