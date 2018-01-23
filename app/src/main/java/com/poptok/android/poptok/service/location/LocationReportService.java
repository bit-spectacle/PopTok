package com.poptok.android.poptok.service.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.poptok.android.poptok.model.location.LocationLocalLog;
import com.poptok.android.poptok.service.RestartService;
import com.poptok.android.poptok.service.localDB.LocalDBOpenHelper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

@EService
public class LocationReportService extends Service implements Runnable {
    private static final String TAG = "LocationReport";
    private static final int REBOOT_DELAY_TIMER = 10 * 100;
    private static final int LOCATION_UPDATE_DELAY = 60 * 1000;

    LocalDBOpenHelper db;
    private Handler handler;

    @RestService
    ILocationReporter locationReporter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        reportLocation();
        handler.postDelayed(this, LOCATION_UPDATE_DELAY);
    }

    @Background
    public void reportLocation() {
        int userNo = 100;
        if(userNo > 0) {
            List<LocationLocalLog> list = db.getAll();
            for(LocationLocalLog log : list) {
                Log.d(TAG, String.format("%d %f %f %s %s", log.getId(), log.getLatitude(), log.getLongitude(), log.getProvider(), log.getRegdate()));
            }

            if (list != null && list.size() > 0) {
                Log.d(TAG, String.format("Call Async %d", list.size()));
                LocationReportAsyncTask locationReportAsyncTask = new LocationReportAsyncTask(db, list, locationReporter);
                locationReportAsyncTask.execute(userNo);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        Context context = getApplicationContext();
        db = LocalDBOpenHelper.getInstance(context);

        handler = new Handler();
        handler.postDelayed(this, REBOOT_DELAY_TIMER);

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        unregisterRestartAlarm();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        registerRestartAlarm();
        super.onDestroy();
    }

    private void registerRestartAlarm() {

        Log.d(TAG, "registerRestartAlarm()");

        Intent intent = new Intent(LocationReportService.this, RestartService.class);
        intent.setAction(RestartService.ACTION_RESTART_LOCATION_REPORT);
        PendingIntent sender = PendingIntent.getBroadcast(LocationReportService.this, 0, intent, 0);

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
        Intent intent = new Intent(LocationReportService.this, RestartService.class);
        intent.setAction(RestartService.ACTION_RESTART_LOCATION_REPORT);
        PendingIntent sender = PendingIntent.getBroadcast(LocationReportService.this, 0, intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }
}
