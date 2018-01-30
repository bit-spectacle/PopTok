package com.poptok.android.poptok.service.hash;

import android.os.AsyncTask;

import com.poptok.android.poptok.model.hash.TagItem;

import java.util.List;


public class HashListAsyncTask  extends AsyncTask<Integer, String, List<TagItem>> {

    IHashfinder iHashfinder;
    IHashfindResultHandler iHashfindResultHandler;

    public HashListAsyncTask(IHashfinder iHashfinder, IHashfindResultHandler iHashfindResultHandler) {
        this.iHashfinder = iHashfinder;
        this.iHashfindResultHandler = iHashfindResultHandler;
    }

    @Override
    protected List<TagItem> doInBackground(Integer... integers) {
        List<TagItem> list = iHashfinder.findHashList(integers[0]);
        return list;
    }

    @Override
    protected void onPostExecute(List<TagItem> tagItems) {
        super.onPostExecute(tagItems);
        iHashfindResultHandler.hashFindResultHandler(tagItems);
    }
}
