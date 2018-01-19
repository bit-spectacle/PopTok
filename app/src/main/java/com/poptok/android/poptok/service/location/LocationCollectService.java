package com.poptok.android.poptok.service.location;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.poptok.android.poptok.service.RestartService;
import com.poptok.android.poptok.service.localDB.LocalDBOpenHelper;

/**
 * Created by BIT on 2018-01-18.
 */

public class LocationCollectService extends Service implements Runnable, LocationListener {

    private static final String TAG = "LocationCollect";
    // 서비스 종료시 재부팅 딜레이 시간, activity의 활성 시간을 벌어야 한다.
    private static final int REBOOT_DELAY_TIMER = 10 * 100;

    // GPS 받는 주기 20초
    private static final int LOCATION_UPDATE_DELAY = 60 * 1000;

    // 최소 GPS 정보 업데이트 거리 10미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    LocalDBOpenHelper db;

    Location location;
    double latitude;
    double longitude;
    double altitude;
    double accuracy;
    String provider;

    private Handler handler;
    private LocationManager locationManager;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        getLocation();
        Log.d(TAG, String.format("run : %f %f %f %f %s", longitude, latitude, altitude, accuracy, provider));
        handler.postDelayed(this, LOCATION_UPDATE_DELAY);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // GPS 정보 가져오기
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 현재 네트워크 상태 값 알아오기
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            // GPS 와 네트워크사용이 가능하지 않을때
            Log.d(TAG, "GPS is not enabled.");
        } else {
            // 네트워크 정보로 부터 위치값 가져오기
            provider = isNetworkEnabled ? provider = LocationManager.NETWORK_PROVIDER : LocationManager.GPS_PROVIDER;
            locationManager.requestLocationUpdates(
                    provider,
                    LOCATION_UPDATE_DELAY,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    // 위도 경도 저장
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    altitude = location.getAltitude();
                    accuracy = location.getAccuracy();

                    db.add(latitude, longitude, altitude, accuracy, provider);
                }
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        Context context = getApplicationContext();
        db = LocalDBOpenHelper.getInstance(context);
        locationManager = (LocationManager)context.getSystemService(LOCATION_SERVICE);
        handler = new Handler();
        handler.postDelayed(this, REBOOT_DELAY_TIMER);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {

        // 등록된 알람은 제거
        Log.d(TAG, "onCreate()");
        unregisterRestartAlarm();

        super.onCreate();
    }

    @Override
    public void onDestroy() {

        // 서비스가 죽었을때 알람 등록
        Log.d(TAG, "onDestroy()");
        registerRestartAlarm();

        super.onDestroy();
    }

    /**
     * 서비스가 시스템에 의해서 또는 강제적으로 종료되었을 때 호출되어
     * 알람을 등록해서 10초 후에 서비스가 실행되도록 한다.
     */
    private void registerRestartAlarm() {

        Log.d(TAG, "registerRestartAlarm()");

        Intent intent = new Intent(LocationCollectService.this, RestartService.class);
        intent.setAction(RestartService.ACTION_RESTART_LOCATION_COLLECT);
        PendingIntent sender = PendingIntent.getBroadcast(LocationCollectService.this, 0, intent, 0);

        long firstTime = SystemClock.elapsedRealtime();
        firstTime += REBOOT_DELAY_TIMER; // 10초 후에 알람이벤트 발생

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,REBOOT_DELAY_TIMER, sender);
    }


    /**
     * 기존 등록되어있는 알람을 해제한다.
     */
    private void unregisterRestartAlarm() {

        Log.d(TAG, "unregisterRestartAlarm()");
        Intent intent = new Intent(LocationCollectService.this, RestartService.class);
        intent.setAction(RestartService.ACTION_RESTART_LOCATION_COLLECT);
        PendingIntent sender = PendingIntent.getBroadcast(LocationCollectService.this, 0, intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
