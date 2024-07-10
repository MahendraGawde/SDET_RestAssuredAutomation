package com.day4;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ParsingJSONResponseData {
    @Test(priority =1)
    void testJsonResponse(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/store")
        .then()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                        .body("store.books[0].author",equalTo("Nigel Rees"));

    }

}
