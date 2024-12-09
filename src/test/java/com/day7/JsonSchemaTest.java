package com.day7;

public class JsonSchemaTest {
    public static void main(String[] args) {
        JsonSchemaValidationUtil.testJsonSchema("http://localhost:3000/students", "studentsJsonSchema.json");
    }
}
