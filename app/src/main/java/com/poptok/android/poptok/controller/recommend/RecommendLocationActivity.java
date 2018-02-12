package com.poptok.android.poptok.controller.recommend;

import android.content.Intent;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.location.LocationList;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.recommend.IRecommendFinder;
import com.poptok.android.poptok.service.recommend.RecommendLocationAsyncTask;
import com.poptok.android.poptok.view.recommend.RecommendLocationAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

/**
 * Created by BIT on 2018-02-06.
 */


@EActivity(R.layout.user_recommendplace)
public class RecommendLocationActivity extends BaseActivity implements IAsyncResultHandler<JSONResult<List<LocationList>>> {

    @Bean
    AuthStore authStore;

//    @Bean
//    RecommendLocationStore recommendLocationStore;

    @Bean
    RecommendLocationAdapter recommendLocationAdapter;

    @RestService
    IRecommendFinder recommendFinder;

//    @ViewById(R.id.recommendPlaceGridView)
//    ListView recommendPlaceGridView;

    @ViewById(R.id.recommendPlaceGridView)
    GridView recommendPlaceGridView;


    RecommendLocationAsyncTask recommendLocationAsyncTask;
    int tag = 0;

    @AfterViews
    public void init(){

        recommendLocationAsyncTask = new RecommendLocationAsyncTask(this, recommendFinder);
        recommendLocationAsyncTask.execute(authStore.getUserInfo().getUserNo());


    }

//    @Click(R.id.recommendRelativeLayout)
//    public void recommendRelativeLayout(){
//        tag = Integer.parseInt(recommendPlaceGridView.getTag().toString());
//
//        recommendLocationAsyncTask = new RecommendLocationAsyncTask(this, recommendFinder);
//        recommendLocationAsyncTask.execute(authStore.getUserInfo().getUserNo());
//
//    }



    @Override
    public void resultHandler(JSONResult<List<LocationList>> result) {

        if(result.getCode().equals("FAIL")){
            Toast.makeText(getApplicationContext(), "추천 장소가 없습니다.", Toast.LENGTH_LONG).show();
        }else if(result.getCode().equals("SUCC")){
            Toast.makeText(getApplicationContext(), "추천 장소가 있습니다.", Toast.LENGTH_LONG).show();
            Log.i("result.getData() : " , ""+result.getData());
            recommendLocationAdapter.setItems(result.getData());
            recommendPlaceGridView.setAdapter(recommendLocationAdapter);
        }
    }

//    public void getLocationInfo(JSONResult<LocationList> result){
//
//        if(result.getCode().equals("FAIL")){
//            Toast.makeText(getApplicationContext(), "추천 장소 보여주기 실패.", Toast.LENGTH_LONG).show();
//        }else if(result.getCode().equals("SUCC")){
//            Toast.makeText(getApplicationContext(), "추천 장소 보여주기 성공.", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(this, RecommendMapActivity.class);
//            startActivity(intent);
//        }
//    }

    @ItemClick(R.id.recommendPlaceGridView)
    void listItemClicked(LocationList locationList){
        Intent intent = new Intent(this, RecommendMapActivity_.class);
        intent.putExtra("LocationLatitude", locationList.getLatitude());
        intent.putExtra("LocationLongitude", locationList.getLongitude());
        intent.putExtra("LocationName", locationList.getBusinessName());
        startActivity(intent);
    }


}
