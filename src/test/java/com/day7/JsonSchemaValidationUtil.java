package com.day7;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationUtil {

        /**
         * Utility method for validating a JSON response against a JSON schema.
         *
         * @param url               URL to make the GET request
         * @param jsonSchemaFile    Name of the JSON schema file (located in classpath)
         */
        public static void testJsonSchema(String url, String jsonSchemaFile) {
            try {
                Response response = given()
                        .when()
                        .get(url);

                // Debugging: Log the actual response (optional)
                response.then().log().all();

                // Perform JSON schema validation
                response.then()
                        .assertThat()
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaFile));
            } catch (Exception e) {
                System.err.println("Error during JSON Schema validation: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


