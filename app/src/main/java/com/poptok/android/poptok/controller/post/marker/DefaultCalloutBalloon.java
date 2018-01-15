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

public class DefaultCalloutBalloon extends FragmentActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener {

    private MapPoint centerPoint ;
    private MapView mapView;
    private MapPOIItem defaultMarker;
    private int count = 0;

    class DefaultCalloutBalloonAdapter implements CalloutBalloonAdapter{
        private final View defaultCalloutBalloon;

        public DefaultCalloutBalloonAdapter() {
            defaultCalloutBalloon = getLayoutInflater().inflate(R.layout.post_balloon_basic, null);
        }
        @Override
        public View getCalloutBalloon(MapPOIItem poiItem){
            // ((ImageView) defaultCalloutBalloon.findViewById(R.id.badge)).setImageURI("./");
            //여기서 조건문 image가 없을 경우~ 조건 달아서 해줘야함
//            ((ImageView) defaultCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.ic_launcher);
//            ((TextView) defaultCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
//            ((TextView) defaultCalloutBalloon.findViewById(R.id.desc)).setText("지니 귀여워");
            return defaultCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
            //여기서 클릭한게 뭔지 알아내서 리스트를 보여줘야함
            String name = mapPOIItem.getItemName();
            //이름 가지고 경도위도 알아내서 같은 경도 위도에 있는 글들 쫙 뿌려줘야하니까


            return null;
        }

    }

//    private void addDefaultMarker(int count){
//        //해당 좌표에 몇개나 글이 있는지 알아야하기 때문에 int count를 변수로 받아온다.
//        MapPoint.GeoCoordinate mapPointGeo = centerPoint.getMapPointGeoCoord();
//    }

    private void createDefaultMarker(MapView mapView) {
        defaultMarker = new MapPOIItem();
        //default marker마다 이름을 다르게 해줘야 삭제하거나 할 때 유용!
        String name = "Default Marker"+count;
        count++;
        defaultMarker.setItemName(name);
        defaultMarker.setTag(0);
       // defaultMarker.set
//        defaultMarker.setMarker

        //mapPoint를 받아와야함 --> 현재 사용자가 화면으로 보고 있는 위치 기준이 가장 좋겠지
        defaultMarker.setMapPoint(mapView.getMapCenterPoint());
        //중심을 가져와서 그 주변에 보이는 곳의 말풍선을 다 그려줘야함.
        //중심을 가져왔으니까 화면 크기 계산해서
        //defaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        //defaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);


        mapView.addPOIItem(defaultMarker);
        mapView.selectPOIItem(defaultMarker, true);
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

        //지도가 이동되었기때문에 이동한 위치에 있는 다른 말풍선들이 그려지도록

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        //switch문으로 Zoom레벨별로 축척 계산해서 말풍선 뿌려줘야할듯

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

        //드래그가 끝나면 그려주는걸로?


    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {


        //이거 역시 이동이 끝나면 그려주는 것으로

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
