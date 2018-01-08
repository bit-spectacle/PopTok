package com.poptok.android.poptok.model;

/**
 * Created by BIT on 2018-01-08.
 */

public class LocationParam {

    public Point top;
    public Point bottom;
    public Point center;
    public int zoomLevel;
    final static double init_lat= 0.0000005;
    final static double init_lon = 0.0000008;

    public static LocationParam getLoacationParam(int zoomLevel, int displayWidth, int displayHeight, double centerLatitude, double centerLongitude) {
        LocationParam locationParam = new LocationParam();
        locationParam.zoomLevel = zoomLevel;
        locationParam.center = new Point(centerLatitude, centerLongitude);

        double range = Math.pow(2, zoomLevel + 1);
        double top_lat = centerLatitude + (init_lat * displayHeight * 1.5 * range);
        double top_lon = centerLongitude + (init_lon * displayHeight * 1.5 * range);
        double bot_lat = centerLatitude - (top_lat - centerLatitude);
        double bot_lon = centerLongitude - (top_lon - centerLongitude);

        locationParam.top = new Point(top_lat, top_lon);
        locationParam.bottom = new Point(bot_lat, bot_lon);

        return  locationParam;
    }
}
