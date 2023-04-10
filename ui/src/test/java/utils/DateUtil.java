package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class DateUtil {

    public static LocalDate getTodayDate() {
        return LocalDate.now();
    }

    public static LocalDate getDayAfterDate(LocalDate date, int daysAfterDate) {
        return date.plusDays(daysAfterDate);
    }

    public static LocalDate getThisWeekBeginning() {
        LocalDate date = LocalDate.now();
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.minusDays(1);
        }
        return date;
    }

    public static YearMonth getMonth(LocalDate date) {
        return YearMonth.from(date);
    }

    public static YearMonth getMonthAfterDate(LocalDate date, int monthsAfterDate) {
        return YearMonth.from(date).plusMonths(monthsAfterDate);
    }

    public static String getMonthName(LocalDate date) {
        return getMonth(date).getMonth().name().substring(0, 3);
    }

}
