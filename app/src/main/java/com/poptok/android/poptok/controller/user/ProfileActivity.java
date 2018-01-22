package com.poptok.android.poptok.controller.user;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.JSONResult;
import com.poptok.android.poptok.service.user.UserProfileAsyncTask;
import com.poptok.android.poptok.view.user.UserProfile;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-01-22.
 */


@EActivity(R.layout.user_profile)
public class ProfileActivity extends AppCompatActivity {


    @RestService
    IUserFinder userFinder;

    @Bean
    UserProfile userProfile;

    UserProfileAsyncTask userProfileAsyncTask;

    @Bean
    AuthStore authStore;

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        userProfileAsyncTask = new UserProfileAsyncTask(this, userFinder);
        userProfileAsyncTask.execute(authStore.getUserInfo().getEmail());
    }

    public void setView(JSONResult<UserInfo> jsonResult) {
        userProfile.setView(jsonResult);
    }
}
