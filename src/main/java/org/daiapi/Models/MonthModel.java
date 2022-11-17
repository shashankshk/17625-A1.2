package org.daiapi.Models;

/**
 * Model for month object
 */
public class MonthModel {
    String currentMonth;
    public MonthModel(String month){
        this.currentMonth = month;
    }

    public String getCurrentMonth() {
        return this.currentMonth;
    }
}
