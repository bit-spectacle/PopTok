package com.poptok.android.poptok.service.user;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserLoginParam;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-01-15.
 */

@EBean
public class UserThread extends Thread {

    public final static int uLogin = 1;
    public final static int uLogout = 2;


    @Bean
    AuthStore authStore;


    @RestService
    static IUserFinder userFinder;

    public Handler mainHandler;
    public Handler backHandler;

    private Context context;

    public void setMainHandler(Handler handler) {
        mainHandler = handler;
    }

    public void setContext(Context context){
        this.context = context;
    }



    public void run(){
        Looper.prepare();
        backHandler = new UserMessageHandler();
        Looper.loop();
    }

    public class UserMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message result = new Message();
            switch (msg.what) {
                case UserThread.uLogin :
                    UserLoginParam userLoginParam = (UserLoginParam)msg.obj;
                    JSONResult<UserInfo> jsonResult = userFinder.userLogin(userLoginParam.email, userLoginParam.password);
                    result.obj = jsonResult;
                    Log.i("handleMessage ", "userLoginParam.email : "+ userLoginParam.email + " / userLoginParam.password " + userLoginParam.password);

                    break;

                case UserThread.uLogout:
                    break;

                default:
                    break;
            }

            mainHandler.sendMessage(result);
        }
    }
}
