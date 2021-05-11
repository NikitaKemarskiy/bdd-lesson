package org.nikita.api.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.nikita.api.BaseRequestSpecification;
import org.nikita.api.model.ResponseIpAddress;

public class CheckStepDefinitions {
    private final static String BASE_PATH = "check";
    private final static String INVALID_IP_ERROR_MESSAGE = "The ip address must be a valid IPv4 or IPv6 address (e.g. 8.8.8.8 or 2001:4860:4860::8888).";

    private String ip;
    private Response response;

    @Given("IP address to check is {string}")
    public void ipAddress(String ip) {
        this.ip = ip;
    }

    @When("GET request is sent to the Check endpoint with the specified IP address")
    public void get() {
        response = given()
            .param("ipAddress", ip)
            .get();
    }

    @Then("Response status is {int} and IP address is public")
    public void responseCheckIsPublic(int expectedStatus) {
        ResponseIpAddress responseIpAddress = response
            .then()
            .statusCode(expectedStatus)
            .extract()
            .as(ResponseIpAddress.class);

        Assert.assertEquals(ip, responseIpAddress.getData().getIpAddress());
        Assert.assertTrue(responseIpAddress.getData().isPublic());
    }

    @Then("Response status is {int} and IP address is private")
    public void responseCheckIsPrivate(int expectedStatus) {
        ResponseIpAddress responseIpAddress = response
                .then()
                .statusCode(expectedStatus)
                .extract()
                .as(ResponseIpAddress.class);

        Assert.assertEquals(ip, responseIpAddress.getData().getIpAddress());
        Assert.assertFalse(responseIpAddress.getData().isPublic());
    }

    @Then("Response status is {int} and response has invalid IP error message")
    public void responseCheckHasInvalidIpError(int expectedStatus) {
        ResponseIpAddress responseIpAddress = response
                .then()
                .statusCode(expectedStatus)
                .extract()
                .as(ResponseIpAddress.class);

        Assert.assertTrue(responseIpAddress.getErrors().length > 0);
        Assert.assertEquals(INVALID_IP_ERROR_MESSAGE, responseIpAddress.getErrors()[0].getDetail());
    }

    private RequestSpecification given() {
        return RestAssured
            .given()
            .spec(BaseRequestSpecification.given())
            .basePath(BASE_PATH);
    }
}
