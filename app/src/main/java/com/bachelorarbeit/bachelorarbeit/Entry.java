package com.bachelorarbeit.bachelorarbeit;

public class Entry {

    private String sensitivies, activities, places, date, daytime, time;
    private long id;


    public Entry(String sensitivies, String activities, String places, String date, String time){
        this.sensitivies = sensitivies;
        this.activities = activities;
        this.places = places;
        this.date = date;
        this.daytime = time;
    }

    public Entry(long id){
        this.id = id;
    }

    public Entry(){
        Entry entry = new Entry ("", "", "", "", "");
    }

    public void setId(long id)
    {this.id = id;}

    public long getId(){
        return id;
    }

    public String getSensitivies(){
        return sensitivies;
    }

    public void setSensitivities(String sensitivies){
        this.sensitivies = sensitivies;
    }

    public String getActivities(){
        return activities;
    }

    public void setActivities(String activities){
        this.activities = activities;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }
}
