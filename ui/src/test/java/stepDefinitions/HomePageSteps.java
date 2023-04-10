package stepDefinitions;

import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageActions.HomePage;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePageSteps {

    public static final String MAX = "Maximum";
    public static final String SPLITTER = "x";
    private final HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage(Hook.getDriver());
    }

    @When("User navigates Home page")
    public void navigateHomePage() {
        homePage.navigateHomePage();
    }

    @When("User updates browser with browser’s screen resolution {string} settings")
    public void updateBrowserWithBrowserScreenResolutionSettings(String resolutionSetting) {
        if (resolutionSetting.equals(MAX)) {
            homePage.maximizeWindow();
        } else {
            String[] splittedString = resolutionSetting.split(SPLITTER);
            homePage.setWindowSize(parseInt(splittedString[0]), parseInt(splittedString[1]));
        }
    }

    @When("User accepts cookies settings")
    public void acceptCookiesSettings() {
        if (homePage.isCookieModalDisplayed()) {
            homePage.acceptCookies();
        }
    }

    @When("User clicks the {string} link at the top menu")
    public void clicksTheLinkAtTheTopMenu(String linkText) {
        if (homePage.isExpandTopMenuButtonDisplayed()) {
            homePage.clickExpandTopMenuButton();
            homePage.clickMenuLink(linkText);
        } else {
            homePage.clickMenuLink(linkText.toUpperCase());
        }
    }

    @When("User clicks {string} link in the {string} section")
    public void clickLinkInTheSection(String linkText, String section) {
        if (homePage.isExpandTopMenuButtonDisplayed()) {
            homePage.clickToggledSectionLink(section, linkText);
        } else {
            homePage.clickTopSectionLink(section, linkText);
        }
    }

    @Then("Home Page is loaded")
    public void homePageIsLoaded() {
        assertThat(homePage.isPageLoaded())
                .as("Home page is not loaded")
                .isTrue();
    }

    @Then("The top menu {string} block is displayed")
    public void theTopMenuBlockIsDisplayed(String linkText) {
        if (homePage.isExpandTopMenuButtonDisplayed()) {
            assertThat(homePage.isToggledMenuBlockDisplayed(linkText))
                    .as("Toggled menu %s block is not displayed", linkText)
                    .isTrue();
        } else {
            assertThat(homePage.isTopMenuBlockDisplayed(linkText.toUpperCase()))
                    .as("Top menu %s block is not displayed", linkText)
                    .isTrue();

        }
    }

}
