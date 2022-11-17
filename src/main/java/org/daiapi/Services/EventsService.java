package org.daiapi.Services;

import org.daiapi.Models.EventModel;
import org.daiapi.Util.Constants;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EventsService {

    /**
     * Retrieves event based on ID
     * @param eventId for which data needs to be fetched
     * @return event object
     */
    public  EventModel getEventById(String eventId) {
        // Validating if id passed is null
        if(eventId == null) {
            throw new RuntimeException("EventId passed is incorrect");
        }
        List<EventModel> filteredList = Constants.EVENT_LIST.stream().filter(event -> event.id.equals(eventId)).toList();
        System.out.println(filteredList + eventId);
        if(filteredList.size() != 0) return filteredList.get(0);
        return null;
    }

    /**
     * Method to add an event
     * @param eventObject which has to be added
     * @param res spark response object
     * @return event object which is successfully added
     */
    public  EventModel addEvent(EventModel eventObject, Response res) {
        if(eventObject.practitioner == null
                || eventObject.location == null
                || eventObject.detail == null
                || eventObject.name == null
        ) {
            return null;
        }
        System.out.println("this called");
        int newEventIdInt = Integer.parseInt(Constants.EVENT_LIST.get(Constants.EVENT_LIST.size()-1).getId()) +1;
        System.out.println(newEventIdInt);
        String eventId  = String.valueOf(newEventIdInt);
        eventObject.setId(eventId);
        Constants.EVENT_LIST.add(eventObject);
        return eventObject;
    }

    /**
     * Updates an event that exists
     * @param updatedEvent updated event object for the event
     * @return updated event object
     */
    public  EventModel updateEvent(EventModel updatedEvent) {
        if(updatedEvent.id == null) {
            throw new RuntimeException("Event id passed is incorrect");
        }
        EventModel eventToUpdate = getEventById(updatedEvent.id);
        if(eventToUpdate == null) {
            throw new RuntimeException("Event id passed is incorrect");
        }
        if(updatedEvent.date != null) {
            eventToUpdate.setEventDate(updatedEvent.date);
        }
        if(updatedEvent.practitioner != null) {
            eventToUpdate.setPractitioner(updatedEvent.practitioner);
        }
        if(updatedEvent.detail != null) {
            eventToUpdate.setDetail(updatedEvent.detail);
        }
        if(updatedEvent.name != null) {
            eventToUpdate.setName(updatedEvent.name);
        }
        return eventToUpdate;
    }

    /**
     * Deletes an event
     * @param eventId id to be deleted
     * @return 1 if successfully deleted else -1
     */
    public  int deleteEvent(String eventId) {
        System.out.println(eventId + "delete");
        if(eventId == null) {
            throw new RuntimeException("Event id passed is incorrect");
        }

        EventModel eventToDelete = getEventById(eventId);
        if(eventToDelete == null) {
            return -1;
        }
        int index = getIndex(eventId);
        if(index != -1) {
            Constants.EVENT_LIST.remove(index);
            return 1;
        }
        return -1;
    }

    /**
     * Fetches events by date
     * @param date  for which events need to be fetched
     * @return list of events
     */
    public  List<EventModel> getEventsByDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        if(date == null) {
            throw new RuntimeException("Date passed is incorrect");
        }
//        try {
//            LocalDate.parse(date, dateFormatter);
//        } catch (DateTimeParseException e) {
//            throw new RuntimeException("Date passed is incorrect");
//        }
        System.out.println("called this" + date);
        List<EventModel> eventsList = Constants.EVENT_LIST.stream().filter(event -> event.getEventDate().equals(date)).toList();
        return eventsList;
    }

    /**
     * gets Index of event being searched
     * @param eventId
     * @return index if event exists else -1
     */
    public  int getIndex(String eventId) {
        for(int i = 0; i<Constants.EVENT_LIST.size(); i++) {
            if(eventId.equals(Constants.EVENT_LIST.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}
