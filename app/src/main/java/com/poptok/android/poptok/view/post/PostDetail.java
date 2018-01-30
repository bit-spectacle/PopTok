package com.poptok.android.poptok.view.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.post.PostDetailActivity;
import com.poptok.android.poptok.model.post.PostItem;

import org.androidannotations.annotations.Click;
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
    Button btnKakaoChat;

    @ViewById
    ImageView imageDetailBack;
    @ViewById
    ImageView imageDetail;
    @ViewById
    TextView textDetailDate;
    @ViewById
    TextView textDetailContent;
    @ViewById
    TextView textDetailTag;
    @ViewById
    TextView textDetailViewCount;
    @ViewById
    TextView textDetailCommentCount;
    @ViewById
    TextView textDetailLikeCount;

    @ViewById
    FrameLayout frameDetail;

    PostItem postItem;

    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @UiThread
    public void setView(PostItem postItem) {
        Log.d("PostDetail", String.format("postNo: %d", postItem.getPostNo()));
        this.postItem = postItem;

        textDetailNick.setText(postItem.getNickname());

        String imageUrl = postItem.getImage();

        Glide.with(context)
                .load(imageUrl)
                .apply(bitmapTransform(new BlurTransformation(25)))
                .apply(bitmapTransform(new ColorFilterTransformation(Color.argb(80, 40, 40, 40))))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(imageDetailBack);
        if(imageUrl.contains("poptok_logo_back")) {
            imageDetail.setVisibility(View.INVISIBLE);
        }
        else {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.loading))
                    .into(imageDetail);
        }

        textDetailDate.setText(postItem.getPostDate());
        textDetailContent.setText(postItem.getContent());
        textDetailTag.setText(postItem.getTag());
        textDetailViewCount.setText(String.format("%d views", postItem.getViewsCnt()));
        textDetailCommentCount.setText(String.format("%d", postItem.getCommentCnt()));
        textDetailLikeCount.setText(String.format("%d",postItem.getLikeCnt()));

        if(postItem.getKakaoLink().length() > 0) {
            btnKakaoChat.setVisibility(View.VISIBLE);
        }
    }

    @Click
    public void btnKakaoChatClicked(View v) {
        Uri uri = Uri.parse(postItem.getKakaoLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        activity.startActivity(intent);
    }

    public void toggleDetail() {
        int nowVisibility = frameDetail.getVisibility();
        int visibility = nowVisibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE;
        frameDetail.setVisibility(visibility);
    }


}
