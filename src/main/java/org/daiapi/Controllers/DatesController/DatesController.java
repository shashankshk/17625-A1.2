package org.daiapi.Controllers.DatesController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.daiapi.Models.DateModel;
import org.daiapi.Models.DayModel;
import org.daiapi.Models.MonthModel;
import org.daiapi.Models.YearModel;
import org.daiapi.Services.DatesService;
import org.daiapi.Util.Constants;
import spark.Request;
import spark.Response;
import spark.Route;

//import java.util.Date;

public class DatesController {
    private static ObjectMapper objectMapper;
    /**
     * Route to handle current date
     */
    public static Route CurrentDateController = (Request req, Response res) -> {
        try{
            objectMapper = new ObjectMapper();
            // Evaluating current date using query params
            String returnCurrentDate = DatesService.getCurrentDate(req.queryParams("format"),req.queryParams("timeZone"));
            res.status(200);
            DateModel dateObject = new DateModel(returnCurrentDate);
            String returnValue = objectMapper.writeValueAsString(dateObject);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Route to handle current day
     */
    public static Route CurrentDayController = (Request req, Response res) -> {
        try{
            objectMapper = new ObjectMapper();
            // Evaluating current day using query params
            String returnCurrentDay = DatesService.getCurrentDay(req.queryParams("format"), req.queryParams("timeZone"));
            DayModel dayObject = new DayModel(returnCurrentDay);
            String returnValue = objectMapper.writeValueAsString(dayObject);
            res.status(200);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Route to handle current month
     */
    public static Route CurrentMonthController = (Request req, Response res) -> {
        try{
            objectMapper = new ObjectMapper();
            // Evaluating current month using query params
            String returnCurrentMonth = DatesService.getCurrentMonth(req.queryParams("format"), req.queryParams("timeZone"));
            MonthModel monthObject = new MonthModel(returnCurrentMonth);
            String returnValue = objectMapper.writeValueAsString(monthObject);
            res.status(200);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Route to handle current year
     */
    public static Route CurrentYearController = (Request req, Response res) -> {
        try{
            objectMapper = new ObjectMapper();
            // Evaluating current year using query params
            String returnCurrentMonth = DatesService.getCurrentYear(req.queryParams("format"), req.queryParams("timeZone"));
            YearModel yearObject = new YearModel(returnCurrentMonth);
            String returnValue = objectMapper.writeValueAsString(yearObject);
            res.status(200);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };
}
