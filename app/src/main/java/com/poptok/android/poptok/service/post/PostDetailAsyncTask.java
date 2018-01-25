package com.poptok.android.poptok.service.post;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.post.PostDetailActivity;
import com.poptok.android.poptok.model.post.PostItem;

public class PostDetailAsyncTask extends AsyncTask<Integer, String, PostItem> {

    IPostItemFinder postItemFinder;

    PostDetailActivity postDetailActivity;

    public PostDetailAsyncTask(PostDetailActivity activity, IPostItemFinder postItemFinder) {
        this.postDetailActivity = activity;
        this.postItemFinder = postItemFinder;
    }

    @Override
    protected PostItem doInBackground(Integer... postNo) {
        PostItem postItem = postItemFinder.findPost(postNo[0]);
        return  postItem;
    }

    @Override
    protected void onPostExecute(PostItem postItem) {
        super.onPostExecute(postItem);
        this.postDetailActivity.setView(postItem);
    }
}
