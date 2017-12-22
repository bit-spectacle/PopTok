package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.widget.GridView;
import android.widget.ListView;

import com.poptok.android.poptok.view.post.PostListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

@EBean
public class PostListView {

    @RootContext
    Context context;

    @ViewById
    GridView gridPost;

    @Bean
    PostListAdapter adapter;

    @AfterViews
    public void setView() {
        gridPost.setAdapter(adapter);
    }

}
