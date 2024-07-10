package com.day1;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.HashMap;

public class HTTPRequests {
    int id;
    /*
    given() - content type, set cookies, add auth, add param, set headers info etc..
    when() - get post put patch delete
    then() - validate status code, extract response/headers,cookies and response body.
     */

    @Test(priority = 1)
    void getUsers(){
                given()
                .when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                        .body("page",equalTo(2))
                        .log().all();
    }

    @Test(priority = 2)
    void createUser(){

        HashMap userData = new HashMap();
        userData.put("name","Vidya");
        userData.put("job","trainer");

        id = given()
                .contentType("application/json")
                .body(userData)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                /*.then()
                .statusCode(201)
                .log().all();*/
        System.out.println("id created: " +id);

    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})

    void updateUser(){
        HashMap data = new HashMap();
        data.put("name","Vikrant");
        data.put("job","Automation");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then().statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser(){
        given()
                .when().delete("https://reqres.in/api/users/"+id)
                .then().statusCode(204)
                .log().all();
    }

    

}
