package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.service.post.IPostItemFinder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

@EBean
public class PostListAdapter extends BaseAdapter {

    private int lastNo;
    private List<PostListItem> items;

    @RootContext
    Context context;

    @RestService
    IPostItemFinder postItemFinder;

    public PostListAdapter() {
        Log.d("PostListAdapter", "before");
        items = new ArrayList<PostListItem>();
    }

    @AfterInject
    @Background
    void initAdapter() {
        Log.d("initAdapter", "before");
        lastNo = 0;
        while (true) {
            List<PostListItem> postItems = postItemFinder.findPostList(lastNo);
            Log.d("initAdapter", String.format("item count:%d", postItems.size()));
            if(postItems.size() > 0) {
                items.addAll(postItems);
                lastNo = (postItems.get(postItems.size() - 1)).getPostNo();
                break;
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Log.d("getView", String.format("%d", position) );
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
        int itemSize = items.size();
        while(itemSize == 0) {
            try {
                Thread.sleep(10);
                itemSize = items.size();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return itemSize;
    }

    @Override
    public PostListItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
