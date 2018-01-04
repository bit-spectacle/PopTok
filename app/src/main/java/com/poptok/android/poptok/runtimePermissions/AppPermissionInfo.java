package com.poptok.android.poptok.runtimePermissions;


import android.Manifest;

public class AppPermissionInfo {
    public static final String[] LOCATION_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static final String[] VOICE_SEARCH_PERMISSIONS = new String[] {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
}