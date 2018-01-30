package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.poptok.android.poptok.model.hash.TagItem;
import com.poptok.android.poptok.model.search.SearchParam;
import com.poptok.android.poptok.model.store.StoreItem;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.hash.HashListAsyncTask;
import com.poptok.android.poptok.service.hash.IHashfindResultHandler;
import com.poptok.android.poptok.service.hash.IHashfinder;
import com.poptok.android.poptok.service.location.LocationProvider;
import com.poptok.android.poptok.service.store.IStoreFinder;
import com.poptok.android.poptok.service.store.StoreListAsyncTask;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

@EBean
public class PostWrite implements IAsyncResultHandler<List<StoreItem>>, IHashfindResultHandler{

    private static final String TAG = "PostWrite";

    @RootContext
    Context context;

    @Bean
    SearchParam searchParam;

    @Bean
    LocationListAdapter locationListAdapter;

    @RestService
    IStoreFinder iStoreFinder;
    @RestService
    IHashfinder iHashfinder;

    @ViewById
    Spinner spinnerLocation;

    @ViewById
    TagView tagGroup;

    @ViewById
    EditText editHash;

    StoreListAsyncTask storeListAsyncTask;
    HashListAsyncTask hashListAsyncTask;

    LocationProvider locationProvider;

    public StoreItem getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItem storeItem) {
        this.storeItem = storeItem;
    }

    StoreItem storeItem;

    @AfterInject
    public void init() {
        locationProvider = new LocationProvider(context);
        storeListAsyncTask = new StoreListAsyncTask(iStoreFinder, this);
        storeListAsyncTask.execute(searchParam);

        hashListAsyncTask = new HashListAsyncTask(iHashfinder, this);
        hashListAsyncTask.execute(50);
    }

    @Override
    public void resultHandler(List<StoreItem> storeItems) {
        Location nowLocation = locationProvider.getLocation();
        StoreItem nowStore = new StoreItem();
        nowStore.setLocationNo(0);
        nowStore.setBusinessName("내위치");
        nowStore.setLatitude(nowLocation.getLatitude());
        nowStore.setLongitude(nowLocation.getLongitude());
        storeItems.add(0, nowStore);

        locationListAdapter.setItems(storeItems);
        spinnerLocation.setAdapter(locationListAdapter);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StoreItem item = (StoreItem)spinnerLocation.getItemAtPosition(position);
                Log.d(TAG, String.format("%d", item.getLocationNo()));
                PostWrite.this.setStoreItem(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void hashFindResultHandler(List<TagItem> result) {
        List<Tag> tags = new ArrayList<>(result.size());
        for(TagItem item :result) {
            tags.add(new Tag(item.getTag()));
        }
        tagGroup.addTags(tags);

        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int i) {
                String beforHash = editHash.getText().toString();
                if(beforHash.contains(tag.text) == false) {
                    String afterHash = beforHash;
                    afterHash += "#" + tag.text + ",";
                    editHash.setText(afterHash);
                }
            }
        });
    }
}
