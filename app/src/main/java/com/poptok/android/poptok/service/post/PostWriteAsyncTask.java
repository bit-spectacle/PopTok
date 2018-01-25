package com.poptok.android.poptok.service.post;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.post.PostWriteActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.post.PostWriteParam;


public class PostWriteAsyncTask extends AsyncTask<PostWriteParam, String, JSONResult<Integer>> {
    IPostItemFinder postItemFinder;
    PostWriteActivity postWriteActivity;

    public PostWriteAsyncTask(IPostItemFinder postItemFinder, PostWriteActivity postWriteActivity) {
        this.postItemFinder = postItemFinder;
        this.postWriteActivity = postWriteActivity;
    }

    @Override
    protected JSONResult<Integer> doInBackground(PostWriteParam... postWriteParams) {
        JSONResult<Integer> jsonResult = postItemFinder.writePost(postWriteParams[0]);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.postWriteActivity.writeResultHandler(jsonResult);
    }
}
