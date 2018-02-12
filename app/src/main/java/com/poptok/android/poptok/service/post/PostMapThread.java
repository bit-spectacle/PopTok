package com.poptok.android.poptok.service.post;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.android.gms.maps.model.LatLng;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.search.SearchParam;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-01-11.
 */

@EBean
public class PostMapThread extends Thread {
    public final static int cPostMap = 1;
    public final static int cPostList = 2;
    public final static int cPostGet = 3;

    @RestService
    IPostItemFinder postItemFinder;

    @Bean
    AuthStore authStore;

    public Handler mainHandler;
    public Handler backHandler;

    public void setMainHandler(Handler handler) {
        mainHandler = handler;
    }

    public void run() {
        Looper.prepare();
        backHandler = new PostMessageHandler();
        Looper.loop();
    }

    public class PostMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message result = new Message();
            switch (msg.what) {
                case PostMapThread.cPostMap :
//                    RecommendLocationParam locationParam = (RecommendLocationParam)msg.obj;
//                    int userNo = authStore.isLogin() ? authStore.getUserInfo().getUserNo() : 0;
//                    result.obj = postItemFinder.findPostMap(locationParam.top.latitude,locationParam.top.longitude,locationParam.bottom.latitude,locationParam.bottom.longitude,1, userNo);

                    SearchParam searchParam = (SearchParam)msg.obj;
                    int userNo = authStore.isLogin() ? authStore.getUserInfo().getUserNo() : 0;
                    LatLng top = searchParam.getTop();
                    LatLng bot = searchParam.getBottom();
                    result.obj = postItemFinder.findPostMap(top.latitude, top.longitude, bot.latitude, bot.longitude,1, userNo);
                    break;
                case PostMapThread.cPostList :
                    break;
                case PostMapThread.cPostGet:
                    int postNo = (int)msg.obj;
                    result.obj = postItemFinder.findPost(postNo);
                    break;
                default:
                    break;
            }

            if(mainHandler != null) {
                mainHandler.sendMessage(result);
            }
        }
    }
}
