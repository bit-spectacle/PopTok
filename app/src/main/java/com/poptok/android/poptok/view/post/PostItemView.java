package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostListItem;

import org.androidannotations.annotations.EViewGroup;
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

    public void bind(PostListItem postItem) {
        Log.d("bind", String.format("%d", postItem.getPostNo()));
        textPost.setText(postItem.getContent());
        textTag.setText(postItem.getTag());
        Glide.with(this)
                .load(postItem.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(imagePost);
    }

}
