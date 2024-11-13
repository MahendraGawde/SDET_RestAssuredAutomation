package com.day6;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

//GSPANN Technologies interview question
public class Students {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://api.example.com";

        // Create the request body with variables studentName and marks
        String requestBody = "{ \"studentName\": \"John Doe\", \"marks\": 85 }";

        // Send POST request and validate response
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/students")
                        .then()
                        .statusCode(200)  // Validate status code
                        .body("marks", greaterThan(80)) // Validate that marks are greater than 80
                        .extract().response();

        // Additional validations (if required)
        int studentId = response.path("studentId");
        String name = response.path("name");
        int marks = response.path("marks");

        // Print values for verification
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
    }
}
