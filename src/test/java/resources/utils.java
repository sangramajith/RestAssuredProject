package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {
	static RequestSpecification req;
	public RequestSpecification requestSpec() throws IOException {
		if(req==null) {
		PrintStream log= new PrintStream(new FileOutputStream("logging.text"));
		   req= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				  .addFilter(RequestLoggingFilter.logRequestTo(log))
				  .addFilter(ResponseLoggingFilter.logResponseTo(log))
				  .setContentType(ContentType.JSON).build();
		   return req;
		}
		  return req;
	}
	public ResponseSpecification responseSpec() {
		 ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
			  .expectContentType(ContentType.JSON).build();
	 return resSpec;
	}
	public String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\pvsam\\eclipse-workspace\\ApiAutomation\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	public String getresponseValue(String response,String value) {
		JsonPath js=new JsonPath(response);
		return js.getString(value);
	}
}
