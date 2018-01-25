package com.poptok.android.poptok.view.user;

import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-01-23.
 */

@EBean
public class ChangeNickname {

    @ViewById(R.id.userNickNameText)
    TextView userNickNameText;

    @ViewById(R.id.userIdText)
    TextView userIdText;

    @UiThread
    public void setView(JSONResult<UserInfo> jsonResult) {
        UserInfo userInfo = jsonResult.getData();
        userNickNameText.setText(userInfo.getNickname());
        userIdText.setText(userInfo.getEmail());
    }
}
