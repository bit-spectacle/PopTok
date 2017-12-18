package com.poptok.android.poptok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


public class DrawMapActivity extends AppCompatActivity{

    private MapPoint.GeoCoordinate mapPointGeo;
    private MapPoint mapPoint = null;


    public void onMapViewInitialized(MapView mapView) {

        //DAUM 지도 api를 사용하기 위해서 REST KEY를 사용해준다.
        mapView.setDaumMapApiKey("f1ce1afe4bd1995ca9fe8c5957fcd78b");
        //최근 위치를 알아내기
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        //말풍선을 띄우기 위해서 줌 레벨을 1로 준다. 이때 경도 위도는 최근 위치로 받아올 것이기 때문에 의미가 없는 숫자.
        //경도 위도를 따로 얻어오는 기본 안드로이드 함수를 사용하면 경도 위도도 현재 위치로 설정할 수 있지만 그건 차후에 하도록한다.
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 1, false);
        //단순히 잘 찍혔는지 로그를 확인하기 위함.
        Log.i("DrawMapActivity : ", "onMapViewInitialized");
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        MapView mapView = new MapView(this);

        MapView.CurrentLocationEventListener mCurrentLocationEventListener = null;
        //현재 위치와 전혀 상관없는 포인트가 찍히더라도 빨리 현위치로 돌아오라고 initialized를 데려와서 씀
        onMapViewInitialized(mapView);
        setContentView(R.layout.activity_drawmap);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }




}
