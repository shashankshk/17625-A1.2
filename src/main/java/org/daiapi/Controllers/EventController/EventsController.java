package org.daiapi.Controllers.EventController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.daiapi.Models.EventModel;
import org.daiapi.Services.EventsService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class EventsController {
    private static ObjectMapper objectMapper;

    /**
     * Controller to handle events by id request
     */
    public static Route EventByIdController = (Request req, Response res) -> {
        try{
            EventsService eventService = new EventsService();
            objectMapper = new ObjectMapper();
            EventModel eventReturned = eventService.getEventById(req.params("eventId"));
            if(eventReturned == null) {
                res.status(204);
                return "[]";
            }
            res.status(200);
            String returnValue = objectMapper.writeValueAsString(eventReturned);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Controller to handle events by date requests
     */
    public static Route EventByDateController = (Request req, Response res) -> {
        try{
            EventsService eventService = new EventsService();
            objectMapper = new ObjectMapper();
            List<EventModel> eventList = eventService.getEventsByDate(req.queryParams("date"));
            res.status(200);
            String listValue = objectMapper.writeValueAsString(eventList);
            res.body(listValue);
            return listValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Controller to handle update event requests
     */
    public static Route UpdateEventController = (Request req, Response res) -> {
        try{
            EventsService eventService = new EventsService();
            objectMapper = new ObjectMapper();
            EventModel eventToUpdate = eventService.updateEvent(objectMapper.readValue(req.body(), EventModel.class));
            res.status(200);
            String returnValue = objectMapper.writeValueAsString(eventToUpdate);
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Controller to handle delete event requests
     */
    public static Route DeleteEventController = (Request req, Response res) -> {
        try{
            EventsService eventService = new EventsService();
            objectMapper = new ObjectMapper();
            int deleted = eventService.deleteEvent(req.params("eventId"));
            String deletedResponse = String.valueOf(deleted);
            if(deleted == -1) {
                res.body("Id not found");
                res.status(204);
                return "Id not found";
            }
            res.status(200);
            res.body(deletedResponse);
            return deletedResponse;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };

    /**
     * Controller to handle add event requests
     */
    public static Route AddEventController = (Request req, Response res) -> {
        try{
            EventsService eventService = new EventsService();
            objectMapper = new ObjectMapper();
            EventModel eventAdded = eventService.addEvent(objectMapper.readValue(req.body(), EventModel.class), res);
            if(eventAdded == null){
                res.status(400);
                return "";
            }
            res.status(200);

            String addResponse = objectMapper.writeValueAsString(eventAdded);
            res.body(addResponse);
            return addResponse;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
    };
}
