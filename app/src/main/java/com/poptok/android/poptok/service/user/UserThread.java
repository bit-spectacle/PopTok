package com.poptok.android.poptok.service.user;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.auth.CookieStore;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.model.user.UserParam;

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
    public final static int uCheckNickname = 3;
    public final static int uChangeNickname = 4;


    @Bean
    AuthStore authStore;

    @Bean
    CookieStore cookieStore;


    @RestService
    static IUserFinder userFinder;

    public Handler mainHandler;
    public Handler backHandler;
    public Handler changeNickHandler;

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
        changeNickHandler = new UserMessageHandler();
        Looper.loop();
    }

    public class UserMessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message result = new Message();

            switch (msg.what) {
                case UserThread.uLogin :
                    UserParam userLoginParam = (UserParam)msg.obj;
                    JSONResult<UserInfo> jsonResult = userFinder.userLogin(userLoginParam.email, userLoginParam.password);
                    result.obj = jsonResult;
                    Log.i("handleMessage ", "userParam.email : "+ userLoginParam.email + " / userParam.password " + userLoginParam.password);
                    break;

                case UserThread.uLogout:
                    cookieStore.clear();
                    authStore.setUserInfo(null);
                    break;

//                case UserThread.uCheckNickname:
//                    int userNo = (int)msg.obj;
//                    Log.i("uCheckNick" , ""+userNo);
//                    UserParam userCheckNicknameParam = (UserParam)msg.obj;
//                    JSONResult<Integer> check = userFinder.checkNickname(userCheckNicknameParam.nickname);
//                    result.obj = check;
//                    Log.i("handleMessage ", "userParam.email : "+ userCheckNicknameParam.nickname);
//                    Log.i("uCheckNick", ""+msg.obj);

//                    break;
//                case UserThread.uChangeNickname:
//                    UserParam userChangeNicknameParam = (UserParam)msg.obj;
//                    jsonResult = userFinder.changeNickname(userChangeNicknameParam.nickname, userChangeNicknameParam.userNo);
//                    result.obj = jsonResult;
//                    Log.i("handleMessage ", "userParam.email : "+ userChangeNicknameParam.nickname +"userParam.userNo : " + userChangeNicknameParam.userNo);
//                    break;

                default:
                    break;
            }

            mainHandler.sendMessage(result);
        }
    }
}
