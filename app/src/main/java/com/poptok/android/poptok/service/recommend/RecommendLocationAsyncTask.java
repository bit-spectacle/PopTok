package com.poptok.android.poptok.service.recommend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.recommend.RecommendLocationActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.location.LocationList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

/**
 * Created by BIT on 2018-02-08.
 */

public class RecommendLocationAsyncTask extends AsyncTask<Integer, String, JSONResult<List<LocationList>>> {

    IRecommendFinder recommendFinder;

    IAsyncResultHandler iAsyncResultHandler;

    RecommendLocationActivity recommendLocationActivity;

    public RecommendLocationAsyncTask(RecommendLocationActivity activity, IRecommendFinder recommendFinder){
        this.recommendLocationActivity = activity;
        this.recommendFinder = recommendFinder;
    }


    @Override
    protected JSONResult<List<LocationList>> doInBackground(Integer... userNo) {
        JSONResult<List<LocationList>> result = recommendFinder.getRecommendLocation(userNo[0]);

        return result;
    }

    @Override
    protected void onPostExecute(JSONResult<List<LocationList>> result) {
        super.onPostExecute(result);
        this.recommendLocationActivity.resultHandler(result);
    }

}
