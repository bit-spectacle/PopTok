package com.poptok.android.poptok.view.recommend;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.location.LocationList;
import com.poptok.android.poptok.service.recommend.IRecommendFinder;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIT on 2018-02-07.
 */

@EBean
public class RecommendLocationAdapter  extends BaseAdapter {

    private List<LocationList> items;

    @RestService
    IRecommendFinder recommendFinder;

    @RootContext
    Context context;

//    @Bean
//    RecommendLocationStore recommendLocationStore;

    @Bean
    AuthStore authStore;

    public RecommendLocationAdapter(){
        items = new ArrayList<LocationList>();
    }

    public void setItems(List<LocationList> items){
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("RecommendAdapter", " : getView() called");

        RecommendLocationView itemView;
        LocationList locationList = getItem(position);

        if(convertView == null){
            itemView = RecommendLocationView_.build(context);
        }
        else{
            itemView = (RecommendLocationView)convertView;
        }

//        itemView.bind(locationList);
        itemView.bind(getItem(position));
        return itemView;

        //recommendRelativeLayout
//        LinearLayout recommendRelativeLayout = (LinearLayout)convertView.findViewById(R.id.recommendRelativeLayout);

//        double latitude = locationList.getLatitude();
//        double longitude = locationList.getLongitude();

//        RecommendLocationParam locationParam = new RecommendLocationParam(locationList.getLatitude(), locationList.getLongitude());
//        recommendRelativeLayout.setTag(locationList.getLocationNo());





    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public LocationList getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


}
