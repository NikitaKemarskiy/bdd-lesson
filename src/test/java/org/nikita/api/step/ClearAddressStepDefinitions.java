package org.nikita.api.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.nikita.api.BaseRequestSpecification;
import org.nikita.api.model.ResponseClearAddress;
import org.nikita.api.model.ResponseIpAddress;

public class ClearAddressStepDefinitions {
    private final static String BASE_PATH = "clear-address";

    private String ip;
    private Response response;

    @Given("IP address to delete is {string}")
    public void ipAddress(String ip) {
        this.ip = ip;
    }

    @When("DELETE request is sent to the Clear-address endpoint with the specified IP address")
    public void delete() {
        response = given()
            .param("ipAddress", ip)
            .delete();
    }

    @Then("Response status is {int} and does not have deleted addresses")
    public void responseCheckNoDeletedAddresses(int expectedStatus) {
        ResponseClearAddress responseClearAddress = response
            .then()
            .statusCode(expectedStatus)
            .extract()
            .as(ResponseClearAddress.class);

        Assert.assertEquals(0, responseClearAddress.getData().getNumReportsDeleted());
    }

    private RequestSpecification given() {
        return RestAssured
            .given()
            .spec(BaseRequestSpecification.given())
            .basePath(BASE_PATH);
    }
}
