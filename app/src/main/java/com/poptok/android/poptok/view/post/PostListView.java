package com.poptok.android.poptok.view.post;

import android.content.Context;
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
    ListView listPost;

    @Bean
    PostListAdapter adapter;

    @AfterViews
    public void setView() {
        listPost.setAdapter(adapter);
    }

//    @ItemClick
//    void listPostItemClicked(PostItem postItem) {
//        Log.d("clicked", postItem.getContent());
//        Toast.makeText(context, postItem.getContent(), Toast.LENGTH_LONG).show();
//    }

}
