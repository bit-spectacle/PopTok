package com.poptok.android.poptok.model.location;

/**
 * Created by BIT on 2018-02-06.
 */

public class LocationList {

    private String businessName;
    private double latitude;
    private double longitude;
    private int userNo;
    private int locationNo;
    private float score;

    public LocationList(){

    }




//    public LocationList(String locationName, double latitude, double longitude, int locationNo, float score){
//        this.locationName = locationName;
//        this.latitude = latitude;
//        this.longitude =longitude;
//        this.locationNo = locationNo;
//        this.score = score;
//    }


    public LocationList(String businessName, double latitude, double longitude, int userNo, int locationNo, float score) {
        this.businessName = businessName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userNo = userNo;
        this.locationNo = locationNo;
        this.score = score;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
