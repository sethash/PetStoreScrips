package Actions;

import static PetStore.PetStore.Config.*;
import static io.restassured.RestAssured.given;


import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PetActions {
	 public static String PET_ENDPOINT = BASE_URL + "/pet";
	    private RequestSpecification requestSpecification;
	    
	    public PetActions()
	    {
	        requestSpecification = new RequestSpecBuilder()
	                 .setBaseUri(BASE_URL)
	                .setContentType(ContentType.JSON).build();
	    }
	    
	    public Utils.Pet addNewPet(Utils.Pet request) {
	        return given(requestSpecification)
	                .body(request)
	                .post(PET_ENDPOINT).as(Utils.Pet.class);
	    }
	    
	    public List<Utils.Pet> getPetsByStatus() {
	        return given(requestSpecification)
	                .queryParam("status","available")
	                .get(PET_ENDPOINT + "/findByStatus")
	                .then().log().all()
	                .extract().body()
	                .jsonPath().getList("", Utils.Pet.class);

	    }

	    }


