package com.poptok.android.poptok.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.poptok.android.poptok.service.location.LocationCollectService;
import com.poptok.android.poptok.service.location.LocationReportService_;

public class RestartService extends BroadcastReceiver {

    public static final String ACTION_RESTART_LOCATION_COLLECT = "ACTION.Restart.LocationCollect";
    public static final String ACTION_RESTART_LOCATION_REPORT = "ACTION.Restart.LocationReport";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RestartService", "RestartService called!!!!!!!!!!!!!!!!!!!!!!!");

        /* 서비스 죽일때 알람으로 다시 서비스 등록 */
        if (intent.getAction().equals(ACTION_RESTART_LOCATION_COLLECT)) {

            Log.d("RestartService", "LocationCollectService revive");

            Intent collectIntent = new Intent(context, LocationCollectService.class);
            context.startService(collectIntent);
        }

        if (intent.getAction().equals(ACTION_RESTART_LOCATION_REPORT)) {

            Log.d("RestartService", "LocationReportService_ revive");
            LocationReportService_.intent(context).start();
//            Intent reportIntent = new Intent(context, LocationReportService.class);
//            context.startService(reportIntent);
        }

        /* 폰 재부팅할때 서비스 등록 */
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Log.d("RestartService", "ACTION_BOOT_COMPLETED");

            Intent collectIntent = new Intent(context, LocationCollectService.class);
            context.startService(collectIntent);
            LocationReportService_.intent(context).start();
//            Intent reportIntent = new Intent(context, LocationReportService.class);
//            context.startService(reportIntent);
        }
    }
}