package com.poptok.android.poptok.controller.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.auth.AuthStore_;
import com.poptok.android.poptok.model.setting.PoptokAppSettings;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_settingmenu)
public class SettingMenuActivity extends AppCompatActivity {

    private static final String TAG = "SettingMenuActivity";
    @Bean
    PoptokAppSettings poptokAppSettings;

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
    void init() {

    }

    @Click
    public void changeProfileText() {
        AuthStore authStore = AuthStore_.getInstance_(this.getApplicationContext());
        if (authStore.isLogin() == false) {
            LoginActivity_.intent(this).start();
            Toast.makeText(getApplicationContext(), "로그인을 해주세요.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, ProfileActivity_.class);
            startActivity(intent);
        }
    }

    @CheckedChange(R.id.gpsSwitch)
    public void gpsSwitchClicked(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, String.format("%s", isChecked));
        poptokAppSettings.setGpson(isChecked);
    }

}
