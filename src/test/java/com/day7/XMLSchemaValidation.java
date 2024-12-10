package com.day7;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class XMLSchemaValidation {
    @Test
    public void xmlSchemaValidator(){
        given().when()
                .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
    }
}
