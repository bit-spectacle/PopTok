package com.poptok.android.poptok.model.location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by BIT on 2018-01-08.
 */

public class LocationParam {

    public LocationPoint top;
    public LocationPoint bottom;
    public LocationPoint center;
    public int zoomLevel;
    final static double init_lat= 0.0000005;
    final static double init_lon = 0.0000008;

    public static LocationParam getLoacationParam(int zoomLevel, int displayWidth, int displayHeight, double centerLatitude, double centerLongitude) {
        LocationParam locationParam = new LocationParam();
        locationParam.zoomLevel = zoomLevel;
        locationParam.center = new LocationPoint(centerLatitude, centerLongitude);

        double range = Math.pow(2, zoomLevel + 1);
        double top_lat = centerLatitude + (init_lat * displayHeight * 1.5 * range);
        double top_lon = centerLongitude + (init_lon * displayWidth * 1.5 * range);
        double bot_lat = centerLatitude - (top_lat - centerLatitude);
        double bot_lon = centerLongitude - (top_lon - centerLongitude);

        locationParam.top = new LocationPoint(top_lat, top_lon);
        locationParam.bottom = new LocationPoint(bot_lat, bot_lon);

        return  locationParam;
    }

    public static LocationParam getGoogleLocationParam(GoogleMap googleMap) {
        Projection projection = googleMap.getProjection();
        LatLngBounds bounds = projection.getVisibleRegion().latLngBounds;
        LatLng center = googleMap.getCameraPosition().target;

        LocationParam locationParam = new LocationParam();
        locationParam.zoomLevel = (int)googleMap.getCameraPosition().zoom;
        locationParam.center = new LocationPoint(center.latitude, center.longitude);
        locationParam.top = new LocationPoint(bounds.northeast.latitude, bounds.northeast.longitude);
        locationParam.bottom = new LocationPoint(bounds.southwest.latitude, bounds.southwest.longitude);

        return  locationParam;
    }
}
