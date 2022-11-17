package org.daiapi;
import org.daiapi.Controllers.DatesController.DatesController;
import org.daiapi.Controllers.EventController.EventsController;
import org.daiapi.Controllers.TimezoneController.TimezoneController;
import spark.Service;

import static spark.Service.ignite;
import static spark.Spark.*;
public class Main {

    public static void main(String[] args) {
        // Starting server
        runMainServer(4567);
    }

    /**
     * Method to ignite the spark server
     * @param port on which the server will be started
     */
    public static void runMainServer(int port) {
        Service http = ignite()
                .port(port);
        // Endpoints to handle date API
        http.get("/date/currentDate",       DatesController.CurrentDateController);
        http.get("/date/currentDay",       DatesController.CurrentDayController);
        http.get("/date/currentMonth",       DatesController.CurrentMonthController);
        http.get("/date/currentYear",       DatesController.CurrentYearController);

        // Endpoints to handle events API
        http.get("/events/date",       EventsController.EventByDateController);
        http.post("/events/event",       EventsController.AddEventController);
        http.get("/events/:eventId",       EventsController.EventByIdController);
        http.put("/events/:eventId",       EventsController.UpdateEventController);
        http.delete("/events/:eventId",       EventsController.DeleteEventController);

        // Endpoints to handle timezones API
        http.get("/timeZones", TimezoneController.TimezoneListController);
        http.after((request, response) -> {
            response.type("application/json");
        });
    }
}