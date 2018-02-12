package com.poptok.android.poptok.controller.recommend;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import org.androidannotations.annotations.EActivity;

import java.util.List;

/**
 * Created by BIT on 2018-02-06.
 */


@EActivity(R.layout.user_recommendfriend)
public class RecommendFriendActivity extends BaseActivity implements IAsyncResultHandler<JSONResult<List<FriendList>>>{









    @Override
    public void resultHandler(JSONResult<List<FriendList>> result) {

    }
}
