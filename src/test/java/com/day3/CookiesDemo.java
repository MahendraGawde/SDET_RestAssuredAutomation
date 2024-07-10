package com.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {
    // Cookie value changes for every session or when refresh so when this method fails our
    // test shall pass.
    @Test(priority = 1)
    void testCookies(){
        given()
                .when()
                    .get("https://www.google.com")
                .then()
                    .cookie("AEC","AQTF6HzwQBXCBdUU_klGNVZelTNKsPflt675Fk3bPrQL4iPvC045ctH9NLQ")
                    .log().all();

    }

    @Test(priority = 2)
    void getCookiesInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com");

        // Get single cookie value
        // String cookieValue = res.getCookie("AEC");
        // System.out.println("Value of cookie is ==>" +cookieValue);

        //Get all cookies info
        Map <String,String> allCookiesValues = res.getCookies();
//        System.out.println(allCookiesValues.keySet());

        for(String cookie: allCookiesValues.keySet()){
            String cookieVal = res.getCookie(cookie);
            System.out.println(cookie+ " " +cookieVal);

        }
    }
}
