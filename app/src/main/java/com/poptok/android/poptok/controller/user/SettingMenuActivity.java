package com.poptok.android.poptok.controller.user;

import android.support.v7.app.AppCompatActivity;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_settingmenu)
public class SettingMenuActivity extends AppCompatActivity {

    @AfterViews
    void init(){
        //중복확인 및 비밀번호 체크
    }
}
