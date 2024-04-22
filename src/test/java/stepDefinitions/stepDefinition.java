package stepDefinitions;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.deletePaylod;
import resources.RequestDataBuild;
import resources.apiResources;
import resources.utils;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class stepDefinition extends utils {
	 RequestSpecification res;
	 ResponseSpecification resSpec;
	 Response response;
	 static String place_ID;
	 @Given("Add Place playload with {string} {string} {string}")
	 public void add_place_playload_with(String name, String language, String address) throws IOException {
		  res=given().spec(requestSpec())
				  .body(RequestDataBuild.addPlaceData(name,language,address)); 
	}
	@When("user calls {string} api with {string} call")
	public void user_calls_api_with_post_call(String resource, String httpMethod) {
		 apiResources resourceName=apiResources.valueOf(resource);
		 if(httpMethod.equalsIgnoreCase("POST")) {
			 response=res.when().post(resourceName.getResource());
		 }
		
		 else if(httpMethod.equalsIgnoreCase("GET")) {
			 response=res.when().get(resourceName.getResource());
		 }
		 else if(httpMethod.equalsIgnoreCase("DELETE")) {
			 response=res.when().delete(resourceName.getResource());
		 }
				 
	}
	@Then("verify status code {int}")
	public void verify_status_code(int statusCodeExpected) {
	    response= response.then().spec(responseSpec()).extract().response();
		assertEquals(response.statusCode(),statusCodeExpected);
		
	}
	@Then("verify {string} in response body is {string}")
	public void verify_in_response_body_is_ok(String item,String expectedValue) {
		 JsonPath js=new JsonPath(response.asString());
		 String status=js.getString(""+item+"");
		 System.out.println(status);
		 assertEquals(status,expectedValue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String field, String resource) throws IOException {
	   
		 place_ID=getresponseValue(response.asString(),"place_id");
	     
		 res=given().spec(requestSpec()).queryParam("place_id",place_ID);
		 user_calls_api_with_post_call(resource,"GET");
		 
		 String nameFromResponse=getresponseValue(response.asString(),"name");
		 assertEquals(nameFromResponse,field);
		
	}
	@Given("delete place api with payload")
	public void delete_place_api_with_payload() throws IOException {
	
		pojoClasses.deletePaylod dp=new deletePaylod();
		dp.setplace_id(place_ID);
	   
	    res=given().spec(requestSpec()).body(dp);
	}
	
}
