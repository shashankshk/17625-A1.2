package org.daiapi.Models;

/**
 * Model for date response
 */
public class DateModel {
    String currentDate;
    public DateModel(String date){
        this.currentDate = date;
    }

    public String getCurrentDate() {
        return this.currentDate;
    }
}
