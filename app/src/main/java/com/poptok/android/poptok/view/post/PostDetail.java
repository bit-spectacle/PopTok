package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostItem;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
/**
 * Created by BIT on 2018-01-16.
 */

@EBean
public class PostDetail {

    @RootContext
    Context context;

    @ViewById
    ImageView imageDetailUser;
    @ViewById
    TextView textDetailNick;

    @ViewById
    ImageView imageDetailBack;
    @ViewById
    ImageView imageDetail;
    @ViewById
    TextView textDetailDate;
    @ViewById
    TextView textDetailContent;
    @ViewById
    TextView textDetailViewCount;
    @ViewById
    TextView textDetailCommentCount;
    @ViewById
    TextView textDetailLikeCount;

    @ViewById
    FrameLayout frameDetail;

    @UiThread
    public void setView(PostItem postItem) {
        Log.d("PostDetail", String.format("postNo: %d", postItem.getPostNo()));

        textDetailNick.setText(postItem.getNickname());

        Glide.with(context)
                .load(postItem.getImage())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .apply(bitmapTransform(new ColorFilterTransformation(Color.argb(80, 40, 40, 40))))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(imageDetailBack);
        Glide.with(context)
                .load(postItem.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(imageDetail);

        textDetailDate.setText(postItem.getPostDate());
        textDetailContent.setText(postItem.getContent());
        textDetailViewCount.setText(String.format("%d views", postItem.getViewsCnt()));
        textDetailCommentCount.setText(String.format("%d", postItem.getCommentCnt()));
        textDetailLikeCount.setText(String.format("%d",postItem.getLikeCnt()));
    }

    public void toggleDetail() {
        int nowVisibility = frameDetail.getVisibility();
        int visibility = nowVisibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE;
        frameDetail.setVisibility(visibility);
    }
}
