package com.poptok.android.poptok.view.user;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-01-22.
 */

@EBean
public class UserProfile {

    @ViewById
    TextView textProfileNick;

    @ViewById(R.id.profileImage)
    ImageView profileImage;

    @RootContext
    Context context;

    @UiThread
    public void setView(JSONResult<UserInfo> jsonResult) {
        UserInfo userInfo = jsonResult.getData();
        textProfileNick.setText(userInfo.getNickname());

//        profileImage.setImageURI(Uri.parse(jsonResult.getData().getProfileImage()));
//
//        Glide.with(context).load(Uri.parse(jsonResult.getData().getProfileImage()))
//                .apply(new RequestOptions().placeholder(R.drawable.poptok_logo))
//                .into(profileImage);

//                Glide.with(context).load(Uri.parse(jsonResult.getData().getProfileImage()))
//                .into(profileImage);
    }
}
