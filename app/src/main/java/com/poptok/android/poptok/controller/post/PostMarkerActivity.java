package com.poptok.android.poptok.controller.post;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.poptok.android.poptok.R;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by BIT on 2017-12-26.
 */

public class PostMarkerActivity extends FragmentActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener {

    private MapView mMapView;
    private MapPOIItem multiMarker;
    private MapPOIItem singleMarker;
    private MapPOIItem friendMarker;
    private MapPOIItem secretMarker;

    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter{
        private final View calloutBalloon;

        public CustomCalloutBalloonAdapter() {
            calloutBalloon = getLayoutInflater().inflate(R.layout.post_custom_callout_balloon, null);
        }
        @Override
        public View getCalloutBalloon(MapPOIItem poiItem){

            return calloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
            //여기서 클릭한게 뭔지 알아내서 리스트를 보여줘야함
            return null;
        }

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

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
