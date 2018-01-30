package com.poptok.android.poptok.controller.friend;

import android.app.Fragment;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import org.androidannotations.annotations.EFragment;

import java.util.List;

/**
 * Created by BIT on 2018-01-27.
 */

@EFragment(R.layout.user_myfriend)
public class MyFriendFragment extends Fragment implements IAsyncResultHandler<List<PostListItem>> {

    @Override
    public void resultHandler(List<PostListItem> result) {

    }
}
