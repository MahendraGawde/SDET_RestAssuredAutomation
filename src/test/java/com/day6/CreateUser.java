package com.day6;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {
    @Test
    void testCreateUser(ITestContext context){
        Faker faker = new Faker();
        JSONObject userData = new JSONObject();
        userData.put("name", faker.name().fullName());
        userData.put("email",faker.internet().safeEmailAddress());
        userData.put("gender","Male");
        userData.put("status","inactive");



        System.out.println("---------->Create User<------------");
        String bearerToken = "434d24350e9bccfaae6a277e53f18ec46d2af77f8f64e76a14191b77f6c701ef";

        int id = given()
                .headers("Authorization","Bearer " +bearerToken)
                .contentType("application/json")
                .body(userData.toString())

                .when().post("https://gorest.co.in/public/v2/users")
                        .jsonPath().getInt("id");

        System.out.println("Generated ID: " +id);

        context.setAttribute("user_id",id);

        System.out.println(userData);


    }
}
