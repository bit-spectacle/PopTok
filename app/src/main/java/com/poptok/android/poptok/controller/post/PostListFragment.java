package com.poptok.android.poptok.controller.post;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.view.post.PostListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.post_list)
public class PostListFragment extends Fragment {
//
//    @Bean
//    PostListView postListView;

    @ViewById
    GridView gridPost;

    @Bean
    PostListAdapter adapter;

    @AfterViews
    public void init() {
        gridPost.setAdapter(adapter);
        Log.d("init", "after");
    }


    @ItemClick(R.id.gridPost)
    void listPostItemClicked(PostItem postItem) {
        Log.d("clicked", postItem.getContent());
        Toast.makeText(getActivity(), postItem.getContent(), Toast.LENGTH_SHORT).show();
    }

}
