package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import javax.annotation.meta.When;

public class TC_VediogameAPI {

		@Test(priority = 1)
		public void test_getAllVideogames() {
			given()
			.when()
				.get("http://localhost:8080/app/videogames ")
				.then()
				.statusCode(200);
			System.out.println("Test case 1 passed ");
			
		}
		@Test (priority = 2)
		public void addNewGame() {

			HashMap data = new HashMap();
			data.put("id", "100");
			data.put("Name", "Spide-man");
			data.put("ReleaseDate", "2019-09-20T08:55:58:510z");
			data.put("ReviewScore", "5");
			data.put("Category", "adventure");
			data.put("rating", "universal");
			
			Response res = 
					given()
					.contentType("application/json")
					.body(data)
					.when()
					.post("http://localhost:8080/app/videogames")
					.then()
					.statusCode(200)
					.log().body()
					.extract().response();
					String jesonString = res.asString();
					Assert.assertEquals(jesonString.contains("Record Added Successfully"),true);
					System.out.println("Test case 2 passed ");
		}
		
		@Test (priority =3)
		public void getVideoGame() {
			given()
			.when()
			.get("http://localhost:8080/app/videogames/5")
			.then()
				.statusCode(200)
				//.log().body()
				.body("videoGame.id", equalTo("5"))
				.body("videoGame.name", equalTo("The Legend of Zelda: Ocarina of Time"));
			System.out.println("Test case 3 passed ");
		}
		@Test (priority =4)
		public void updateVideogameName() {
			HashMap data = new HashMap();
			data.put("id","5");
			data.put("name","Paceman");
			data.put("releaseDate","2019-09-20T08:57:58.510Z");
			data.put("reviewScore","4");
			data.put("category","Adventure");
			data.put("rating","Universal");
			
			given()
			.contentType("application/json")
			.body(data)
			
			.when()
			.put("http://localhost:8080/app/videogames/5")
			
			.then()
			 .statusCode(200)
			 .log().body()
			 .body("videoGame.id", equalTo("5"))
				.body("videoGame.name", equalTo("Paceman"));
			System.out.println("Test case 4 passed ");
		}
		
		
		@Test (priority = 5)
		public void deleteVideogame() {
			Response res = 
			given()
			.when()
			.delete("http://localhost:8080/app/videogames/5")
			.then()
			.statusCode(200)
			.log().body()
			.extract().response();			
			String jsonRes = res.asString() ;
			Assert.assertEquals(jsonRes.contains("Record Deleted Successfully") , true);
			System.out.println("Test case 5 passed ");
		}
}
	