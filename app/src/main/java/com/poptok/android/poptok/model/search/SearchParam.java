package com.poptok.android.poptok.model.search;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class SearchParam {
    LatLng top;
    LatLng bottom;
    LatLng center;
    String dateFirst;
    String dateLast;
    String loactionKind;
    String openKind;
    String postKind;
    String searchWord;
    int zoomLevel;

    public LatLng getTop() {
        return top;
    }

    public void setTop(LatLng top) {
        this.top = top;
    }

    public LatLng getBottom() {
        return bottom;
    }

    public void setBottom(LatLng bottom) {
        this.bottom = bottom;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public String getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(String dateFirst) {
        this.dateFirst = dateFirst;
    }

    public String getDateLast() {
        return dateLast;
    }

    public void setDateLast(String dateLast) {
        this.dateLast = dateLast;
    }

    public String getLoactionKind() {
        return loactionKind;
    }

    public void setLoactionKind(boolean chkMyLocation, boolean chkEnjoy, boolean chkLand,
                                boolean chkLife, boolean chkStore, boolean chkMotel,
                                boolean chkFood, boolean chkMedic, boolean chkEdu) {
        if(chkMyLocation && chkEnjoy && chkLand &&
                chkLife && chkStore && chkMotel &&
                chkFood && chkMedic && chkEdu) {
            this.loactionKind = "ALL";
        }
        else {
            String result = "";
            if(chkMyLocation) {
                result += "MY";
            }
            if(chkEnjoy) result = addMoreOption(result, "N", ",");
            if(chkLand) result = addMoreOption(result, "L", ",");
            if(chkLife) result = addMoreOption(result, "F", ",");
            if(chkStore) result = addMoreOption(result, "D", ",");
            if(chkMotel) result = addMoreOption(result, "O", ",");
            if(chkFood) result = addMoreOption(result, "Q", ",");
            if(chkMedic) result = addMoreOption(result, "S", ",");
            if(chkEdu) result = addMoreOption(result, "R", ",");
        }
    }
    
    private String addMoreOption(String insert, String option, String seperator) {
        if(seperator.length() > 0 && insert.length() > 0) {
            insert += seperator;
        }
        insert += option;
        return insert;
    }

    public String getOpenKind() {
        return openKind;
    }

    public void setOpenKind(boolean isFriend) {
        this.openKind = isFriend ? "ALL" : "FRIEND";
    }

    public String getPostKind() {
        return postKind;
    }

    public void setPostKind(String postKind) {
        this.postKind = postKind;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public void setLoactionKind(String loactionKind) {
        this.loactionKind = loactionKind;
    }

    public void setOpenKind(String openKind) {
        this.openKind = openKind;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public void setGoogleLocation(GoogleMap googleMap) {
        Projection projection = googleMap.getProjection();
        LatLngBounds bounds = projection.getVisibleRegion().latLngBounds;
        this.center = googleMap.getCameraPosition().target;
        this.top = bounds.northeast;
        this.bottom = bounds.southwest;
        this.zoomLevel = (int)googleMap.getCameraPosition().zoom;
    }
}
