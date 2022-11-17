//package org.daiapi;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;
//
//import java.net.URI;
//
//public class TimeZoneAPITest {
//
//    private ObjectMapper objectMapperObj;
//    @BeforeEach
//    public void setUp() {
//        objectMapperObj = new ObjectMapper();
//    }
//
//    // Test to check if timeZones are found are not
//    @Test
//    public void getTimeZones(){
//        // Checks for timezones present in timeZones.json file
//        get(URI.create("/timeZones"))
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body(matchesJsonSchemaInClasspath("timeZones.json"));
//    }
//
//    // Test to check if it error is thrown when wrong resource is accessed
//    @Test
//    public void getTimeZonesError() {
//        get(URI.create("/timeZones/all"))
//                .then()
//                .assertThat()
//                .statusCode(404);
//    }
//
//    // Test to check if default time zone is set
//    @Test
//    public void selectDefaultTimezone() {
//        with().params("timeZone","Asia/Kolkata")
//                .when()
//                .post(URI.create("/timeZones/selectedTimeZone"))
//                .then()
//                .statusCode(201)
//                .body("timeZone", equalTo("Asia/Kolkata"));
//
//    }
//
//    // Test to check if 400 status is returned when invalid timezone is sent
//    @Test
//    public void selectDefaultTimezoneError(){
//        with().params("timeZone","InvalidTimeZone")
//                .when()
//                .post(URI.create("/timeZones/selectedTimeZone"))
//                .then()
//                .statusCode(400);
//
//    }
//}