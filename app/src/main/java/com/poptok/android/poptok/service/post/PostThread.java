package com.poptok.android.poptok.service.post;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.poptok.android.poptok.model.LocationParam;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-01-11.
 */

@EBean
public class PostThread extends Thread {
    public final static int cPostMap = 1;
    public final static int cPostList = 2;

    @RestService
    static IPostItemFinder postItemFinder;

    public static Handler mainHandler;
    public Handler backHandler;

    public void setMainHandler(Handler handler) {
        mainHandler = handler;
    }

    public void run() {
        Looper.prepare();
        backHandler = new PostMessageHandler();
        Looper.loop();
    }

    public static class PostMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message result = new Message();
            switch (msg.what) {
                case PostThread.cPostMap :
                    LocationParam locationParam = (LocationParam)msg.obj;
                    result.obj = postItemFinder.findPostMap(locationParam.top.latitude,locationParam.top.longitude,locationParam.bottom.latitude,locationParam.bottom.longitude,1,6);
                    //result.obj = postItemFinder.findPostMap(37.498918388671875 , 127.03491187792969, 37.490278388671875, 127.02108787792969,1,6);
                    break;
                case PostThread.cPostList :
                    break;
                default:
                    break;
            }

            mainHandler.sendMessage(result);
        }
    }
}
