package com.poptok.android.poptok.controller.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.user_login)
public class LoginActivity extends AppCompatActivity {

    private static String API_KEY="f1ce1afe4bd1995ca9fe8c5957fcd78b";

    @ViewById(R.id.poptokTextView)
    TextView poptokTextView;


    @Click
    public void loginButton(View v){
        //db랑 연결해서 회원인지 확인하는 것이 필요.
        //회원이 아니거나 비밀번호가 다를 경우 확인해달라는 toast 띄워야함
        Log.d("loginbutton", "loginButton");
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
