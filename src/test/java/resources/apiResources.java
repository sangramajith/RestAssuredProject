package resources;
//enum=class which has collection of constants or methods
public enum apiResources {

	AddPlaceApi("maps/api/place/add/json"),
	getPlaceApi("maps/api/place/get/json"),
	deletePlaceApi("maps/api/place/delete/json");
    private String resource;
	apiResources(String resource) {
	  this.resource=resource;
	}
	public String getResource() {
	     return resource;

	}
} 