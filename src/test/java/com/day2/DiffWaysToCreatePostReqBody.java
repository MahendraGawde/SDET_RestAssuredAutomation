package com.day2;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Json Server should be up and running to run retrieve data from Json.
public class DiffWaysToCreatePostReqBody {

    //1. Using Hashmap
   // @Test(priority = 1)
    void testPostUsingHashmap(){
        HashMap data = new HashMap();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456" );

        String[] courseArr = {"C","C++"};
        data.put("courses", courseArr);

        given().contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    //2. Post request using org.json library
//    @Test(priority = 1)
    void testPostByJsonLibrary(){
        JSONObject data = new JSONObject();
        data.put("name","Tony");
        data.put("location","Texas");
        data.put("phone","123456" );

        String[] courseArr = {"Java","Python"};
        data.put("courses", courseArr);

        given().contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Tony"))
                .body("location",equalTo("Texas"))
                .body("phone",equalTo("123456"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("Python"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    //3. test POST req using POJO
//    @Test(priority =1)
    void testPostUsingPOJO() {

        Pojo_PostReq data = new Pojo_PostReq();
        data.setName("Vishal");
        data.setLocation("New Jersey");
        data.setPhone("999555777");
        String[] courseArr = {"Java","Python"};
        data.setCourses(courseArr);
        given().contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Vishal"))
                .body("location",equalTo("New Jersey"))
                .body("phone",equalTo("999555777"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("Python"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();

    }

    //4. test Post using external json file

    @Test(priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File jsonfile = new File(".\\src\\test\\resources\\body.json");
        FileReader fileReader = new FileReader(jsonfile);
        JSONTokener jToken = new JSONTokener(fileReader);
        JSONObject data = new JSONObject(jToken);

        given().contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Vishal"))
                .body("location",equalTo("New Jersey"))
                .body("phone",equalTo("999555777"))
                .body("courses[0]",equalTo("Playwright"))
                .body("courses[1]",equalTo("Selenium"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();



    }
    @Test(priority = 2)
    void testDelete(){
        given().when()
                .delete("http://localhost:3000/students/11")
                .then().statusCode(200)
                .log().all();
    }
}
