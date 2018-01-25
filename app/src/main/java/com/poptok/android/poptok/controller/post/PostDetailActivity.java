package com.poptok.android.poptok.controller.post;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.service.post.IPostItemFinder;
import com.poptok.android.poptok.service.post.PostDetailAsyncTask;
import com.poptok.android.poptok.view.post.PostDetail;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.post_detail)
public class PostDetailActivity extends AppCompatActivity {

    @Extra
    int postNo;

    @RestService
    IPostItemFinder postItemFinder;

    @Bean
    PostDetail postDetail;

    PostDetailAsyncTask postDetailAsyncTask;

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        postDetail.setActivity(this);
        postDetailAsyncTask = new PostDetailAsyncTask(this, postItemFinder);
        postDetailAsyncTask.execute(postNo);
    }

    public void setView(PostItem postItem) {
        postDetail.setView(postItem);
    }


    @Click(R.id.btnDetailBack)
    void btnDetailBackClicked() {
        this.onBackPressed();
    }

    @Click({R.id.imageDetail, R.id.imageDetailBack})
    void imageDetailClicked() {
        postDetail.toggleDetail();
    }

}
