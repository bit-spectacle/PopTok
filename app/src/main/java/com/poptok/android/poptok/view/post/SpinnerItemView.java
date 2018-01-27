package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostListItem;
import com.poptok.android.poptok.model.store.StoreItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.spinner_item)
public class SpinnerItemView extends RelativeLayout  {

    @ViewById
    TextView spinner_title;

    @ViewById
    TextView spinner_category;

    public SpinnerItemView(Context context) {
        super(context);
    }

    public void bind(StoreItem storeItem) {
        Log.d("bind", String.format("%d", storeItem.getLocationNo()));
        spinner_title.setText(storeItem.getBusinessName());
        spinner_category.setText(storeItem.getCategory());
    }
}
