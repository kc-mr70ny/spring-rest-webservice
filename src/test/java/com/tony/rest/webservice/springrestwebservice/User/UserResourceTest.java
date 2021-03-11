package com.tony.rest.webservice.springrestwebservice.User;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserResourceTest {

    //[
//        {
//            "id": 1,
//                "name": "Adam",
//                "birthDate": "2021-03-11T22:04:07.612+00:00"
//        },
//        {
//            "id": 2,
//                "name": "Bob",
//                "birthDate": "2021-03-11T22:04:07.612+00:00"
//        },
//        {
//            "id": 3,
//                "name": "Carl",
//                "birthDate": "2021-03-11T22:04:07.612+00:00"
//        }
//]

    @Test
    void getUsers() {
        when().
                get("/users").
                then().
                contentType(JSON).
                body("[0].name", equalTo("Adam"));
    }

    @Test
    void createUser() {
        RequestSpecification request = given();
        request.header("content-type", MediaType.APPLICATION_JSON_VALUE);
        request.body(new User(2,"test",new Date()));
        Response response = request.post("/users").andReturn();
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        String location = response.getHeader("location");
        assertTrue("URI is incorrect", location.endsWith("/users/2"));
    }

    @Test
    void deleteUser() {
        Response response = when().delete("/users/2").then().extract().response();
        Assertions.assertEquals(204, response.statusCode());
    }
}