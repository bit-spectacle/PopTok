//package com.poptok.android.poptok.controller.post;
//
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.os.Handler;
//import android.os.Message;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import com.poptok.android.poptok.R;
//import com.poptok.android.poptok.model.location.LocationParam;
//import com.poptok.android.poptok.model.post.PostMapItem;
//import com.poptok.android.poptok.service.post.PostThread;
//
//import net.daum.mf.map.api.MapPOIItem;
//import net.daum.mf.map.api.MapPoint;
//import net.daum.mf.map.api.MapView;
//
//import org.androidannotations.annotations.AfterViews;
//import org.androidannotations.annotations.Bean;
//import org.androidannotations.annotations.EFragment;
//import org.androidannotations.annotations.ViewById;
//
//import java.util.List;
//
//@EFragment(R.layout.activity_drawmap)
//public class PostMapFragment extends Fragment
//        implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener, MapView.POIItemEventListener{
//
//    private static final String LOG_TAG = "PostMapActivity : ";
//
//
//    @ViewById(R.id.map_view)
//    ViewGroup mapViewContainer;
//
//    @ViewById(R.id.trackingOffButton)
//    Button trackingOffButton;
//
//    @ViewById(R.id.trackingOnButton)
//    Button trackingOnButton;
//
//    @Bean
//    PostThread postThread;
//
//    @Bean
//    DefaultCalloutBalloonAdapter defaultCalloutBalloonAdapter;
//
//    MapView mapView;
//
//    @AfterViews
//    public void init() {
//
//        Activity activity = this.getActivity();
//        mapView = new MapView(activity);
//
//        MapView.CurrentLocationEventListener mCurrentLocationEventListener = null;
//        onMapViewInitialized(mapView);
//
//        trackingOffButton.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Log.i(LOG_TAG, "trackingOffButton Clicked");
//                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//                mapView.setShowCurrentLocationMarker(false);
//            }
//        });
//
//        trackingOnButton.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Log.i(LOG_TAG, "trackingOnButton Clicked");
//                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//
//                DisplayMetrics metrics = v.getResources().getDisplayMetrics();
//                int width = metrics.widthPixels;
//                int height = metrics.heightPixels;
//
//                int zoomLevel = mapView.getZoomLevel();
//                MapPoint center = mapView.getMapCenterPoint();
//                MapPoint.GeoCoordinate gc = center.getMapPointGeoCoord();
//
//                LocationParam locationParam = LocationParam.getLoacationParam(zoomLevel, width, height, gc.latitude,  gc.longitude );
//                Log.i(LOG_TAG,
//                        String.format("display size check %f %f %f %f",
//                                locationParam.top.latitude, locationParam.top.longitude,
//                                locationParam.bottom.latitude, locationParam.bottom.longitude) );
//
//                Message.obtain(postThread.backHandler, PostThread.cPostMap, locationParam).sendToTarget();
//            }
//        });
//
//        if(mapViewContainer != null) {
//            mapViewContainer.addView(mapView);
//        }
//
//        mapView.setCalloutBalloonAdapter(defaultCalloutBalloonAdapter);
//
//        postThread.setMainHandler(postDataHandler);
//        postThread.setDaemon(true);
//        postThread.start();
//    }
//
//    // postThread에서 postMap data가 넘어오면 UI 처리를 해준다.
//    private Handler postDataHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            List<PostMapItem> list = (List<PostMapItem>)msg.obj;
//            Log.d(LOG_TAG, String.format("postDataHandler size: %d", list.size()));
//
//            for(int i=0; i< list.size(); i++) {
//                createCustomMarker(mapView, list.get(i));
//            }
//        }
//    };
//
//    private void createCustomMarker(MapView mapView, PostMapItem item) {
//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName(item.getContent());
//        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(item.getLatitude(), item.getLongitude()));
//        marker.setUserObject(item);
//
//        mapView.addPOIItem(marker);
//        mapView.selectPOIItem(marker, false);
//    }
//
//
//    public void onMapViewInitialized(MapView mapView) {
//
//        //DAUM 지도 api를 사용하기 위해서 REST KEY를 사용해준다.
//        mapView.setDaumMapApiKey("f1ce1afe4bd1995ca9fe8c5957fcd78b");
//        //최근 위치를 알아내기
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        //말풍선을 띄우기 위해서 줌 레벨을 1로 준다. 이때 경도 위도는 최근 위치로 받아올 것이기 때문에 의미가 없는 숫자.
//        //경도 위도를 따로 얻어오는 기본 안드로이드 함수를 사용하면 경도 위도도 현재 위치로 설정할 수 있지만 그건 차후에 하도록한다.
//        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 12, false);
//        //단순히 잘 찍혔는지 로그를 확인하기 위함.
//        //mapView.setShowCurrentLocationMarker(true);
//
//
//        Log.i(LOG_TAG, "onMapViewInitialized");
//
//        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
//
//    }
//
//    @Override
//    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {
//        Log.i(LOG_TAG, "onMapViewCenterPointMoved() call");
////        MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
////        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
////        mapView.setShowCurrentLocationMarker(false);
////        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
//
//
//    }
//
//    @Override
//    public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
//
//        Log.i(LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));
//
//    }
//
//    @Override
//    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewSingleTapped() call");
//    }
//
//    @Override
//    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewDoubleTapped() call");
//    }
//
//    @Override
//    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewLongPressed() call");
//    }
//
//    @Override
//    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewCenterPointMoved() call");
//        //위치 트래킹 모드 off
////        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
////        mapView.setShowCurrentLocationMarker(false);
////        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
////
////        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
//
//    }
//
//    @Override
//    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewDragEnded() call");
//        //위치 트래킹모드 off
////        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
////        mapView.setShowCurrentLocationMarker(false);
////        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
////
////        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
//    }
//
//    @Override
//    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
//        Log.i(LOG_TAG, "onMapViewMoveFinished() call");
//        //위치 트래킹모드 off
////        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
////        mapView.setShowCurrentLocationMarker(false);
////        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
////
////        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude), false);
//    }
//
//    @Override
//    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
//
//        Log.i(LOG_TAG, String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
//    }
//
//    @Override
//    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
//
//    }
//
//    @Override
//    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
//
//    }
//
//    @Override
//    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
//
//    }
//
//    @Override
//    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
//
//    }
//}
