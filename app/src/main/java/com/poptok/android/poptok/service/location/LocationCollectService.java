package com.poptok.android.poptok.service.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.poptok.android.poptok.service.RestartService;
import com.poptok.android.poptok.service.localDB.LocalDBOpenHelper;

public class LocationCollectService extends Service implements Runnable {

    private static final String TAG = "LocationCollect";
    // 서비스 종료시 재부팅 딜레이 시간, activity의 활성 시간을 벌어야 한다.
    private static final int REBOOT_DELAY_TIMER = 10 * 100;
    // GPS 받는 주기 20초
    private static final int LOCATION_UPDATE_DELAY = 60 * 1000;

    LocalDBOpenHelper db;
    Handler handler;
    LocationProvider locationProvider;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        getLocation();
        handler.postDelayed(this, LOCATION_UPDATE_DELAY);
    }

    private void getLocation() {
        Location location = locationProvider.getLocation();
        if(location !=null) {
            Log.d(TAG, String.format("run : %f %f %f %f %s", location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), location.getProvider()));
            db.add(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), location.getProvider());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");

        locationProvider = new LocationProvider(this);
        Context context = getApplicationContext();
        db = LocalDBOpenHelper.getInstance(context);

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

}
