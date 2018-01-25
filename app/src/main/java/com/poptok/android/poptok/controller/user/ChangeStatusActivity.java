package com.poptok.android.poptok.controller.user;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.user.ChangeStatusAsyncTask;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.UserThread;
import com.poptok.android.poptok.view.user.ChangeStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by BIT on 2018-01-25.
 */
@EActivity(R.layout.user_changestatus)
public class ChangeStatusActivity extends BaseActivity {

    @Bean
    AuthStore authStore;

    @ViewById(R.id.backButton)
    ImageButton backButton;

    @Bean
    UserThread userThread;

    @Bean
    ChangeStatus changeStatus;

    @ViewById(R.id.changeButton)
    Button changeButton;

    @ViewById(R.id.userIdText)
    TextView userIdText;

    @ViewById(R.id.userStatusTextView)
    TextView userStatusTextView;

    @ViewById(R.id.userStatusEditText)
    EditText userStatusEditText;

    @RestService
    IUserFinder userFinder;


    String status = null;



    ChangeStatusAsyncTask changeStatusAsyncTask;

    String LOG_TAG = "ChangeStatus";
    @AfterViews
    public void init(){

        userIdText.setText(authStore.getUserInfo().getEmail());
        userStatusTextView.setText(authStore.getUserInfo().getStatus());
    }

    public void statusChangeHandler(JSONResult<UserInfo> jsonResult){

        Log.i(LOG_TAG, ""+jsonResult.getCode());
        if(jsonResult.getCode().equals("SUCC")){
            userStatusEditText.setText("");
            Toast.makeText(getApplicationContext(), "변경 되었습니다.", Toast.LENGTH_LONG).show();
            userStatusTextView.setText(status);
            authStore.setUserStatus(status);
        }else{
            Toast.makeText(getApplicationContext(), "변경을 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }

    @Click
    public void changeButton(){

        status = userStatusEditText.getText().toString();

        if(status.trim().length() == 0){
            status = "";
        }

        changeStatusAsyncTask = new ChangeStatusAsyncTask(authStore, this, userFinder);
        changeStatusAsyncTask.execute(status);

    }

    @Click
    public void backButton(){
        onBackPressed();
    }

    public void setView(JSONResult<UserInfo> jsonResult) {
        changeStatus.setView(jsonResult);
    }



}
