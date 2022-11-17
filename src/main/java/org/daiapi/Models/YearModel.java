package org.daiapi.Models;

/**
 * Model for year object
 */
public class YearModel {
    String currentYear;
    public YearModel(String year){
        this.currentYear = year;
    }

    public String getCurrentYear() {
        return this.currentYear;
    }
}
