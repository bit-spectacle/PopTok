package com.poptok.android.poptok.controller.user;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.AppBaseActivity;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.model.user.UserLoginParam;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.service.user.JSONResult;
import com.poptok.android.poptok.service.user.UserThread;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.user_login)
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LoginActivity : ";

    @ViewById(R.id.poptokTextView)
    TextView poptokTextView;

    @ViewById(R.id.userNameEditText)
    EditText userNameEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @Bean
    UserThread userThread;

    @Bean
    AuthStore authStore;

    Context context;

    @AfterViews
    void init(){
        context = this.getApplicationContext();
        userThread.setMainHandler(loginHandler);
        userThread.setDaemon(true);
        userThread.start();
    }

    private Handler loginHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            JSONResult<UserInfo> jsonResult = (JSONResult)msg.obj;
            Log.i(LOG_TAG, "loginHandler called() ");

            if(jsonResult.getCode().equals("SUCC")){
                authStore.setSessionID(jsonResult.getSessionId());
                authStore.setUserInfo(jsonResult.getData());

                Intent intent = new Intent(context, AppBaseActivity.class);
                startActivity(intent);
            }
            else {

            }

        }
    };

    private class JSONResultLogin extends JSONResult<UserInfo> {}




    @Click
    public void loginButton(View v){
        //db랑 연결해서 회원인지 확인하는 것이 필요.
        //회원이 아니거나 비밀번호가 다를 경우 확인해달라는 toast 띄워야함

//        String Email  = UserInfo.class.get
//        Log.i(LOG_TAG, ""+ UserInfo.getEmail().toString());
//        Log.i(LOG_TAG, String.format(UserInfo.getEmail(), ""));

        String email = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserLoginParam userLoginParam = UserLoginParam.getUserLogin(email, password);

        Message.obtain(userThread.backHandler, UserThread.uLogin, userLoginParam).sendToTarget();
        //Message.obtain(UserThread.uLogin, userLoginParam).sendToTarget();

        //Log.i("loginbutton", "" + email + " " + password);
    }

    @Click
    public void cancelButton(View v){
        //이게 여기 있을 필요가 있는가?

    }

    @Click
    public void joinText(View v){

        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);

    }

    @Click
    public void findIdText(View v){

    }

    @Click
    public void findPasswordText(View v){

    }



}
