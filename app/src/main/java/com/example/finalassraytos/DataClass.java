package com.example.finalassraytos;

public class DataClass {
    private String dataName;
    private String dataSNumber;

    private String dataYear;
    private String key;
    public DataClass(){


    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataClass( String dataName, String dataSNumber, String dataYear) {
        this.dataName = dataName;
        this.dataSNumber = dataSNumber;
        this.dataYear = dataYear;
    }
    public String getDataSNumber() {
        return dataSNumber;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataYear() {
        return dataYear;
    }

}
