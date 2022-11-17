package org.daiapi.Services;

import org.daiapi.Util.Constants;
import spark.Request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatesService {

    private DatesService(){

    }

    /**
     * Returns current date based on the format and timezone
     *
     * @param formatRequired in which the response is required
     * @param timeZoneRequired in which the date is required
     * @return current date
     */
    public static String getCurrentDate(String formatRequired, String timeZoneRequired){
        System.out.println(formatRequired+timeZoneRequired);
        if(formatRequired == null || !Constants.supportedDateFormats.contains(formatRequired)) {
            throw new RuntimeException("Date format is unsupported");
        }
        if(timeZoneRequired == null || !isValidTimezone(timeZoneRequired)){
            throw new RuntimeException("TimeZone is not supported");
        }
        DateFormat requiredDateFormat = new SimpleDateFormat(formatRequired);
        requiredDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneRequired));
        String currentDate = requiredDateFormat.format(new Date());
        return currentDate;
    }

    /**
     * Returns current day based on the format and timezone
     *
     * @param formatRequired in which the response is required
     * @param timeZoneRequired in which the day is required
     * @return current day
     */
    public static String getCurrentDay(String formatRequired, String timeZoneRequired){
        System.out.println(formatRequired+timeZoneRequired);
        if(formatRequired == null || !Constants.supportedDayFormats.contains(formatRequired)) {
            throw new RuntimeException("Day format is unsupported");
        }
        if(timeZoneRequired == null || !isValidTimezone(timeZoneRequired)){
            throw new RuntimeException("TimeZone is not supported");
        }
        DateFormat requiredDayFormat = new SimpleDateFormat(formatRequired);
        requiredDayFormat.setTimeZone(TimeZone.getTimeZone(timeZoneRequired));
        String currentDay = requiredDayFormat.format(new Date());
        return currentDay;
    }

    /**
     * Returns current month based on the format and timezone
     *
     * @param formatRequired in which the response is required
     * @param timeZoneRequired in which the month is required
     * @return current month
     */
    public static String getCurrentMonth(String formatRequired, String timeZoneRequired){
        System.out.println(formatRequired+timeZoneRequired);
        if(formatRequired == null || !Constants.supportedMonthFormats.contains(formatRequired)) {
            throw new RuntimeException("Day format is unsupported");
        }
        if(timeZoneRequired == null || !isValidTimezone(timeZoneRequired)){
            throw new RuntimeException("TimeZone is not supported");
        }
        DateFormat requiredMonthFormat = new SimpleDateFormat(formatRequired);
        requiredMonthFormat.setTimeZone(TimeZone.getTimeZone(timeZoneRequired));
        String currentMonth = requiredMonthFormat.format(new Date());
        return currentMonth;
    }

    /**
     * Returns current year based on the format and timezone
     *
     * @param formatRequired in which the response is required
     * @param timeZoneRequired in which the year is required
     * @return current year
     */
    public static String getCurrentYear(String formatRequired, String timeZoneRequired){
        System.out.println(formatRequired+timeZoneRequired);
        if(formatRequired == null || !Constants.supportedYearFormats.contains(formatRequired)) {
            throw new RuntimeException("Day format is unsupported");
        }
        if(timeZoneRequired == null || !isValidTimezone(timeZoneRequired)){
            throw new RuntimeException("TimeZone is not supported");
        }
        DateFormat requiredYearFormat = new SimpleDateFormat(formatRequired);
        requiredYearFormat.setTimeZone(TimeZone.getTimeZone(timeZoneRequired));
        String currentYear = requiredYearFormat.format(new Date());
        return currentYear;
    }

    /**
     * Tels is timezone is valid
     * @param timeZOne which is being checked
     * @return true or false
     */
    public static boolean isValidTimezone (String timeZOne) {
        return Set.of(TimeZone.getAvailableIDs()).contains(timeZOne);
    }
}
