package com.poptok.android.poptok.controller.post;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;
import com.poptok.android.poptok.view.post.PostListView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.PageScrolled;

@EActivity(R.layout.post_list)
public class PostListActivity extends AppCompatActivity {

    @Bean
    PostListView postListView;

    @ItemClick(R.id.gridPost)
    void listPostItemClicked(PostItem postItem) {
        Log.d("clicked", postItem.getContent());
        Toast.makeText(this, postItem.getContent(), Toast.LENGTH_SHORT).show();
    }

//    @PageScrolled(R.id.gridPost)
//    void onPageScrolled(ViewPager view, int position, float positionOffeset, int positionOffsetPixels) {
//
//    }


}
