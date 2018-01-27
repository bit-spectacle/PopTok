package com.poptok.android.poptok.service.store;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.poptok.android.poptok.model.search.SearchParam;
import com.poptok.android.poptok.model.store.StoreItem;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;


public class StoreListAsyncTask extends AsyncTask<SearchParam, String, List<StoreItem>> {

    IStoreFinder iStoreFinder;
    IAsyncResultHandler iAsyncResultHandler;

    public StoreListAsyncTask(IStoreFinder iStoreFinder, IAsyncResultHandler iAsyncResultHandler) {
        this.iStoreFinder = iStoreFinder;
        this.iAsyncResultHandler = iAsyncResultHandler;
    }

    @Override
    protected List<StoreItem> doInBackground(SearchParam... searchParams) {
        LatLng top = searchParams[0].getTop();
        LatLng bot = searchParams[0].getBottom();
        List<StoreItem> items = iStoreFinder.storeList(top.latitude, top.longitude, bot.latitude, bot.longitude);
        return items;
    }

    @Override
    protected void onPostExecute(List<StoreItem> storeItems) {
        super.onPostExecute(storeItems);
        iAsyncResultHandler.resultHandler(storeItems);
    }
}
