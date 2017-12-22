package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.post_nested_postitem)
public class PostItemView extends RelativeLayout {

    @ViewById
    ImageView imagePost;

    @ViewById
    TextView textPost;

    @ViewById
    TextView textTag;

    public PostItemView(Context context) {
        super(context);
    }

    @UiThread
    public void bind(PostItem postItem) {
        textPost.setText(postItem.getContent());
        textTag.setText(postItem.getTag());
        Glide.with(this).load(postItem.getImage()).into(imagePost);
    }

}
