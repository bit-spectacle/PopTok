package com.poptok.android.poptok.service.post;

import com.poptok.android.poptok.model.post.PostItem;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class PostItemFinder implements IPostItemFinder {

    @Override
    public List<PostItem> findAll() {
        List<PostItem> items = new ArrayList<PostItem>();
        items.add(new PostItem(1, 1, "content", "imageUrl", "2017-01-01"));
        items.add(new PostItem(5, 1, "content5", "imageUrl", "2017-01-05"));
        items.add(new PostItem(4, 1, "content4", "imageUrl", "2017-01-04"));

        return items;
    }
}
