package com.poptok.android.poptok.service.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.poptok.android.poptok.model.location.LocationLocalLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class LocalDBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "LocalDBOpenHelper";

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "LocationCollect";
    private static LocalDBOpenHelper db;

    private LocalDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static LocalDBOpenHelper getInstance(Context context) {
        if(db == null) {
            db = new LocalDBOpenHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery =
                "CREATE TABLE Location (" +
                        "   id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "   latitude DOUBLE NOT NULL, " +
                        "   longitude DOUBLE NOT NULL, " +
                        "   altitude DOUBLE NOT NULL, " +
                        "   accuracy DOUBLE NOT NULL, " +
                        "   provider VARCHAR(30) NOT NULL, " +
                        "   regdate VARCHAR(20) NOT NULL" +
                        "   );";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oleVersion, int newVersion) {
        String dropQuery =
                "DROP TABLE IF EXISTS Location";
        db.execSQL(dropQuery);
        onCreate(db);
    }

    public void add(double latitude, double longitude, double altitude, double accuracy, String provider) {
        SQLiteDatabase db = this.getWritableDatabase();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());

        ContentValues values = new ContentValues();
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("altitude", altitude);
        values.put("accuracy", accuracy);
        values.put("provider", provider);
        values.put("regdate", date);

        db.insert("Location", null, values);
        db.close();
    }

    public int remove(List<LocationLocalLog> logList) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] ids = new String[logList.size()];
        for(int i=0; i < ids.length; i++) {
            ids[i] = String.valueOf(logList.get(i).getId());
        }
        String whereClause = String.format("id in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(ids.length, "?")) });
        Log.d(TAG, whereClause);
        return db.delete("Location", whereClause, ids);
    }


    public List<LocationLocalLog> getAll() {
        List<LocationLocalLog> logList = new ArrayList<LocationLocalLog>();

        String SELECT_ALL = "SELECT * FROM Location";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                LocationLocalLog locationLocalLog = new LocationLocalLog();
                locationLocalLog.setId(cursor.getInt(0));
                locationLocalLog.setLatitude(cursor.getDouble(1));
                locationLocalLog.setLongitude(cursor.getDouble(2));
                locationLocalLog.setAltitude(cursor.getDouble(3));
                locationLocalLog.setAccuracy(cursor.getDouble(4));
                locationLocalLog.setProvider(cursor.getString(5));
                locationLocalLog.setRegdate(cursor.getString(6));

                logList.add(locationLocalLog);
            } while (cursor.moveToNext());
        }

        return logList;

    }
}