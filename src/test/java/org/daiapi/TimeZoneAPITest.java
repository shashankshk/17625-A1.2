package org.daiapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.util.Set;
import java.util.TimeZone;

//
public class TimeZoneAPITest {

    private static ObjectMapper objectMapperObj;

    @BeforeAll
    public static void setUpAll() {
        objectMapperObj = new ObjectMapper();
        Main.runMainServer(8083);

    }

    // Test to check if timeZones are found are not
    @Test
    public void getTimeZones() throws JsonProcessingException {
        // Checks for timezones present in timeZones.json file
        given()
                .baseUri("http://localhost:8081")
                .with()
                .contentType(ContentType.JSON)
                .when()
                .get(URI.create("/timeZones"))
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo(objectMapperObj.writeValueAsString(Set.of(TimeZone.getAvailableIDs()))));
    }
    @Test
    public void getTimeZonesError() throws JsonProcessingException {
        // Checks for timezones present in timeZones.json file
        given()
                .baseUri("http://localhost:8081")
                .with()
                .contentType(ContentType.JSON)
                .when()
                .get(URI.create("/timeZones/new"))
                .then()
                .assertThat()
                .statusCode(404);
    }
}
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