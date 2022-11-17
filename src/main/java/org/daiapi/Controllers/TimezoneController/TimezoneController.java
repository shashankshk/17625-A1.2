package org.daiapi.Controllers.TimezoneController;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;
import java.util.TimeZone;

public class TimezoneController {
    private static ObjectMapper objectMapper;
    /**
     * Controller to handle Timezone list requests
     */
    public static Route TimezoneListController = (Request req, Response res) -> {
        try{
            objectMapper = new ObjectMapper();
            String returnValue = objectMapper.writeValueAsString(Set.of(TimeZone.getAvailableIDs()));
            res.body(returnValue);
            return returnValue;
        } catch (RuntimeException e){
            res.status(400);
            System.out.println(e);
            return e.getMessage();
        }
//        return "Dates API";
    };
}
