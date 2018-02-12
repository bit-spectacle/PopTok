package com.poptok.android.poptok.model.recommend;

/**
 * Created by BIT on 2018-02-07.
 */

public class RecommendLocationParam {

    public double latitude;
    public double longitude;

    public RecommendLocationParam(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static RecommendLocationParam getLocation(double latitude, double longitude){
        RecommendLocationParam locationParam = new RecommendLocationParam(latitude, longitude);
        return locationParam;
    }


}
