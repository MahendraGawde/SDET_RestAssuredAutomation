package com.day6;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {
    @Test
    void testPatchReq(ITestContext context){
        Faker faker = new Faker();
        JSONObject userData = new JSONObject();
        userData.put("name", faker.name().fullName());
        userData.put("email",faker.internet().safeEmailAddress());
        userData.put("status","inactive");

        System.out.println("---------->Update User<------------");
        String bearerToken = "434d24350e9bccfaae6a277e53f18ec46d2af77f8f64e76a14191b77f6c701ef";

        int id = (int) context.getAttribute("user_id"); //This should come from create user req


        given()
                .headers("Authorization","Bearer " +bearerToken)
                .contentType("application/json")
                .pathParam("id",id)
                .body(userData.toString())

                .when().patch("https://gorest.co.in/public/v2/users/{id}")
                        .then()
                            .statusCode(200)
                            .log().all();



    }
}
