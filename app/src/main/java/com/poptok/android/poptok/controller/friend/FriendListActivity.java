package com.poptok.android.poptok.controller.friend;

import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.friend.AcceptFriendAsyncTask;
import com.poptok.android.poptok.service.friend.AddMeFriendAsyncTask;
import com.poptok.android.poptok.service.friend.GetFriendListAsyncTask;
import com.poptok.android.poptok.service.friend.IFriendFinder;
import com.poptok.android.poptok.service.friend.RejectFriendAsyncTask;
import com.poptok.android.poptok.view.friend.FriendListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

//import com.poptok.android.poptok.service.friend.GetFriendListAsyncTask;

/**
 * Created by BIT on 2018-01-26.
 */
@EActivity(R.layout.user_friend)
public class FriendListActivity extends BaseActivity implements IAsyncResultHandler<JSONResult<List<FriendList>> > {

    @ViewById
    ImageButton btnDetailBack;

    @ViewById(R.id.addMeFriendLayout)
    LinearLayout addMeFriendLayout;

    @ViewById(R.id.myFriendLayout)
    LinearLayout myFriendLayout;

    @ViewById(R.id.addMeFriendGridView)
    ListView addMeFriendView;
//    GridView addMeFriendView;

    @ViewById(R.id.myFriendGridView)
    ListView myFriendGridView;

    @ViewById(R.id.profileButton)
    Button profileButton;

    @RestService
    IFriendFinder friendFinder;

    @Bean
    AuthStore authStore;

    @Bean
    FriendListAdapter friendListAdapter;


    GetFriendListAsyncTask getFriendListAsyncTask;
    AddMeFriendAsyncTask addMeFriendAsyncTask;
    AcceptFriendAsyncTask acceptFriendAsyncTask;
    RejectFriendAsyncTask rejectFriendAsyncTask;
//    IAsyncResultHandler<List<FriendList>> iAsyncResultHandler;

    String LOG_TAG = "FriendListActivity : ";

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

//        addMeFriendAsyncTask = new AddMeFriendAsyncTask(iAsyncResultHandler, friendFinder);

        addMeFriendAsyncTask = new AddMeFriendAsyncTask(this, friendFinder);
        int userNo = authStore.getUserInfo().getUserNo();
        addMeFriendAsyncTask.execute(userNo);

        getFriendListAsyncTask = new GetFriendListAsyncTask(this,friendFinder);
        getFriendListAsyncTask.execute(userNo);

    }

    @Click(R.id.profileButton)
    public void profileButton(){
        if(profileButton.getText().equals("추가")){
            //어떤 친구의 값인지 어떻게 알아내지?
            acceptFriendAsyncTask = new AcceptFriendAsyncTask(this, friendFinder);
            acceptFriendAsyncTask.execute(authStore.getUserInfo().getUserNo());
        }else if(profileButton.getText().equals("삭제")){
            //여기서도 어떤 친구의 값인지 알아내서 변수로 전달해줘야함.
            rejectFriendAsyncTask = new RejectFriendAsyncTask(this, friendFinder);
            rejectFriendAsyncTask.execute(authStore.getUserInfo().getUserNo());
        }
    }

    @Click
    public void btnDetailBack() {
        onBackPressed();
    }




    @Override
    public void resultHandler(JSONResult<List<FriendList>> result) {

        Log.i(LOG_TAG, "********************************"+result.getCode());
        //뒤에 1이 붙으면 AddMeFriend 안붙으면 GetFriendProfile
        if(result.getCode().equals("FAIL1")){
            Log.i(LOG_TAG, "친구 없습니다.");
            addMeFriendLayout.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "나를 추가한 친구 없습니다.", Toast.LENGTH_LONG).show();
        }if(result.getCode().equals("FAIL")){
            Log.i(LOG_TAG, "친구 없습니다.");
            Toast.makeText(getApplicationContext(), "친구 없습니다.", Toast.LENGTH_LONG).show();
        }if(result.getCode().equals("SUCC1")){
            Log.i(LOG_TAG, "*******************************************친구 있습니다.");
            Toast.makeText(getApplicationContext(), "나를 추가한 친구 있습니다.", Toast.LENGTH_LONG).show();
//            profileButton.setText("추가");
            friendListAdapter.setItems(result.getData());
            addMeFriendView.setAdapter(friendListAdapter);
        }if(result.getCode().equals("SUCC")){
            Log.i(LOG_TAG, "*******************************************친구 있습니다.");
            Toast.makeText(getApplicationContext(), "친구 있습니다.", Toast.LENGTH_LONG).show();
//            profileButton.setText("삭제");
            friendListAdapter.setItems(result.getData());
            myFriendGridView.setAdapter(friendListAdapter);
        }

    }


    public void getFriendHandler(List<FriendList> result) {

        Log.i(LOG_TAG, "" + result);
        if (result != null) {
            Toast.makeText(getApplicationContext(), "친구 있습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "친구 없습니다.", Toast.LENGTH_LONG).show();
        }
    }


    public void checkFriendHandler(JSONResult<Integer> jsonResult) {

        Log.i(LOG_TAG, "" + jsonResult.getCode());
        if (jsonResult.getCode().equals("SUCC")) {
            Toast.makeText(getApplicationContext(), "친구 신청 가능합니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "친구 신청 불가합니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void rejectFriendHandler(JSONResult<Integer> jsonResult) {

        Log.i(LOG_TAG, "" + jsonResult.getCode());
        if (jsonResult.getCode().equals("SUCC")) {
            Toast.makeText(getApplicationContext(), "친구 신청 거절되었습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "거절에 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void acceptFriendHandler(JSONResult<Integer> jsonResult) {

        Log.i(LOG_TAG, "" + jsonResult.getCode());
        if (jsonResult.getCode().equals("SUCC")) {
            Toast.makeText(getApplicationContext(), "친구 신청 수락되었습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "거절에 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void requestFriendHandler(JSONResult<Integer> jsonResult) {

        Log.i(LOG_TAG, "" + jsonResult.getCode());
        if (jsonResult.getCode().equals("SUCC")) {
            Toast.makeText(getApplicationContext(), "친구 신청되었습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "친구 신청 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }


}
