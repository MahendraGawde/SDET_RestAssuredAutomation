package com.day6;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    void testGetUser(ITestContext context){
        int id = (int) context.getAttribute("user_id"); //This should come from create user req
        String bearerToken = "434d24350e9bccfaae6a277e53f18ec46d2af77f8f64e76a14191b77f6c701ef";

        System.out.println("---------->Get User<------------");

         given()
                .headers("Authorization","Bearer " +bearerToken)
                 .pathParam("id",id)
                 .when()
                 .get("https://gorest.co.in/public/v2/users/{id}")
                 .then()
                 .statusCode(200)
                 .log().all();




    }
}
