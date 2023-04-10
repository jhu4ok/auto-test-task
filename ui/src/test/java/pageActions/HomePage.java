package pageActions;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.HomePagePO;
import utils.ConfigsReader;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

@Getter
public class HomePage extends BasePage {

    public static final String URL = ConfigsReader.get().value("base.url");
    private final HomePagePO homePagePO = new HomePagePO();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return isElementDisplayed(waiter, homePagePO.getMainNavigation());
    }

    public boolean isCookieModalDisplayed() {
        return isElementDisplayed(waiter, homePagePO.getCookieModal());
    }

    public boolean isTopMenuBlockDisplayed(String linkText) {
        return isElementDisplayed(xpath(format(homePagePO.getTopMenuDropDownBlock(), linkText)));
    }

    public boolean isToggledMenuBlockDisplayed(String linkText) {
        WebElement element = findElement(xpath(format(homePagePO.getToggledMenuDropDownBlock(), linkText)));
        scrollIntoView(element);
        return isElementDisplayed(element);
    }

    public boolean isExpandTopMenuButtonDisplayed() {
        return isElementDisplayed(homePagePO.getToggleTopMenuButton());
    }

    public void navigateHomePage() {
        getPage(URL);
    }

    public void acceptCookies() {
        clickOn(homePagePO.getAcceptCookiesButton());
    }

    public void clickMenuLink(String linkText) {
        clickOnUsingJS(xpath(format(homePagePO.getMenuLink(), linkText)));
    }

    public void clickExpandTopMenuButton() {
        clickOn(homePagePO.getToggleTopMenuButton());
    }

    public void clickToggledSectionLink(String section, String linkText) {
        clickOn(waiter, xpath(format(homePagePO.getToggledMenuSectionLink(), section, linkText)));
    }

    public void clickTopSectionLink(String section, String linkText) {
        clickOn(waiter, xpath(format(homePagePO.getTopMenuSectionLink(), section, linkText)));
    }

}
