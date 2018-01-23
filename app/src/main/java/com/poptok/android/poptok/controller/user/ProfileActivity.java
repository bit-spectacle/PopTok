package com.poptok.android.poptok.controller.user;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.JSONResult;
import com.poptok.android.poptok.service.user.UserProfileAsyncTask;
import com.poptok.android.poptok.view.user.UserProfile;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BIT on 2018-01-22.
 */


@EActivity(R.layout.user_profile)
public class ProfileActivity extends AppCompatActivity {

    @ViewById(R.id.profileImage)
    CircleImageView profileImage;

    @ViewById(R.id.userNickNameTextView)
    TextView userNicknameTextView;

    @ViewById(R.id.userStatusTextView)
    TextView userStatusTextView;

    @ViewById(R.id.changeImageButton)
    Button changeImageButton;

    @ViewById(R.id.changeStatusButton)
    Button changeStatusButton;

    @ViewById(R.id.changeNickNameButton)
    Button changeNickNameButton;

    @ViewById(R.id.backButton)
    Button backButton;

    @RestService
    IUserFinder userFinder;

    @Bean
    UserProfile userProfile;

    UserProfileAsyncTask userProfileAsyncTask;

    @Bean
    AuthStore authStore;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        //profileImage.setImageDrawable((getResources().getDrawable(R.drawable.default_profile)));
    }


    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        userProfileAsyncTask = new UserProfileAsyncTask(this, userFinder);
        userProfileAsyncTask.execute(authStore.getUserInfo().getEmail());
    }

    @Click
    public void backButton(){
        onBackPressed();
    }

    @Click
    public void changeImageButton(){
        //권한체크 후 갤러리 실행


    }

    @Click
    public void changeStatusButton(){
        //API 호출해줘야함
    }

    @Click
    public void changeNickNameButton(){

    }

    public void setView(JSONResult<UserInfo> jsonResult) {
        userProfile.setView(jsonResult);
    }
}
