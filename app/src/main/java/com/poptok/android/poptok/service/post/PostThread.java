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
    public final static int cPostGet = 3;

    @RestService
    IPostItemFinder postItemFinder;

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
                case PostThread.cPostMap :
                    LocationParam locationParam = (LocationParam)msg.obj;
                    result.obj = postItemFinder.findPostMap(locationParam.top.latitude,locationParam.top.longitude,locationParam.bottom.latitude,locationParam.bottom.longitude,1,6);
                    break;
                case PostThread.cPostList :
                    break;
                case PostThread.cPostGet:
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
