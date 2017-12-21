package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.partial_postitem)
public class PostItemView extends LinearLayout {

    @ViewById
    ImageView imagePost;

    @ViewById
    TextView textPost;

    public PostItemView(Context context) {
        super(context);
    }

    public void bind(PostItem postItem) {
        textPost.setText(postItem.getContent());
    }

}
