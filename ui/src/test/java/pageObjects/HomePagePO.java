package pageObjects;

import lombok.Getter;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

@Getter
public class HomePagePO {

    private final By mainNavigation = id("main-nav");
    private final By cookieModal = id("cookieModal");
    private final By acceptCookiesButton = cssSelector("[class*='acceptDefaultCookieFirstVisit']");
    private final String menuLink = "//a[normalize-space()='%s']";
    private final String topMenuDropDownBlock = menuLink + "//ancestor::li//div[@class='dropdown']";
    private final String topMenuSectionLink =
            "//span[normalize-space()='%s']//following-sibling::ul//a[normalize-space()='%s']";
    private final String toggledMenuDropDownBlock = menuLink + "//ancestor::li//div[@aria-expanded='true']";
    private final By toggleTopMenuButton = className("toggleLeftNav");
    private final String toggledMenuSectionLink = "//li[text()='%s']//following-sibling::li/a[normalize-space()='%s']";

}
