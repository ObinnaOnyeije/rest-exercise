package restExercise;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class HomepageTest {
	
	private String base = "http://swapi.co/api/vehicles/";

	@Test
	public void testRequest() {
		given().
			baseUri(base).
			contentType(ContentType.JSON).
		when().
			get().
		then().
			assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON);
	}
	
	@Test
	public void testResponse() {
		given().
			baseUri(base).
			contentType(ContentType.JSON).
		when().
			get().
		then().
			assertThat().
				headers("Vary", "Accept, Cookie", "Content-Type", "application/json");
	}
	
	@Test
	public void testStructure() {
		given().
		baseUri(base).
		contentType(ContentType.JSON).
	when().
		get("/4/").
	then().
		assertThat().
			body(containsString("name")).and().
			body(containsString("model")).and().
			body(containsString("manufacturer"));
	}
	
	@Test
	public void testContent() {
		given().
		baseUri(base).
		contentType(ContentType.JSON).
	when().
		get("/6/").
	then().
		assertThat().
			body("name", equalTo("T-16 skyhopper")).and().
			body("model", equalTo("T-16 skyhopper")).and().
			body("manufacturer", equalTo("Incom Corporation"));
	}
	
}
