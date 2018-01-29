package com.poptok.android.poptok.view.post;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poptok.android.poptok.model.store.StoreItem;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class LocationListAdapter extends BaseAdapter {

    List<StoreItem> items;

    @RootContext
    Context context;


    public LocationListAdapter() {
        items = new ArrayList<StoreItem>();
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public StoreItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerItemView itemView;
        if(convertView == null) {
            itemView = SpinnerItemView_.build(context);
        }
        else {
            itemView = (SpinnerItemView)convertView;
        }
        itemView.bind(getItem(position));
        return  itemView;
    }
}
