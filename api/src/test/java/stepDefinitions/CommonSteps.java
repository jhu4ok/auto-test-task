package stepDefinitions;

import hooks.Hook;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps extends BaseSteps {

    public CommonSteps() {
        super(Hook.getContext());
    }

    @Then("API Response {string} status code is {int}")
    public void validateStatusCode(String responseReference, int expectedStatusCode) {
        Response actualResponse = (Response) context.getContext(responseReference);
        assertThat(actualResponse.getStatusCode())
                .as("API Response status code is unexpected")
                .isEqualTo(expectedStatusCode);
    }

}
