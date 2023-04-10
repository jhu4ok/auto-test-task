package stepDefinitions;

import data.SliderPositions;
import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import pageActions.EconomicCalendarPage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import static data.SliderPositions.getSliderPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.DateUtil.*;

public class EconomicCalendarSteps {

    private final EconomicCalendarPage economicCalendarPage;
    private int currentSliderPosition = 0;

    public EconomicCalendarSteps() {
        this.economicCalendarPage = new EconomicCalendarPage(Hook.getDriver());
    }

    @When("User selects {string} on Economic Calendar slider and")
    public void selectOnEconomicCalendarSliderAnd(String sliderValue) {
        economicCalendarPage.switchToCalendarFrame();
        SliderPositions targetSliderPosition = getSliderPosition(sliderValue);
        economicCalendarPage.selectSliderValue(currentSliderPosition, targetSliderPosition);
        currentSliderPosition = targetSliderPosition.getPosition();
        economicCalendarPage.switchToDefaultContent();
    }

    @When("User clicks {string} link in the {string} text block at the Economic Calendar bottom")
    public void clickLinkInTheTextBlockAtTheBottom(String linkText, String textBlock) {
        economicCalendarPage.clickTextLink(textBlock, linkText);
    }

    @Then("Economic Calendar page is loaded")
    public void economicCalendarPageIsLoaded() {
        assertThat(economicCalendarPage.isPageLoaded())
                .as("Economic Calendar page is not loaded")
                .isTrue();
    }

    @Then("Economic Calendar displays {string} selected date")
    public void economicCalendarDisplaysSelectedDate(String sliderValue) {
        economicCalendarPage.switchToCalendarFrame();
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(economicCalendarPage.getSliderText())
                .as("Slider text is unexpected")
                .isEqualTo(sliderValue);
        switch (getSliderPosition(sliderValue)) {
            case RECENT_AND_NEXT -> {
                softAssert.assertThat(economicCalendarPage.isCalendarDateActive(getTodayDate().getDayOfMonth()))
                        .as("Calendar's Today day is not active")
                        .isTrue();
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(getTodayDate()));
            }
            case TODAY -> {
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(getTodayDate().getDayOfMonth()))
                        .as("Calendar's Today day is not selected")
                        .isTrue();
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(getTodayDate()));
            }
            case TOMORROW -> {
                softAssert.assertThat(
                                economicCalendarPage.isCalendarDateSelected(getDayAfterDate(getTodayDate(), 1).getDayOfMonth()))
                        .as("Calendar's Tomorrow day %s is not selected")
                        .isTrue();
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(getDayAfterDate(getTodayDate(), 1)));
            }
            case THIS_WEEK -> {
                LocalDate firstThisWeekDay = getThisWeekBeginning();
                LocalDate lastThisWeekDay = getDayAfterDate(getThisWeekBeginning(), 7);
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(firstThisWeekDay));
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(firstThisWeekDay.getDayOfMonth()))
                        .as("Calendar's This Week first day %s is not selected", firstThisWeekDay.getDayOfMonth())
                        .isTrue();
                if (!firstThisWeekDay.getMonth().equals(lastThisWeekDay.getMonth())) {
                    economicCalendarPage.clickNextMonthButton();
                }
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(lastThisWeekDay.getDayOfMonth()))
                        .as("Calendar's This Week last day %s is not selected", lastThisWeekDay.getDayOfMonth())
                        .isTrue();
            }
            case NEXT_WEEK -> {
                LocalDate firstNextWeekDay = getDayAfterDate(getThisWeekBeginning(), 7);
                LocalDate lastNextWeekDay = getDayAfterDate(getThisWeekBeginning(), 13);
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(firstNextWeekDay));
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(firstNextWeekDay.getDayOfMonth()))
                        .as("Calendar's Next Week first day %s is not selected", firstNextWeekDay.getDayOfMonth())
                        .isTrue();
                if (!firstNextWeekDay.getMonth().equals(lastNextWeekDay.getMonth())) {
                    economicCalendarPage.clickNextMonthButton();
                }
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(lastNextWeekDay.getDayOfMonth()))
                        .as("Calendar's Next Week last day %s is not selected", lastNextWeekDay.getDayOfMonth())
                        .isTrue();
            }
            case THIS_MONTH -> {
                YearMonth thisMonth = getMonth(getTodayDate());
                LocalDate firstThisMonthDay = thisMonth.atDay(1);
                LocalDate lastThisMonthDay = thisMonth.atEndOfMonth();
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(firstThisMonthDay));
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(firstThisMonthDay.getDayOfMonth()))
                        .as("Calendar's This Month first day %s is not selected", firstThisMonthDay.getDayOfMonth())
                        .isTrue();
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(lastThisMonthDay.getDayOfMonth()))
                        .as("Calendar's This Month last day %s is not selected", lastThisMonthDay.getDayOfMonth())
                        .isTrue();
            }
            case NEXT_MONTH -> {
                YearMonth nextMonth = getMonthAfterDate(getTodayDate(), 1);
                LocalDate firstNextMonthDay = nextMonth.atDay(1);
                LocalDate lastNextMonthDay = nextMonth.atEndOfMonth();
                softAssert.assertThat(economicCalendarPage.getCalendarMonthText())
                        .as("Calendar's month is unexpected")
                        .isEqualTo(getMonthName(firstNextMonthDay));
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(firstNextMonthDay.getDayOfMonth()))
                        .as("Calendar's Next Month first day %s  is not selected", firstNextMonthDay.getDayOfMonth())
                        .isTrue();
                softAssert.assertThat(economicCalendarPage.isCalendarDateSelected(lastNextMonthDay.getDayOfMonth()))
                        .as("Calendar's Next Month last day %s is not selected", lastNextMonthDay.getDayOfMonth())
                        .isTrue();
            }
            default -> throw new IllegalArgumentException("Unsupported sliderValue value: " + sliderValue);
        }
        softAssert.assertAll();
        economicCalendarPage.switchToDefaultContent();
    }

    @Then("{string} document was opened in {int} tab")
    public void documentWasOpenedInNewTab(String expectedText, int expectedTabCount) {
        SoftAssertions softAssert = new SoftAssertions();
        ArrayList<String> tabs = economicCalendarPage.getWindowTabs();
        softAssert.assertThat(tabs.size())
                .as("Tabs count is unexpected")
                .isEqualTo(expectedTabCount);
        economicCalendarPage.switchToWindow(tabs.get(1));
        softAssert.assertThat(economicCalendarPage.getCurrentUrl())
                .as("New tab's title is unexpected")
                .contains(expectedText);
        softAssert.assertAll();
    }

}
