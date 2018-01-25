package com.poptok.android.poptok.controller.user;

import android.util.Log;
import android.view.View;
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
import com.poptok.android.poptok.service.user.ChangeNicknameAsyncTask;
import com.poptok.android.poptok.service.user.CheckNickNameAsyncTask;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.UserThread;
import com.poptok.android.poptok.view.user.ChangeNickname;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.user_changenickname)
public class ChangeNickNameActivity extends BaseActivity {


    @ViewById(R.id.backButton)
    ImageButton backButton;

    @ViewById(R.id.checkNicknameButton)
    Button checkNicknameButton;

    @ViewById(R.id.userNameEditText)
    EditText userNameEditText;

    @ViewById(R.id.changeButton)
    Button changeButton;

    @ViewById(R.id.userIdText)
    TextView userIdText;

    @ViewById(R.id.userNickNameText)
    TextView userNickNameText;

    @RestService
    IUserFinder userFinder;

    @Bean
    AuthStore authStore;

    @Bean
    UserThread userThread;

    @Bean
    ChangeNickname changeNickname;

    ChangeNicknameAsyncTask changeNicknameAsyncTask;
    CheckNickNameAsyncTask checkNickNameAsyncTask;

    String LOG_TAG = "ChangeNickname";

    String nickname = "";

    @AfterViews
    public void init(){

        userIdText.setText(authStore.getUserInfo().getEmail());
        userNickNameText.setText(authStore.getUserInfo().getNickname());

    }

    @Click
    public void backButton(){
        onBackPressed();
    }

    public void nicknameCheckHandler(JSONResult<Integer> jsonResult){

        if(jsonResult.getCode().equals("SUCC") && jsonResult.getData() == 0){
                Toast.makeText(getApplicationContext(), "변경 가능한 닉네임입니다.", Toast.LENGTH_LONG).show();
                changeButton.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(getApplicationContext(), "이미 있는 닉네임입니다.", Toast.LENGTH_LONG).show();
            }
    }

    public void nicknameChangeHandler(JSONResult<UserInfo> jsonResult){

        Log.i(LOG_TAG,jsonResult.getCode());
        if(jsonResult.getCode().equals("SUCC"))
        {
            userNameEditText.setText("");
            Toast.makeText(getApplicationContext(), "변경 되었습니다.", Toast.LENGTH_LONG).show();
            userNickNameText.setText(nickname);

            changeButton.setVisibility(View.GONE);
        }
        else{
            Toast.makeText(getApplicationContext(), "변경을 실패하였습니다.", Toast.LENGTH_LONG).show();
        }
    }



    @Click
    public void checkNicknameButton(){
        nickname = userNameEditText.getText().toString();

        if(nickname.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "변경하실 닉네임을 입력해주세요.", Toast.LENGTH_LONG).show();
            return;
        }
        checkNickNameAsyncTask = new CheckNickNameAsyncTask(this, userFinder);
        checkNickNameAsyncTask.execute(nickname);

        Log.i("checkNickname : " , ""+nickname);
    }


    @Click
    public void changeButton(){

        String nicknameCheck = userNameEditText.getText().toString();

        Log.i(LOG_TAG, ""+ nickname + "nicknameCheck : " + nicknameCheck);
        if(!nickname.trim().equals(nicknameCheck))
        {
            Toast.makeText(getApplicationContext(), "중복체크를 다시해주세요.", Toast.LENGTH_LONG).show();
            changeButton.setVisibility(View.GONE);
            return;
        }

        changeNicknameAsyncTask = new ChangeNicknameAsyncTask(authStore, this, userFinder);
        changeNicknameAsyncTask.execute(nickname.trim());

    }

    public void setView(JSONResult<UserInfo> jsonResult) {
        changeNickname.setView(jsonResult);
    }

}
