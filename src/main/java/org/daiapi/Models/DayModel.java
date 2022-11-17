package org.daiapi.Models;

/**
 * Model for Day response object
 */
public class DayModel {
    String currentDay;
    public DayModel(String day){
        this.currentDay = day;
    }

    public String getCurrentDay() {
        return this.currentDay;
    }
}
