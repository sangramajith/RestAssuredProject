package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		stepDefinition sdf=new stepDefinition();
		if(stepDefinition.place_ID==null) {
		sdf.add_place_playload_with("Delete Creator", "Java", "delete_the_place");
		sdf.user_calls_api_with_post_call("AddPlaceApi", "Post");
		sdf.verify_place_id_created_maps_to_using("Delete Creator", "getPlaceApi");
		
		}
	}
	
}
