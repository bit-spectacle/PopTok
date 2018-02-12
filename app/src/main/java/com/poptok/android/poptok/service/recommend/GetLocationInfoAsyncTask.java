package com.poptok.android.poptok.service.recommend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.recommend.RecommendLocationActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.location.LocationList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

/**
 * Created by BIT on 2018-02-09.
 */

public class GetLocationInfoAsyncTask extends AsyncTask<Integer, String, JSONResult<LocationList>> {

    IRecommendFinder recommendFinder;

    IAsyncResultHandler iAsyncResultHandler;

    RecommendLocationActivity recommnedLocationActivity;



    @Override
    protected JSONResult<LocationList> doInBackground(Integer... userNo) {
//        JSONResult<LocationList> result = recommendFinder.getLocationInfo(userNo[0]);
//        return result;
        return null;
    }

    @Override
    protected void onPostExecute(JSONResult<LocationList> result) {
        super.onPostExecute(result);
        //this.friendListActivity.getFriendHandler(result);
//        iAsyncResultHandler.resultHandler(result);
//        this.recommnedLocationActivity
//        this.recommnedLocationActivity.resultHandler(result);
    }



}
