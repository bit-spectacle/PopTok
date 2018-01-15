package com.poptok.android.poptok.service.user;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.poptok.android.poptok.model.UserLogin;

/**
 * Created by BIT on 2018-01-15.
 */

@EBean
public class UserThread extends Thread {

    public final static int uLogin = 1;


    @RestService
    static UserFinder userFinder;

    public static Handler mainHandler;
    public Handler backHandler;

    public void setMainHandler(Handler handler) {
        mainHandler = handler;
    }

    public void run(){
        Looper.prepare();
        backHandler = new UserMessageHandler();
        Looper.loop();
    }

    public static class UserMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message result = new Message();
            switch (msg.what) {
                case UserThread.uLogin :
                    UserLogin userLogin = (UserLogin)msg.obj;
                    result.obj = userFinder.userLogin("super@poptok.com", "asdf");
                    break;

                default:
                    break;
            }

            mainHandler.sendMessage(result);
        }
    }
}
