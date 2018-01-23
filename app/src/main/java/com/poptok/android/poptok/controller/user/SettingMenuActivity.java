package com.poptok.android.poptok.controller.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.TextView;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_settingmenu)
public class SettingMenuActivity extends AppCompatActivity {

    @ViewById(R.id.changeProfileText)
    TextView changeProfileText;

    @ViewById(R.id.changePasswordText)
    TextView changePasswordText;

    @ViewById(R.id.allAlramSwitch)
    Switch allAlarmSwitch;

    @ViewById(R.id.friendPostingAlramSwitch)
    Switch friendPostingAlramSwitch;

    @ViewById(R.id.commentAlramSwitch)
    Switch commentAlramSwitch;

    @ViewById(R.id.gpsSwitch)
    Switch gpsSwitch;

    @AfterViews
    void init(){

//        changeProfileText.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(this,ProfileActivity_.class);
//                startActivity(intent);
//            }
//        });
    }

    @Click
    public void changeProfileText(){
        Intent intent = new Intent(this,ProfileActivity_.class);
        startActivity(intent);
    }






//
//    public void changeProfileText(){
////        Intent intent = new Intent(this, ProfileActivity.class);
////        startActivity(intent);
//        //LoginActivity_.intent(this.getActivity()).start();
//        //ProfileActivity.intent(this).start();
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
//    }

}
