package com.poptok.android.poptok.view.friend;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.friend.AcceptFriendAsyncTask;
import com.poptok.android.poptok.service.friend.IFriendFinder;
import com.poptok.android.poptok.service.friend.RejectFriendAsyncTask;
import com.poptok.android.poptok.service.user.IUserFinder;

import org.androidannotations.annotations.Bean;
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

    @Bean
    AuthStore authStore;

    String LOG_TAG = "FriendListAdapter : ";

    AcceptFriendAsyncTask acceptFriendAsyncTask;
    RejectFriendAsyncTask rejectFriendAsyncTask;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        Log.i(LOG_TAG, "getView called()");

        FriendItemView itemView;
        FriendList friendList = getItem(position);

        if(convertView == null){
            itemView = FriendItemView_.build(context);
        }
        else{
            itemView = (FriendItemView)convertView;
        }
        itemView.bind(friendList);

        Button profileButton = (Button)convertView.findViewById(R.id.profileButton);
        profileButton.setTag(friendList.getUserNo());
        if(friendList.getUserStatus() == 1)
            profileButton.setText("추가");
        else if(friendList.getUserStatus() == 2)
            profileButton.setText("삭제");
        profileButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Integer userNo = Integer.parseInt(v.getTag().toString());

                if(v.getContext().getText(position).equals("추가")){
                    //어떤 친구의 값인지 어떻게 알아내지?
                    acceptFriendAsyncTask = new AcceptFriendAsyncTask(authStore, v, friendFinder);
                    acceptFriendAsyncTask.execute(userNo,authStore.getUserInfo().getUserNo());
                }else if(v.getContext().getText(position).equals("삭제")){
                    //여기서도 어떤 친구의 값인지 알아내서 변수로 전달해줘야함.
                    rejectFriendAsyncTask = new RejectFriendAsyncTask(authStore, v, friendFinder);
                    rejectFriendAsyncTask.execute(userNo, authStore.getUserInfo().getUserNo());
                }


            }
        });



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
