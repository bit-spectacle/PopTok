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

    private List<PostListItem> items;

    @RootContext
    Context context;

    public PostListAdapter() {
        Log.d("PostListAdapter", "before");
        items = new ArrayList<PostListItem>();
    }

    public void setItems(List<PostListItem> items) {
        this.items = items;
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
        return items.size();
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
