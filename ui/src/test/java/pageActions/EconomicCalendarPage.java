package pageActions;

import data.SliderPositions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.EconomicCalendarPO;

import static data.SliderPositions.RECENT_AND_NEXT;
import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class EconomicCalendarPage extends BasePage {

    public static final double SLIDER_STEP = 0.16;
    private final EconomicCalendarPO economicCalendarPO = new EconomicCalendarPO();

    public EconomicCalendarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        switchToFrame(economicCalendarPO.getCalendarFrame());
        boolean elementPresent = isElementPresent(waiter, economicCalendarPO.getCalendarContainer());
        switchToDefaultContent();
        return elementPresent;
    }

    public boolean isCalendarButtonDisplayed() {
        return isElementDisplayed(economicCalendarPO.getCalendarNotHighlightedButton());
    }

    public void selectSliderValue(int initialSliderPosition, SliderPositions targetSliderPosition) {
        if (isCalendarButtonDisplayed()) {
            clickCalendarButton();
            waiter.until(invisibilityOfElementLocated(economicCalendarPO.getCalendarNotHighlightedButton()));
            scrollIntoCenterView(findElement(economicCalendarPO.getSliderText()));
        }
        WebElement slider = waiter.until(visibilityOfElementLocated(economicCalendarPO.getSliderContainer()));
        int xOffset = getXOffset(initialSliderPosition, targetSliderPosition, slider);
        WebElement sliderThumb = waiter.until(elementToBeClickable(economicCalendarPO.getSliderThumb()));
        dragAndDropBy(xOffset, sliderThumb);
    }

    private static int getXOffset(int initialSliderPosition, SliderPositions targetSliderPosition, WebElement slider) {
        if (targetSliderPosition.equals(RECENT_AND_NEXT)) {
            return (int) -((initialSliderPosition * SLIDER_STEP) * slider.getSize().getWidth());
        } else {
            return (int) (((targetSliderPosition.getPosition() - initialSliderPosition) * SLIDER_STEP) *
                    slider.getSize().getWidth());
        }
    }

    public void clickCalendarButton() {
        clickOn(economicCalendarPO.getCalendarNotHighlightedButton());
    }

    public void clickNextMonthButton() {
        clickOn(economicCalendarPO.getCalendarNextMonthButton());
    }

    public void clickTextLink(String textBlock, String linkText) {
        clickOnUsingJS(xpath(format(economicCalendarPO.getTextBlockLink(), textBlock, linkText)));
    }

    public void switchToCalendarFrame() {
        switchToFrame(economicCalendarPO.getCalendarFrame());
    }

    public String getSliderText() {
        return getElementText(economicCalendarPO.getSliderText());
    }

    public String getCalendarMonthText() {
        return getElementText(economicCalendarPO.getCalendarMonth());
    }

    public boolean isCalendarDateActive(int date) {
        WebElement dateElement = findElement(xpath(format(economicCalendarPO.getCalendarDate(), date)));
        return dateElement.getAttribute(CLASS).contains(ACTIVE);
    }

    public boolean isCalendarDateSelected(int date) {
        WebElement dateElement = findElement(xpath(format(economicCalendarPO.getCalendarDate(), date)));
        return Boolean.parseBoolean(dateElement.getAttribute(ARIA_PRESSED));
    }

}
