package com.poptok.android.poptok.controller.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.service.location.LocationProvider;
import com.poptok.android.poptok.service.recommend.IRecommendFinder;
import com.poptok.android.poptok.view.recommend.RecommendLocationAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-02-09.
 */

@EActivity
public class RecommendMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    @Bean
    AuthStore authStore;

    @Extra
    String LocationName;

    @Extra
    double LocationLatitude;

    @Extra
    double LocationLongitude;

//    @Bean
//    RecommendLocationStore recommendLocationStore;

    @Bean
    RecommendLocationAdapter recommendLocationAdapter;

    @RestService
    IRecommendFinder recommendFinder;

    private MapView mapView = null;
    private GoogleMap googleMap = null;
    LocationProvider locationProvider;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View layout = LayoutInflater.from(this).inflate(R.layout.user_recommendmap, null);

//        mapView = (MapView) layout.findViewById(R.id.map);

        setContentView(R.layout.user_recommendmap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @AfterViews
    public void init(){
        Log.i("Log======", ""+LocationName);
        Log.i("LOG======", ""+LocationLatitude);
        Log.i("LOG======", ""+LocationLongitude);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        Log.i("Log", ""+LocationName);
//        Log.i("LOG", ""+LocationLatitude);
//        Log.i("LOG", ""+LocationLongitude);

        LatLng location = new LatLng(LocationLatitude, LocationLongitude);
        mMap.addMarker(new MarkerOptions().position(location).title(LocationName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));


        googleMap.setMinZoomPreference(6);
        googleMap.setMaxZoomPreference(20);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));


    }

//    @Override
//    public void onCreate(@Nullable Bundle saveInstanceState){
//        super.onCreate(saveInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreate(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View layout = inflater.inflate(R.layout.post_map, container, false);
//
//        MapView mapView = (MapView) layout.findViewById(R.id.map);
//        mapView.getMapAsync(this);
//
////        ballonBasic = LayoutInflater.from(this.getActivity()).inflate(R.layout.post_balloon_basic, null);
////        postBasicLayout = (RelativeLayout) ballonBasic.findViewById(R.id.postBasicLayout);
////        textPostBasic = (TextView) ballonBasic.findViewById(R.id.textPostBasic);
//
////        locationProvider = new LocationProvider(this);
//
////        postMapThread.setMainHandler(postDataHandler);
////        postMapThread.setDaemon(true);
////        postMapThread.start();
//
//        return layout;
//    }


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
////        View layout = inflater.inflate(R.layout.post_map, container, false);
////
////        MapView mapView = (MapView) layout.findViewById(R.id.map);
////        mapView.getMapAsync(this);
//
//    }

    @Override


    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//    }
//

//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }



//    @Override
//    public void resultHandler(JSONResult<LocationList> result) {
//
//    }
}
