package com.poptok.android.poptok.controller.cloud;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.hash.TagItem;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.hash.HashListAsyncTask;
import com.poptok.android.poptok.service.hash.IHashfindResultHandler;
import com.poptok.android.poptok.service.hash.IHashfinder;

import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EActivity(R.layout.cloud_word)
public class WordcloudActivity extends AppCompatActivity implements IHashfindResultHandler {

    private static final String TAG = "WordcloudActivity";
    @ViewById
    WordCloudView wordCloud;

    @RestService
    IHashfinder iHashfinder;
    HashListAsyncTask hashListAsyncTask;

    @AfterViews
    public void init() {
        hashListAsyncTask = new HashListAsyncTask(iHashfinder, this);
        hashListAsyncTask.execute(50);
    }

    @Click
    public void fabRefreshClicked(View v) {
        hashListAsyncTask = new HashListAsyncTask(iHashfinder, this);
        hashListAsyncTask.execute(50);
    }

    @Override
    public void hashFindResultHandler(List<TagItem> result) {
        List<WordCloud> list = getWordCloudList(result);

        Log.d(TAG, list.get(0).getText());

        wordCloud.clearCache(true);
        wordCloud.clearHistory();

        wordCloud.setDataSet(list);
        wordCloud.setSize(500,350);
        wordCloud.setColors(ColorTemplate.MATERIAL_COLORS);
        wordCloud.notifyDataSetChanged();
    }

    private List<WordCloud> getWordCloudList(List<TagItem> result) {
        List<WordCloud> list = new ArrayList<>(result.size());
        for(TagItem item : result) {
            WordCloud wordCloud = new WordCloud(item.getTag(), item.getCount());
            list.add(wordCloud);
        }

        return  list;
    }
}
