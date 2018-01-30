package com.poptok.android.poptok.view.friend;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.friend.IFriendFinder;
import com.poptok.android.poptok.service.user.IUserFinder;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIT on 2018-01-26.
 */

@EBean
public class FriendListAdapter  extends BaseAdapter {

    private int userNo;
    private List<FriendList> items;
    private int count;

    @RestService
    IUserFinder userFinder;

    @RestService
    IFriendFinder friendFinder;

    @RootContext
    Context context;

    String LOG_TAG = "FriendListAdapter : ";

    public FriendListAdapter(){

        Log.i(LOG_TAG, "PostListAdapter called()");
        items = new ArrayList<FriendList>();
    }

//    @AfterInject
//    @Background
//    void initAdapter(){
//
//        Log.i(LOG_TAG, "initAdapter() called");
//
//        count = 0 ;
//        while (true){
//            List<FriendList> friendLists = friendFinder.findFriend(userNo);
//            Log.i(LOG_TAG, ""+friendLists);
//
//            if(friendLists != null )
//            {
//                items.addAll(friendLists);
//                count = friendLists.size();
//                break;
//            }
//        }
//    }

    public void setItems(List<FriendList> items){
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i(LOG_TAG, "getView called()");

        FriendItemView itemView;

        if(convertView == null){
            itemView = FriendItemView_.build(context);
        }
        else{
            itemView = (FriendItemView)convertView;
        }
        itemView.bind(getItem(position));

        return itemView;
    }

    @Override
    public int getCount() {

//        count = items.size();
//        while(count == 0 ){
//            try{
//                Thread.sleep(10);
//                count = items.size();
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }

        return items.size();
    }

    @Override
    public FriendList getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


}
