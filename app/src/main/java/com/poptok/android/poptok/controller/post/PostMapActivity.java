package com.poptok.android.poptok.controller.post;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.poptok.android.poptok.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;

import static net.daum.mf.map.api.MapView.CurrentLocationTrackingMode.TrackingModeOff;

/**
 * Created by BIT on 2017-12-19.
 */

@EActivity
public class PostMapActivity extends FragmentActivity
        implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {

    private static final String LOG_TAG = "PostMapActivity : ";

    //    public void onMapViewInitialized(MapView mapView) {
//        Log.i(LOG_TAG, "onMapViewInitialized() Success");
//
//        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithCONGCoord());
//
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapView mapView = new MapView(this);
        // MapView.clearMapTilePersistentCache();
        // mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        MapView.CurrentLocationEventListener mCurrentLocationEventListener = null;

        onMapViewInitialized(mapView);

        //MapPoint mapPoint;

//      mapView.setCurrentLocationEventListener(mCurrentLocationEventListener);

        setContentView(R.layout.activity_drawmap);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);


    }

    @Click(R.id.trackingOffButton)
    void trackingOffButton(MapView mapView) {
        Log.i(LOG_TAG, "trackingOffButton Clicked");
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);

    }

    @Click(R.id.tackingOnButton)
    void trackingOnButton(MapView mapView){
        Log.i(LOG_TAG, "trackingOnButton Clicked");
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }


    public void onMapViewInitialized(MapView mapView) {

        //DAUM 지도 api를 사용하기 위해서 REST KEY를 사용해준다.
        mapView.setDaumMapApiKey("f1ce1afe4bd1995ca9fe8c5957fcd78b");
        //최근 위치를 알아내기
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        //말풍선을 띄우기 위해서 줌 레벨을 1로 준다. 이때 경도 위도는 최근 위치로 받아올 것이기 때문에 의미가 없는 숫자.
        //경도 위도를 따로 얻어오는 기본 안드로이드 함수를 사용하면 경도 위도도 현재 위치로 설정할 수 있지만 그건 차후에 하도록한다.
        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 12, false);
        //단순히 잘 찍혔는지 로그를 확인하기 위함.
        //mapView.setShowCurrentLocationMarker(true);


        Log.i(LOG_TAG, "onMapViewInitialized");
        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {
        Log.i(LOG_TAG, "onMapViewCenterPointMoved() call");
//        MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//        mapView.setShowCurrentLocationMarker(false);
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);


    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {

        Log.i(LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewSingleTapped() call");
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewDoubleTapped() call");
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewLongPressed() call");
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewCenterPointMoved() call");
        //위치 트래킹 모드 off
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//        mapView.setShowCurrentLocationMarker(false);
//        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
//
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewDragEnded() call");
        //위치 트래킹모드 off
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//        mapView.setShowCurrentLocationMarker(false);
//        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
//
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        Log.i(LOG_TAG, "onMapViewMoveFinished() call");
        //위치 트래킹모드 off
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//        mapView.setShowCurrentLocationMarker(false);
//        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
//
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {

        Log.i(LOG_TAG, String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
    }
}

