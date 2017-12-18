package com.poptok.android.poptok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static String API_KEY="f1ce1afe4bd1995ca9fe8c5957fcd78b";

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
       super.onCreate(saveInstanceState);

       setContentView(R.layout.activity_main);

    }

    public void loginButtonClick(View v){
        //db랑 연결해서 회원인지 확인하는 것이 필요.
        //회원이 아니거나 비밀번호가 다를 경우 확인해달라는 toast 띄워야함

    }

    public void cancelButtonClick(View v){
        //이게 여기 있을 필요가 있는가?

    }

    public void joinTextClick(View v){

        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);

    }

    public void findIdTextClick(View v){

    }

    public void findPasswordTextClick(View v){

    }

}
