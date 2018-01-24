package com.poptok.android.poptok.service.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;

public class LocationProvider implements LocationListener {

    private static final String TAG = "LocationProvider";
    private LocationManager locationManager;

    // GPS 받는 주기 20초
    private static final int LOCATION_UPDATE_DELAY = 60 * 1000;
    // 최소 GPS 정보 업데이트 거리 10미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    Context context;

    public LocationProvider(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
    }

    public Location getLocation() {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }

        // GPS 정보 가져오기
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 현재 네트워크 상태 값 알아오기
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            // GPS 와 네트워크사용이 가능하지 않을때
            Log.d(TAG, "GPS is not enabled.");
        } else {
            // 네트워크 정보로 부터 위치값 가져오기
            String provider = isNetworkEnabled ? provider = LocationManager.NETWORK_PROVIDER : LocationManager.GPS_PROVIDER;

            locationManager.requestLocationUpdates(
                    provider,
                    LOCATION_UPDATE_DELAY,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (locationManager != null) {
                Location location = locationManager.getLastKnownLocation(provider);
                return location;
            }
        }

        return  null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
