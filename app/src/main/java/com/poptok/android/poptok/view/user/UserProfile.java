package com.poptok.android.poptok.view.user;

import android.widget.TextView;

import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.model.JSONResult;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-01-22.
 */

@EBean
public class UserProfile {

    @ViewById
    TextView textProfileNick;

    @UiThread
    public void setView(JSONResult<UserInfo> jsonResult) {
        UserInfo userInfo = jsonResult.getData();
        textProfileNick.setText(userInfo.getNickname());
    }
}
