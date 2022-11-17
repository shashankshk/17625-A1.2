package org.daiapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.daiapi.Models.EventModel;
import org.daiapi.Util.Constants;
import org.junit.jupiter.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.net.URI;
import java.util.List;

public class EventsAPITest {

    private static ObjectMapper objectMapperObj;
    @BeforeAll
    public static void setUpAll() {
        objectMapperObj = new ObjectMapper();
        Main.runMainServer(8081);

    }

    // Test to check if events are fetched for a given date
    @Order(11)
    @Test
    public void getEventsForAGivenDate() throws JsonProcessingException {
        // Preparing data for test
        List<EventModel> expectedAnswer = Constants.EVENT_LIST.stream().filter(event -> event.date.equals("11-16-2022")).toList();
        String jsonFormattedString = objectMapperObj.writeValueAsString(expectedAnswer);
        given()
                .baseUri("http://localhost:8081")
                .with()
                .queryParams("date","11-16-2022")
                .contentType(ContentType.JSON)
                .when()
                .get(URI.create("/events/date"))
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo(jsonFormattedString));
    }

    // Test to check if error is thrown if request body is invalid
    @Order(12)
    @Test
    public void getEventsForAGivenDateError() {
        given()
                .baseUri("http://localhost:8081")
                .with()
                .params("date","invalidDate")
                .when()
                .get(URI.create("/events/date"))
                .then()
                .statusCode(200);
    }

    // Test to check if event with given id is retrieved or not
    @Order(13)
    @Test
    public void getEventWithEventId() throws JsonProcessingException {
        String jsonFormattedString = objectMapperObj.writeValueAsString(Constants.EVENT_LIST.get(2));
        given()
                .baseUri("http://localhost:8081")
                .with()
                .contentType(ContentType.JSON)
                .when()
                .get(URI.create("/events/4"))
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo(jsonFormattedString));
    }

    // Test to check if error is thrown in case wrong Id is sent
    @Order(14)
    @Test
    public void getEventWithEventIdError() {
        given()
                .baseUri("http://localhost:8081")
                .when().with().params("eventId", "22")
                .get(URI.create("/events/22")).then()
                .statusCode(204);
    }

    // Test to check if event is added successfully or not
    @Order(15)
    @Test
    public void addEvent() throws JsonProcessingException {
        // matches response with json in addEvent.json file
        EventModel eventToAdd = new EventModel(null , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        EventModel eventExpected = new EventModel("5" , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        given()
                .baseUri("http://localhost:8081")
                .with()
                .body(objectMapperObj.writeValueAsString(eventToAdd))
                .contentType(ContentType.JSON)
                .when()
                .post(URI.create("/events/event"))
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo(objectMapperObj.writeValueAsString(eventExpected)));
    }

    // Test to check if 400 response code is received for bad request object
    @Order(16)
    @Test
    public void addEventError() throws JsonProcessingException {
        EventModel eventToAdd = new EventModel(null , "11-16-2023", null, "Pittsburgh", "ClientB", "Visit 4");
        EventModel eventExpected = new EventModel("5" , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        given()
                .baseUri("http://localhost:8081")
                .with()
                .body(objectMapperObj.writeValueAsString(eventToAdd))
                .contentType(ContentType.JSON)
                .when()
                .post(URI.create("/events/event")).then()
                .statusCode(400);
    }

    // Test to check if a given event is modified or not
    @Order(17)
    @Test
    public void modifyEvent() throws JsonProcessingException {
        EventModel eventToAdd = new EventModel("3" , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        EventModel eventExpected = new EventModel("3" , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        given()
                .baseUri("http://localhost:8081")
                .with()
                .body(objectMapperObj.writeValueAsString(eventToAdd))
                .contentType(ContentType.JSON)
                .when()
                .put(URI.create("/events/3"))
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo(objectMapperObj.writeValueAsString(eventExpected)));
    }

    // Test to check if response code is 204 if wrong event is tried to modified
    @Order(18)
    @Test
    public void modifyEventError() throws JsonProcessingException {
        EventModel eventToAdd = new EventModel("22" , "11-16-2023", "Dr.Y", "Pittsburgh", "ClientB", "Visit 4");
        given()
                .baseUri("http://localhost:8081")
                .with()
                .body(objectMapperObj.writeValueAsString(eventToAdd))
                .contentType(ContentType.JSON)
                .when()
                .put(URI.create("/events/22")).then()
                .statusCode(400);
    }

    // Test to check if an event is deleted successfully or not
    @Order(19)
    @Test
    public void deleteEvent() {
        given()
                .baseUri("http://localhost:8081")
                .with()
                .contentType(ContentType.JSON)
                .when()
                .delete(URI.create("/events/1")).then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    // Test to check if Response code is 400 for bad request
    @Order(20)
    @Test
    public void deleteEventError() {
        given()
                .baseUri("http://localhost:8081")
                .with()
                .contentType(ContentType.JSON)
                .when()
                .delete(URI.create("/events/7")).then()
                .statusCode(204);
    }


}


