package org.daiapi;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DatesAPITest {

    private static ObjectMapper objectMapperObj;

    @BeforeAll
    public static void setUp() {
        objectMapperObj = new ObjectMapper();
        Main.runMainServer(8080);
    }

    // Test to check if current date is retrieved successfully or not
    @Order(1)
    @Test
    public void CurrentDateController() throws JsonProcessingException {
        // preparing test data
        DateFormat requiredDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        requiredDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String currentDate = requiredDateFormat.format(new Date());
        given()
            .baseUri("http://localhost:8080")
            .with()
            .queryParams("timeZone","America/New_York",
            "format", "MM-dd-yyyy")
            .contentType(ContentType.JSON)
            .when()
            .get(URI.create("/date/currentDate"))
            .then()
            .assertThat()
            .statusCode(200)
            .body("currentDate", equalTo(currentDate));
    }

    // Test to see if 400 status is response code for invalid timezone input
    @Order(2)
    @Test
    public void getCurrentDateError() {
        String testDate = "11-09-2022";
        given()
            .baseUri("http://localhost:8080")
            .with().params("timeZone","invalidTimeZone",
                    "format", "mm-dd-yyyy")
            .when()
            .get(URI.create("/date/currentDate")).then()
            .statusCode(400);
    }

    // Test to check if current day is retrieved successfully or not
    @Order(3)
    @Test
    public void CurrentDayController() throws JsonProcessingException {
        // Preparing data for test
        DateFormat requiredDateFormat = new SimpleDateFormat("dd");
        requiredDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String currentDay = requiredDateFormat.format(new Date());
        given()
            .baseUri("http://localhost:8080")
            .with()
            .queryParams("timeZone","America/New_York",
                    "format", "dd")
            .contentType(ContentType.JSON)
            .when()
            .get(URI.create("/date/currentDay"))
            .then()
            .assertThat()
            .statusCode(200)
            .body("currentDay", equalTo(currentDay));
    }

    // Test to see if 400 status is response code for invalid timezone input
    @Order(4)
    @Test
    public void getCurrentDayError() {
        String testDay = "Wednesday";
        given()
            .baseUri("http://localhost:8080")
            .with().params("timeZone_new","America/NewYork",
                    "format", "DDD")
            .when()
            .get(URI.create("/date/currentDay")).then()
            .statusCode(400);
    }

    // Test to check if current month is retrieved successfully or not
    @Order(5)
    @Test
    public void CurrentMonthController() throws JsonProcessingException {
        // Preparing test data
        DateFormat requiredDateFormat = new SimpleDateFormat("MM");
        requiredDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String currentMonth = requiredDateFormat.format(new Date());
        given()
            .baseUri("http://localhost:8080")
            .with()
            .queryParams("timeZone","America/New_York",
                    "format", "M")
            .contentType(ContentType.JSON)
            .when()
            .get(URI.create("/date/currentMonth"))
            .then()
            .assertThat()
            .statusCode(200)
            .body("currentMonth", equalTo(currentMonth));
    }

    // Test to see if 400 status is response code for invalid timezone input
    @Order(6)
    @Test
    public void getCurrentMonthError(){
        String testMonth = "November";
        given()
            .baseUri("http://localhost:8080")
            .with().params("timeZone","InvalidTimezone",
                    "format", "MMM")
            .when()
            .get(URI.create("/date/currentMonth")).then()
            .statusCode(400);
    }

    // Test to check if current year is retrieved successfully or not
    @Order(7)
    @Test
    public void getCurrentYear() throws JsonProcessingException {
        // Preparing data for test
        DateFormat requiredDateFormat = new SimpleDateFormat("yy");
        requiredDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String currentYear = requiredDateFormat.format(new Date());
        given()
                .baseUri("http://localhost:8080")
                .with()
                .queryParams("timeZone","America/New_York",
                        "format", "yy")
                .contentType(ContentType.JSON)
                .when()
                .get(URI.create("/date/currentYear"))
                .then()
                .assertThat()
                .statusCode(200)
                .body("currentYear", equalTo(currentYear));
    }

    // Test to see if 400 status is response code for invalid timezone input
    @Order(8)
    @Test
    public void getCurrentYearError() throws JsonProcessingException {
        String testYear = "2022";
        given()
                .baseUri("http://localhost:8080").
                with()
                .params("timeZone","InvalidTimeZone",
                        "format", "YY")
                .when()
                .get(URI.create("/date/currentMonth")).then()
                .statusCode(400);
    }
}
