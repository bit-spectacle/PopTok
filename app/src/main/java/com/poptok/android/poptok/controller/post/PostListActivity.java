package com.poptok.android.poptok.controller.post;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.view.post.PostListView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;

@EActivity(R.layout.activity_post_list)
public class PostListActivity extends AppCompatActivity {

    @Bean
    PostListView postListView;

    @ItemClick(R.id.listPost)
    void listPostItemClicked(PostItem postItem) {
        Log.d("clicked", postItem.getContent());
        Toast.makeText(this, postItem.getContent(), Toast.LENGTH_SHORT).show();
    }


}
