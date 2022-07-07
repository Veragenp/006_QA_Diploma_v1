package ru.netology.web.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.netology.web.data.AnswerApproved;
import ru.netology.web.data.CardDate;

import static io.restassured.RestAssured.given;

public class SpecificationApi {
    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
    public static void postRequest() {
        AnswerApproved answer = given()
                .body(CardDate.class)
                .when()
                .post("api/v1/pay")
                .then().log().all()
                .extract().as(AnswerApproved.class);
    }
}
