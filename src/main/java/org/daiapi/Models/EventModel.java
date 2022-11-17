package org.daiapi.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to for event object
 */
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class EventModel {
    @JsonProperty("id")
    public String id;
    @JsonProperty("date")
    public String date;
    @JsonProperty("practitioner")
    public String practitioner;
    @JsonProperty("location")
    public String location;
    @JsonProperty("name")
    public String name;
    @JsonProperty("detail")
    public String detail;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EventModel(
            @JsonProperty("id") String id,
            @JsonProperty("date")String date,
            @JsonProperty("practitioner") String practitioner,
            @JsonProperty("location") String location,
            @JsonProperty("name") String name,
            @JsonProperty("detail") String detail){
        this.id = id;
        this.date = date;
        this.practitioner = practitioner;
        this.location = location;
        this.name = name;
        this.detail = detail;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEventDate() {
        return this.date;
    }

    public void setEventDate(String newDate) {
        this.date = newDate;
    }

    public void setPractitioner(String newPractitioner) {
        this.practitioner = newPractitioner;
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

    public void setDetail(String newDetail) {
        this.detail = newDetail;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
