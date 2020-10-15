package PetStore.PetStore;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static PetStore.PetStore.Config.BASE_URL;


import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.MessageResponse;
import Utils.Pet;
import Utils.StatusEnum;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static Actions.PetActions.PET_ENDPOINT;

public class PetTest {
	
	 RequestSpecification requestSpecification = new RequestSpecBuilder()
	            .setBaseUri(BASE_URL)
	            .setContentType(ContentType.JSON).build();

	    @Test
	    public void getPetsByStatus() {
	        List<Pet> pets = given(requestSpecification)
	                .queryParam("status", StatusEnum.available.toString())
	                .get(PET_ENDPOINT + "/findByStatus")
	                .then().log().all()
	                .extract().body()
	                .jsonPath().getList("", Pet.class);
	        for (Pet pet : pets) {
	            Assert.assertEquals(pet.getStatus(), StatusEnum.available);
	        }
	    }
	    
	    @Test
	    public void addNewPet() {
	        given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .body("{\n" +
	                        "  \"id\": 499278344,\n" +
	                        "  \"name\": \"kity\",\n" +
	                        "  \"photoUrls\": [\n" +
	                        "    \"string\"\n" +
	                        "  ],\n" +
	                        "  \"tags\": [],\n" +
	                        "  \"status\": \"sold\"\n" +
	                        "}")
	               
	                .post(PET_ENDPOINT).then().extract().body().jsonPath().prettyPrint();

	        given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .pathParam("petId", "123")
	                .get(PET_ENDPOINT + "/{petId}")
	                .then()
	                .body("name", equalTo("MyLittlePets"))
	                .extract().body().jsonPath()
	                .prettyPrint();
	    }
	    
	    @Test
	    public void updateExistingPet() {
	        given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .body("{\n" +
	                        "  \"id\": 499278344,\n" +
	                        "  \"name\": \"kity\",\n" +
	                        "  \"photoUrls\": [\n" +
	                        "    \"string\"\n" +
	                        "  ],\n" +
	                        "  \"tags\": [],\n" +
	                        "  \"status\": \"sold\"\n" +
	                        "}")
	                .put(PET_ENDPOINT);

	        given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .pathParam("petId", "499278344")
	                .get(PET_ENDPOINT + "/{petId}")
	                .then()
	                .body("name", equalTo("kity"))
	                .extract().body().jsonPath()
	                .prettyPrint();
	    }
	    
	    
	    @Test
	    public void deletePetById() {
	        given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .pathParam("petId", "898988888")
	                .expect().statusCode(200)
	                .when()
	                .delete(PET_ENDPOINT + "/{petId}");

	        Assert.assertEquals(
	                given()
	                .baseUri(BASE_URL)
	                .log().everything()
	                .contentType(ContentType.JSON)
	                .pathParam("petId", "898988888")
	                .get(PET_ENDPOINT + "/{petId}")
	                .then()
	                .extract().body().jsonPath().getObject("", MessageResponse.class)
	                .getMessage(), "Pet not found");
	    }


}
