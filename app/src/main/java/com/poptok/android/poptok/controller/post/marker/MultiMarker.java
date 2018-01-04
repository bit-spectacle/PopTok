package com.poptok.android.poptok.controller.post.marker;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poptok.android.poptok.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by BIT on 2017-12-26.
 */

public class MultiMarker extends FragmentActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener {

    private MapPoint centerPoint ;
    private MapView mapView;
    private MapPOIItem multiMarker;
    private MapPOIItem defaultMarker;
    private MapPOIItem friendMarker;
    private MapPOIItem secretMarker;

    class DefaultCalloutBalloonAdapter implements CalloutBalloonAdapter{
        private final View defaultCalloutBalloon;

        public DefaultCalloutBalloonAdapter() {
            defaultCalloutBalloon = getLayoutInflater().inflate(R.layout.post_custom_callout_balloon, null);
        }
        @Override
        public View getCalloutBalloon(MapPOIItem poiItem){
            // ((ImageView) defaultCalloutBalloon.findViewById(R.id.badge)).setImageURI("./");
            //여기서 조건문 image가 없을 경우~ 라는 조건을 달아서 이미지가 없으면 이미지를 넣어주지 않도록.
            ((ImageView) defaultCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.ic_launcher);
            ((TextView) defaultCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
            ((TextView) defaultCalloutBalloon.findViewById(R.id.desc)).setText("지니 귀여워");
            return defaultCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
            //여기서 클릭한게 뭔지 알아내서 리스트를 보여줘야함
            return null;
        }

    }

//    private void addDefaultMarker(int count){
//        //해당 좌표에 몇개나 글이 있는지 알아야하기 때문에 int count를 변수로 받아온다.
//        MapPoint.GeoCoordinate mapPointGeo = centerPoint.getMapPointGeoCoord();
//    }

    private void createMultiMarker(MapView mapView) {
        multiMarker = new MapPOIItem();
        String name = "Default Marker";
        multiMarker.setItemName(name);
        multiMarker.setTag(0);
        //mapPoint를 받아와야함 --> 현재 사용자가 화면으로 보고 있는 위치 기준이 가장 좋겠지
        //일단 기본으로 getMapCenterPoint()로 한다
        multiMarker.setMapPoint(mapView.getMapCenterPoint());
        multiMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        multiMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(multiMarker);
        mapView.selectPOIItem(multiMarker, true);
        mapView.setMapCenterPoint(mapView.getMapCenterPoint(), false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nested_drawmap);
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.setDaumMapApiKey("f1ce1afe4bd1995ca9fe8c5957fcd78b");
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);

        //defaultCalloutBalloonAdapter 등록
        mapView.setCalloutBalloonAdapter(new DefaultCalloutBalloonAdapter());

    }



    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

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
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Toast.makeText(this, "Clicked " + mapPOIItem.getItemName() + " Callout Balloon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
