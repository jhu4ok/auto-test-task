package pageObjects;

import lombok.Getter;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

@Getter
public class EconomicCalendarPO {

    public final By calendarContainer = cssSelector("[class*='tc-economic-calendar-item-header']");
    public final By calendarNotHighlightedButton =
            xpath("//span[contains(@class,'tc-calendar-button') and not(contains(@class, 'highlight'))]");
    public final By calendarFrame = id("iFrameResizer0");
    public final By calendarMonth = cssSelector("[class*='mat-calendar-body-label']");
    public final By calendarNextMonthButton = cssSelector("[aria-label='Next month']");
    public final String calendarDate =
            "//div[contains(@class, 'mat-calendar-body-cell-content') and normalize-space()='%d']/ancestor::button";
    public final By sliderContainer = cssSelector("[class='mat-slider-wrapper']");
    public final By sliderThumb = cssSelector("[class='mat-slider-thumb']");
    public final By sliderText = cssSelector("span[class='tc-finalval-tmz']");
    public final String textBlockLink = "//p[contains(text(), '%s')]/..//a[text()='%s']";

}
