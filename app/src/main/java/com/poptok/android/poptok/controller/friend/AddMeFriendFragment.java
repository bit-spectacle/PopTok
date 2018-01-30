//package com.poptok.android.poptok.controller.friend;
//
//import android.app.Fragment;
//
//import com.poptok.android.poptok.R;
//import com.poptok.android.poptok.model.auth.AuthStore;
//import com.poptok.android.poptok.model.user.FriendList;
//import com.poptok.android.poptok.service.IAsyncResultHandler;
//import com.poptok.android.poptok.service.friend.MyFriendListAsyncTask;
//import com.poptok.android.poptok.service.friend.IFriendFinder;
//import com.poptok.android.poptok.view.friend.FriendListAdapter;
//
//import org.androidannotations.annotations.AfterViews;
//import org.androidannotations.annotations.Bean;
//import org.androidannotations.annotations.EFragment;
//import org.androidannotations.rest.spring.annotations.RestService;
//
//import java.util.List;
//
///**
// * Created by BIT on 2018-01-27.
// */
//
//@EFragment(R.layout.user_friend)
//public class AddMeFriendFragment extends Fragment implements IAsyncResultHandler<List<FriendList>> {
//
////    @ViewById(R.id.friendGridPost)
////    GridView gridFriend;
//
//    FriendListActivity friendListActivity;
//
//    @Bean
//    AuthStore authStore;
//
//    @Bean
//    FriendListAdapter adapter;
//
//    @RestService
//    IFriendFinder friendFinder;
//
//    MyFriendListAsyncTask friendListAsyncTask;
////    AddMeFriendAsyncTask addMeFriendAsyncTask;
//
//    @AfterViews
//    public void init(){
//        friendListAsyncTask = new MyFriendListAsyncTask(friendListActivity , friendFinder, this);
//        friendListAsyncTask.execute();
//    }
//
//
//    @Override
//    public void resultHandler(List<FriendList> result) {
//
//        adapter.setItems(result);
////        gridFriend.setAdapter(adapter);
////
////        if(result == null){
////            //Toast.makeText(this.getActivity().getApplicationContext(), "친구 없음", Toast.LENGTH_LONG).show();
////        }else if(result != null){
////            adapter.setItems(result);
////        }
//
//    }
//}
