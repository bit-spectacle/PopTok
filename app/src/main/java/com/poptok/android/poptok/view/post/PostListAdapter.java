package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.service.post.IPostItemFinder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIT on 2017-12-19.
 */

@EBean
public class PostListAdapter extends BaseAdapter {

    private int lastNo;
    private List<PostItem> items;

    @RootContext
    Context context;

    @RestService
    IPostItemFinder postItemFinder;

    public PostListAdapter() {
        items = new ArrayList<PostItem>();
    }

    @AfterInject
    @Background
    void initAdapter() {
        lastNo = 0;
        items = postItemFinder.findPost(lastNo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        PostItemView itemView;
        if(convertView == null) {
            itemView = PostItemView_.build(context);
        }
        else {
            itemView = (PostItemView)convertView;
        }
        itemView.bind(getItem(position));
        return itemView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public PostItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
