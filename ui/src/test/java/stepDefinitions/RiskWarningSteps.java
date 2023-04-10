package stepDefinitions;

import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageActions.RiskWarningPage;

import static org.assertj.core.api.Assertions.assertThat;

public class RiskWarningSteps {

    private final RiskWarningPage riskWarningPage;

    public RiskWarningSteps() {
        this.riskWarningPage = new RiskWarningPage(Hook.getDriver());
    }

    @When("User clicks {string} link in the {string} text block at the Risk Warning bottom")
    public void clickLinkInTheTextBlockAtTheBottom(String linkText, String textBlock) {
        riskWarningPage.clickTextLink(textBlock, linkText);
    }

    @Then("Risk Warning page is loaded")
    public void riskWarningPageIsLoaded() {
        assertThat(riskWarningPage.isPageLoaded())
                .as("Risk Warning page is not loaded")
                .isTrue();
    }

}
