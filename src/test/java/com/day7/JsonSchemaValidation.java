package com.day7;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidation {
    @Test
    public void jsonSchemaValidationTest(){
       Response response = given().
                when().get("http://localhost:3000/students");

       response.then().log().all();
       response.then().assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("studentsJsonSchema.json"));

    }
}
