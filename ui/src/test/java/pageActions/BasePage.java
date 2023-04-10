package pageActions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {

    protected static final String CLASS = "class";
    protected static final String ACTIVE = "active";
    protected static final String ARIA_PRESSED = "aria-pressed";
    protected WebDriver driver;
    protected WebDriverWait waiter;
    private final JavascriptExecutor executor;
    private final Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.executor = ((JavascriptExecutor) this.driver);
        this.action = new Actions(driver);
    }

    public abstract boolean isPageLoaded();

    public ArrayList<String> getWindowTabs() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    public WebElement findElement(By elementLocator) {
        return driver.findElement(elementLocator);
    }

    public String getElementText(By elementLocator) {
        return findElement(elementLocator).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isElementDisplayed(By elementLocator) {
        try {
            return driver.findElement(elementLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementDisplayed(WebDriverWait wait, By elementLocator) {
        try {
            wait.until(visibilityOfElementLocated(elementLocator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementPresent(WebDriverWait wait, By elementLocator) {
        try {
            wait.until(presenceOfElementLocated(elementLocator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public void clickOn(By elementLocator) {
        clickOn(findElement(elementLocator));
    }

    public void clickOn(WebElement element) {
        element.click();
    }

    public void clickOn(WebDriverWait wait, By elementLocator) {
        wait.until(elementToBeClickable(elementLocator)).click();
    }

    public void clickOnUsingJS(By elementLocator) {
        executor.executeScript("arguments[0].click();", findElement(elementLocator));
    }

    public void switchToWindow(String windowName) {
        driver.switchTo().window(windowName);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToFrame(By elementLocator) {
        driver.switchTo().frame(findElement(elementLocator));
    }

    public void dragAndDropBy(int xOffset, WebElement initialElement) {
        action.moveToElement(initialElement).pause(500).dragAndDropBy(initialElement, xOffset, 0).perform();
    }

    public void scrollIntoCenterView(WebElement element) {
        executor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);
    }

    public void scrollIntoView(WebElement element) {
        executor.executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 4);", element);
    }

}
