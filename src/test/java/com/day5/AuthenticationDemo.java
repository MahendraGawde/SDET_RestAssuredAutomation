package com.day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class AuthenticationDemo {

    //Basic , Digest and Preemptive uses similar working mechanism of username and password.
    @Test(priority =1)
    void testBasicAuthentication(){

        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 2)
    void testDigestAuthentication(){

        given()
                .auth().digest("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 3)
    void testPreemptiveAuthentication(){

        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 4)
    void testBearerTokenAuth(){
        String bearerToken = "getItfromGithubToken";
        given()
                .header("Authorization","Bearer" +bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    //OAuth1.0 authentication example requires multiple parameters to pass.
    void testOAuthDemo(){
        given()
                .auth().oauth("consumerKey","consumerSecret","accessToken","tokenSecret")
                .when()
                .get("url")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testOAuth2Demo(){
        given()
                .auth().oauth2("getItfromGithubToken")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

}
