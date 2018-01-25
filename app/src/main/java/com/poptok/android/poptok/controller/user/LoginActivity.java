package com.poptok.android.poptok.controller.user;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.AppBaseActivity;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.model.user.UserLoginParam;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.service.user.UserThread;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.user_login)
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LoginActivity : ";

    @ViewById(R.id.userNameEditText)
    EditText userNameEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @Bean
    UserThread userThread;

    @Bean
    AuthStore authStore;

    Context context;
    InputMethodManager imm;

    @AfterViews
    void init(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        context = this.getApplicationContext();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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
                authStore.setUserInfo(jsonResult.getData());
                goToMain();
            }
            else {
                hideKeyboard();
                Toast.makeText(getApplicationContext(), "로그인 실패. 다시 한번 확인해주세요.", Toast.LENGTH_LONG).show();
            }

        }
    };

    private class JSONResultLogin extends JSONResult<UserInfo> {}


    private void hideKeyboard()
    {
        imm.hideSoftInputFromWindow(userNameEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
    }

    private void goToMain() {
        Intent intent = new Intent(this, AppBaseActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Click(R.id.loginLayout)
    public void userLoginLayoutClicked(View v) {
        hideKeyboard();
    }

    @Click
    public void loginButton(View v){

        String email = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(email == null || email.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_LONG).show();
            userNameEditText.requestFocus();
            return;
        }

        if(password == null || password.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
            passwordEditText.requestFocus();
            return;
        }

        UserLoginParam userLoginParam = UserLoginParam.getUserLogin(email, password);
        Message.obtain(userThread.backHandler, UserThread.uLogin, userLoginParam).sendToTarget();
        hideKeyboard();
    }

    @Click
    public void cancelButton(View v){
        goToMain();
    }

    @Override
    public void onBackPressed() {
        goToMain();
    }

    @Click
    public void joinText(View v){

        JoinActivity_.intent(this).start();
    }

    @Click
    public void findIdText(View v){

    }

    @Click
    public void findPasswordText(View v){

    }



}
