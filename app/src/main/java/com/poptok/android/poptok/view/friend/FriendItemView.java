package com.poptok.android.poptok.view.friend;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.user.FriendList;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-01-27.
 */

@EViewGroup(R.layout.user_friendprofile)
public class FriendItemView extends LinearLayout {

    @ViewById(R.id.friendProfileImage)
    ImageView friendImage;

    @ViewById(R.id.friendNameTextView)
    TextView friendName;

    @ViewById(R.id.friendStatusTextView)
    TextView friendStatus;

    @ViewById(R.id.profileButton)
    Button profileButton;




    public FriendItemView(Context context) {
        super(context);
    }

    public void bind(FriendList friendList){
        Log.i("FriendItemView : ", "bind" );
//
        friendStatus.setText(friendList.getStatus());
        friendName.setText(friendList.getNickname());

//        friendName.setText("Test");
        Glide.with(this).load(friendList.getProfileImage())
                .apply(new RequestOptions().placeholder(R.drawable.poptok_logo))
                .into(friendImage);

    }

}
