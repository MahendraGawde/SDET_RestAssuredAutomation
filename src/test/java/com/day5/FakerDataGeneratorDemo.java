package com.day5;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGeneratorDemo {
    @Test
    void testGenerateDummyData(){
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String mobileNo = faker.phoneNumber().cellPhone();
        String userName = faker.name().username();
        String password = faker.internet().password();
        String emailAdd = faker.internet().safeEmailAddress();

        System.out.println("Full Name:" +fullName);
        System.out.println("First Name: "+firstName);
        System.out.println("Last Name: "+lastName);
        System.out.println("Mobile no.: " +mobileNo);
        System.out.println("User name: " +userName);
        System.out.println("Password: " +password);
        System.out.println("Email address: " +emailAdd);
    }
}
