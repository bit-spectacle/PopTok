package com.poptok.android.poptok.model.store;

public class StoreItem {
    int locationNo;
    String code;
    String businessName;
    String oldAddress;
    String newAddress;
    double latitude;
    double longitude;
    String category;

    public StoreItem() {
    }

    public StoreItem(int locationNo, String code, String businessName, String oldAddress, String newAddress, double latitude, double longitude, String category) {
        this.locationNo = locationNo;
        this.code = code;
        this.businessName = businessName;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    public int getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(int locationNo) {
        this.locationNo = locationNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
