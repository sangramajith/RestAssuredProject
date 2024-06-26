package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.fullBody;
import pojoClasses.location;

public class RequestDataBuild {

	public static fullBody addPlaceData(String name, String language, String address) {
		  fullBody fb=new fullBody();
		  fb.setAccuracy(50);
		  fb.setName(name);
		  fb.setAddress(address);
		  fb.setLanguage(language);
		  fb.setPhone_number("4568741236");
		  fb.setWebsite("https://www.google.com");
		  
		  List<String>Types=new ArrayList<String>();
		  Types.add("Chemical");
		  Types.add("petrol");
		  
		  fb.setTypes(Types);
		  
		  location loc=new location();
		  loc.setLat(91.33);
		  loc.setLng(52.63);
		  
		  fb.setLocation(loc);
		  return fb;
	}
}
