package com.poptok.android.poptok.controller.post;


import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.model.search.SearchParam;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.post.IPostItemFinder;
import com.poptok.android.poptok.service.post.PostListAsyncTask;
import com.poptok.android.poptok.view.post.PostListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

@EFragment(R.layout.post_list)
public class PostListFragment extends Fragment implements IAsyncResultHandler<List<PostListItem>> {

    @ViewById
    GridView gridPost;

    @Bean
    PostListAdapter adapter;

    @Bean
    SearchParam searchParam;

    @RestService
    IPostItemFinder postItemFinder;

    PostListAsyncTask postListAsyncTask;

    @AfterViews
    public void init() {
        postListAsyncTask = new PostListAsyncTask(postItemFinder, this);
        postListAsyncTask.execute(searchParam);
    }

    @ItemClick(R.id.gridPost)
    void listPostItemClicked(PostListItem postItem) {
        int postNo = postItem.getPostNo();
        Intent intent = new Intent(this.getActivity(), PostDetailActivity_.class);
        intent.putExtra("postNo", postNo);
        startActivity(intent);
    }

    @Override
    public void resultHandler(List<PostListItem> result) {
        adapter.setItems(result);
        gridPost.setAdapter(adapter);
    }
}
