package org.daiapi.Util;

import org.daiapi.Models.EventModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    private Constants() {} // no way to instantiate this class
    public static final String MY_VAL = "123";

    static EventModel event1 =  new EventModel("1","12-11-2022", "Dr.X", "Pittsburgh", "ClientA", "Visit 2");
    static EventModel event2 =  new EventModel("2","10-11-2022", "Dr.X", "Pittsburgh", "ClientB", "Visit 3");
    static EventModel event3 =  new EventModel("3","11-16-2022", "Dr.X", "Pittsburgh", "ClientC", "Visit 1");
    static EventModel event4 =  new EventModel("4","11-16-2022", "Dr.X", "Pittsburgh", "ClientB", "Visit 4");

    static EventModel[] eventsList = {event1, event2, event3, event4};
    public static List<EventModel> EVENT_LIST = new ArrayList<EventModel>(Arrays.asList(eventsList));

    public static List<String> supportedDateFormats = List.of("dd-MM-yyyy", "MM-dd-yyyy", "EEE, MMM d, ''yy");
    public static List<String> supportedDayFormats = List.of("dd", "E", "d", "EE");

    public static List<String> supportedMonthFormats = List.of("M", "MMM");

    public static List<String> supportedYearFormats = List.of("yyyy", "yy");
}
