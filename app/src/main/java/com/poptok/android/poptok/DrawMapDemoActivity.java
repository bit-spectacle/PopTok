package com.poptok.android.poptok;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by BIT on 2017-12-19.
 */

public class DrawMapDemoActivity extends FragmentActivity
        implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {

    private static final String LOG_TAG = "DrawMapDemoActivity : ";

    //이거랑 같은거 저 앞에 있는데 필요가 있나?
    //없으면 빨간줄 뜨네;;
//    public void onMapViewInitialized(MapView mapView) {
//        Log.i(LOG_TAG, "onMapViewInitialized() Success");
//
//        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithCONGCoord());
//
//    }
    public void onMapViewInitialized(MapView mapView) {

        //DAUM 지도 api를 사용하기 위해서 REST KEY를 사용해준다.
        mapView.setDaumMapApiKey("f1ce1afe4bd1995ca9fe8c5957fcd78b");
        //최근 위치를 알아내기
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        //말풍선을 띄우기 위해서 줌 레벨을 1로 준다. 이때 경도 위도는 최근 위치로 받아올 것이기 때문에 의미가 없는 숫자.
        //경도 위도를 따로 얻어오는 기본 안드로이드 함수를 사용하면 경도 위도도 현재 위치로 설정할 수 있지만 그건 차후에 하도록한다.
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 7, false);
        //단순히 잘 찍혔는지 로그를 확인하기 위함.
        Log.i(LOG_TAG, "onMapViewInitialized");

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {

        MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
        Log.i(LOG_TAG, "onMapViewCenterPointMoved() call");


    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }
}

