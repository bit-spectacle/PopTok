package com.poptok.android.poptok.controller.post;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostMapItem;
import com.poptok.android.poptok.model.search.SearchParam;
import com.poptok.android.poptok.model.setting.PoptokAppSettings;
import com.poptok.android.poptok.service.location.LocationProvider;
import com.poptok.android.poptok.service.post.PostMapThread;
import com.poptok.android.poptok.tools.IntegerVersionSignature;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.List;

/**
 * Created by KJH on 2017-05-15.
 * Fragment Life Style
 * 1. Fragment is added
 * 2. onAttach()                    Fragment가 Activty에 붙을때 호출
 * 3. onCreate()                    Activty에서의 onCreate()와 비슷하나, ui 관련 작업은 할 수 없다.
 * 4. onCreateView()                Layout을 inflater을 하여 View 작업을 하는 곳
 * 5. onActivityCreated()           Activity에서 Fragment를 모두 생성하고난 다음에 호출됨. Activty의 onCreate()에서 setContentView()한 다음과 같다
 * 6. onStart()                     Fragment가 화면에 표시될때 호출, 사용자의 Action과 상호 작용이 불가능함
 * 7. onResume()                    Fragment가 화면에 완전히 그렸으며, 사용자의 Action과 상호 작용이 가능함
 * 8. Fragment is active
 * 9. UserInfo navigates backward or fragment is removed/replaced  or Fragment is added to the back stack, then removed/replaced
 * 10. onPause()
 * 11. onStop()                     Fragment가 화면에서 더이상 보여지지 않게됬을때
 * 12. onDestroy()                  View 리소스를 해제할수있도록 호출. backstack을 사용했다면 Fragment를 다시 돌아갈때 onCreateView()가 호출됨
 * 13. onDetached()
 * 14. Fragment is destroyed
 */


/**
 * Google Map CallStack
 * 1. onCreate()
 * 2. onCreateView()
 * 3. onActivityCreated()
 * 4. onStart();
 * 5. onResume();
 * 5-2. onMapReady();
 * 6. onPause();
 * 7. onSaveInstanceState();
 * 8. onMapReady();
 */
@EFragment
public class GoogleMapFragment extends Fragment
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String LOG_TAG = "GoogleMapFragment";

    private static final LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 15000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 15000;

    @Bean
    PostMapThread postMapThread;

    @Bean
    SearchParam searchParam;

    @Bean
    PoptokAppSettings poptokAppSettings;

    LocationProvider locationProvider;

    private MapView mapView = null;
    private GoogleMap googleMap = null;
    private GoogleApiClient googleApiClient = null;
    private Marker currentMarker = null;

    View ballonBasic;
    RelativeLayout postBasicLayout;
    TextView textPostBasic;

    private final static int MAXENTRIES = 5;
    private String[] LikelyPlaceNames = null;
    private String[] LikelyAddresses = null;
    private String[] LikelyAttributions = null;
    private LatLng[] LikelyLatLngs = null;

    public GoogleMapFragment() {
        // required
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.post_map, container, false);

        mapView = (MapView) layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        ballonBasic = LayoutInflater.from(this.getActivity()).inflate(R.layout.post_balloon_basic, null);
        postBasicLayout = (RelativeLayout) ballonBasic.findViewById(R.id.postBasicLayout);
        textPostBasic = (TextView) ballonBasic.findViewById(R.id.textPostBasic);

        locationProvider = new LocationProvider(this.getActivity());

        postMapThread.setMainHandler(postDataHandler);
        postMapThread.setDaemon(true);
        postMapThread.start();

        return layout;
    }

    // postThread에서 postMap data가 넘어오면 UI 처리를 해준다.
    private Handler postDataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            googleMap.clear();

            List<PostMapItem> list = (List<PostMapItem>) msg.obj;
            Log.d(LOG_TAG, String.format("postDataHandler size: %d", list.size()));

            for (int i = 0; i < list.size(); i++) {
                addMarker(list.get(i), false);
            }
        }
    };

    private Marker addMarker(PostMapItem postMapItem, boolean isSelectedMarker) {

        LatLng position = new LatLng(postMapItem.getLatitude(), postMapItem.getLongitude());

        int postNo = postMapItem.getPostNo();
        int groupCount = postMapItem.getGroupCount();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(postMapItem.getPostDate());
        markerOptions.position(position);
        markerOptions.zIndex(postNo);

        // 내용
        textPostBasic.setText(postMapItem.getContent());

        // 말풍선 종류
        if (groupCount == 1) {
            postBasicLayout.setBackgroundResource(R.drawable.balloon_basic);
        } else {
            postBasicLayout.setBackgroundResource(R.drawable.balloon_group);
        }

        // 이미지
        String imageUrl = postMapItem.getImage();
        if(imageUrl.length() > 0) {
            Context context = this.getActivity();
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .signature(new IntegerVersionSignature(postNo)))
                    .into(new ViewTarget<TextView, Drawable>(textPostBasic) {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            TextView view = this.view;
                            view.setBackground(resource);
                        }
                    });
            textPostBasic.setTextColor(Color.WHITE);
        }
        else {
            textPostBasic.setBackground(null);
            textPostBasic.setTextColor(Color.BLACK);
        }
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(ballonBasic)));

        Marker marker = googleMap.addMarker(markerOptions);
        String markerTag = String.format("%d|%d", postNo, groupCount);
        marker.setTag(markerTag);

        return marker;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();

        if (googleApiClient != null && googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        if (googleApiClient != null)
            googleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.getFusedLocationProviderClient(this.activity).removeLocationUpdates(
                    new LocationCallback() {

                    }
            );
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();

        if (googleApiClient != null) {
            googleApiClient.unregisterConnectionCallbacks(this);
            googleApiClient.unregisterConnectionFailedListener(this);

            if (googleApiClient.isConnected()) {
                LocationServices.getFusedLocationProviderClient(this.activity).removeLocationUpdates(
                        new LocationCallback() {

                        }
                );
                googleApiClient.disconnect();
            }
        }
    }

    Activity activity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
        this.activity = getActivity();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에 지도의 초기위치를 서울로 이동
        setCurrentLocation(null, "위치정보 가져올 수 없음", "위치 퍼미션과 GPS 활성 여부 확인");

        int zoomLevel = 17;
        if(poptokAppSettings.isGpson() == false) {
            zoomLevel = searchParam.getZoomLevel();
            if(zoomLevel < 6 || zoomLevel > 20) zoomLevel = 17;
        }

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMinZoomPreference(6);
        googleMap.setMaxZoomPreference(20);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 사용권한체크
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);

            if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {
                //사용권한이 없을경우
                //권한 재요청
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else {
                //사용권한이 있는경우
                setGoogleMapEvent();
            }
        } else {
            setGoogleMapEvent();
        }

    }

    private void setGoogleMapEvent() {
        if (googleApiClient == null) {
            buildGoogleApiClient();
        }

        if (ActivityCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);

            googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    Log.d("onMyLocationButtonClick", "onMyLocationButtonClick");
                    return false;
                }
            });

            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    Log.d("OnCameraIdleListener", "OnCameraIdleListener");
                    callPostMapApi();
                }
            });

            googleMap.setOnMarkerClickListener(this);
        }
    }


    private Bitmap createDrawableFromView(View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this.activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                //.enableAutoManage(this, this)
                .build();
        googleApiClient.connect();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        if (currentMarker != null) currentMarker.remove();

        LatLng selectedLocation;

        if (location != null) {
            //현재위치의 위도 경도 가져옴
            selectedLocation = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            if (poptokAppSettings.isGpson()) {
                Location nowLocation = locationProvider.getLocation();
                selectedLocation = nowLocation == null ? DEFAULT_LOCATION : new LatLng(nowLocation.getLatitude(), nowLocation.getLongitude());
            } else {
                LatLng center = searchParam.getCenter();
                selectedLocation = center == null ? DEFAULT_LOCATION : center;
            }
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(selectedLocation);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = this.googleMap.addMarker(markerOptions);

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(selectedLocation));
    }

    private void callPostMapApi() {
//        LocationParam locationParam = LocationParam.getGoogleLocationParam(googleMap);
//        searchParam.setTop(new LatLng(locationParam.top.latitude, locationParam.top.longitude));
//        searchParam.setBottom(new LatLng(locationParam.bottom.latitude, locationParam.bottom.longitude));
        searchParam.setGoogleLocation(googleMap);
        Message.obtain(postMapThread.backHandler, PostMapThread.cPostMap, searchParam).sendToTarget();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Location location = new Location("");
        location.setLatitude(DEFAULT_LOCATION.latitude);
        location.setLongitude((DEFAULT_LOCATION.longitude));

        setCurrentLocation(location, "위치정보 가져올 수 없음",
                "위치 퍼미션과 GPS활성 여부 확인");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!checkLocationServicesStatus()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
            builder.setTitle("위치 서비스 비활성화");
            builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" +
                    "위치 설정을 수정하십시오.");
            builder.setCancelable(true);
            builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent callGPSSettingIntent =
                            new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL_MS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this.activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                LocationServices.getFusedLocationProviderClient(this.activity).requestLocationUpdates(locationRequest,
                        new LocationCallback() {
                        },
                        null
                );
            }
        } else {
            LocationServices.getFusedLocationProviderClient(this.activity).requestLocationUpdates(locationRequest,
                    new LocationCallback() {
                    },
                    null
            );
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        if (cause == CAUSE_NETWORK_LOST)
            Log.e(LOG_TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e(LOG_TAG, "onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(LOG_TAG, "onLocationChanged call..");
        searchCurrentPlaces();
    }

    private void searchCurrentPlaces() {
        @SuppressWarnings("MissingPermission")
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(googleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {

            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                int i = 0;
                LikelyPlaceNames = new String[MAXENTRIES];
                LikelyAddresses = new String[MAXENTRIES];
                LikelyAttributions = new String[MAXENTRIES];
                LikelyLatLngs = new LatLng[MAXENTRIES];

                for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                    LikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                    LikelyAddresses[i] = (String) placeLikelihood.getPlace().getAddress();
                    LikelyAttributions[i] = (String) placeLikelihood.getPlace().getAttributions();
                    LikelyLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                    i++;
                    if (i > MAXENTRIES - 1) {
                        break;
                    }
                }

                placeLikelihoods.release();

                Location location = new Location("");
                location.setLatitude(LikelyLatLngs[0].latitude);
                location.setLongitude(LikelyLatLngs[0].longitude);

                setCurrentLocation(location, LikelyPlaceNames[0], LikelyAddresses[0]);
            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String markerTag = marker.getTag().toString();
        String[] tagSplit = markerTag.split("\\|");

        int postNo = Integer.parseInt(tagSplit[0]);
        int groupCount = Integer.parseInt(tagSplit[1]);

        Intent intent = new Intent(this.activity, PostDetailActivity_.class);
        intent.putExtra("postNo", postNo);
        startActivity(intent);

        return true;
    }
}
