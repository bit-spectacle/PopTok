package com.poptok.android.poptok.service.post;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.model.search.SearchParam;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

public class PostListAsyncTask extends AsyncTask<SearchParam, String , List<PostListItem>> {

    IPostItemFinder postItemFinder;
    IAsyncResultHandler<List<PostListItem>> iAsyncResultHandler;

    public PostListAsyncTask(IPostItemFinder postItemFinder, IAsyncResultHandler<List<PostListItem>> iAsyncResultHandler) {
        this.postItemFinder = postItemFinder;
        this.iAsyncResultHandler = iAsyncResultHandler;
    }

    @Override
    protected List<PostListItem> doInBackground(SearchParam... searchParams) {
        LatLng top = searchParams[0].getTop();
        LatLng bot = searchParams[0].getBottom();
        List<PostListItem> items = postItemFinder.findPostList(top.latitude, top.longitude, bot.latitude, bot.longitude);
        return items;
    }

    @Override
    protected void onPostExecute(List<PostListItem> postListItems) {
        super.onPostExecute(postListItems);
        iAsyncResultHandler.resultHandler(postListItems);
    }
}
