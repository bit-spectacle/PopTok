package com.poptok.android.poptok.controller.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.AppBaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.service.user.CheckEmailAsyncTask;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.JoinAsyncTask;
import com.poptok.android.poptok.service.user.Join_CheckNickNameAsyncTask;
import com.poptok.android.poptok.service.user.UserThread;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.user_join)
public class JoinActivity extends AppCompatActivity {

    /*
    * EditText email = (EditText)findViewById(R.id.IdEditText);
        EditText password = (EditText)findViewById(R.id.passwordEditText);
        EditText passwordConfirm = (EditText)findViewById(R.id.passwordConfirmEditText);
    * */

    @ViewById(R.id.IdEditText)
    EditText idEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @ViewById(R.id.passwordConfirmEditText)
    EditText passwordConfirmEditText;

    @ViewById(R.id.nickNameEditText)
    EditText nicknameEditText;

    @ViewById(R.id.joinButton)
    Button joinButton;

    @ViewById(R.id.cancelButton)
    Button cancelButton;

    @ViewById(R.id.agreeCheckBox)
    CheckBox agreeCheckBox;

    @RestService
    IUserFinder userFinder;

    @Bean
    UserThread userThread;

    @Bean
    AuthStore authStore;

    Join_CheckNickNameAsyncTask checkNickNameAsyncTask;
    CheckEmailAsyncTask checkEmailAsyncTask;
    JoinAsyncTask joinAsyncTask;

    boolean isNicknameCheckPass = false;
    boolean isEmailCheckPass = false;



    @Click
    public void cancelButton(){
        onBackPressed();
    }

    String LOG_TAG = "joinActivity : ";

    @AfterViews
    void init(){

        //중복확인 및 비밀번호 체크

    }
    @Click
    public void agreeCheckBox(){
        agreeCheckBox.setChecked(true);
    }


    public void nicknameCheckHandler(JSONResult<Integer> jsonResult){

        Log.i("nickNameCheckHandler : ",jsonResult.getCode());
        if(jsonResult.getCode().equals("FAIL"))
        {
            Toast.makeText(getApplicationContext(), "사용할 수 없는 닉네임 입니다.", Toast.LENGTH_LONG).show();
            return;
        }

        isNicknameCheckPass = true;

    }

    public void emailCheckHandler(JSONResult<Integer> jsonResult){
        Log.i("emailCheckHandler : ",jsonResult.getCode());
        if(jsonResult.getCode().equals("FAIL"))
        {
            Toast.makeText(getApplicationContext(), "사용할 수 없는 이메일 입니다.", Toast.LENGTH_LONG).show();
            return;
        }


    }

    public void joinHandler(JSONResult<Integer> jsonResult){
        Log.i("joinHandler : ",jsonResult.getCode());
        if(jsonResult.getData() == 0)
        {
            Toast.makeText(getApplicationContext(), "가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AppBaseActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(getApplicationContext(), "가입에 실패하였습니다.", Toast.LENGTH_LONG).show();
            return;
        }

    }

    @Click
    public void joinButton(){

        Log.i(LOG_TAG,"Start");
        String pass = passwordConfirmEditText.getText().toString();
        String pass2 = passwordEditText.getText().toString();
        if(idEditText.getText().length() ==0)
        {
            Log.i(LOG_TAG,"Start");
            Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

        if(! passwordConfirmEditText.getText().toString().equals(passwordEditText.getText().toString())){
            Log.i(LOG_TAG,"비밀번호 다름");
            Toast.makeText(getApplicationContext(), " 비밀번호가 같지 않습니다." , Toast.LENGTH_LONG).show();
            return;
        }

        if(nicknameEditText.getText().length() ==0)
        {
            Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요", Toast.LENGTH_LONG).show();
            return;
        }

//        if(!agreeCheckBox.isChecked()){
//            Toast.makeText(getApplicationContext(), "이용약관 동의를 해주세요", Toast.LENGTH_LONG).show();
//            return;
//        }


//        checkEmailAsyncTask = new CheckEmailAsyncTask(this, userFinder);
//        checkEmailAsyncTask.execute(idEditText.getText().toString());

//        Log.i(LOG_TAG,"checkNickStart");
//
//        checkNickNameAsyncTask = new Join_CheckNickNameAsyncTask(this, userFinder);
//        checkNickNameAsyncTask.execute(nicknameEditText.getText().toString());
//        Log.i(LOG_TAG,"check password");

        Log.i(LOG_TAG,"check JoinAsync");
        joinAsyncTask = new JoinAsyncTask(this, userFinder);
        joinAsyncTask.execute(idEditText.getText().toString(), passwordEditText.getText().toString(), nicknameEditText.getText().toString());

    }






//    void show(){
//        final EditText editText = new EditText(this);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("닉네임 중복 확인");
//        builder.setMessage("사용할 닉네임을 입력해주세요");
//        builder.setView(editText);
//        builder.setPositiveButton("중복확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast toast = Toast.makeText(getApplicationContext(), " 닉네임 변경이 취소되었습니다." , Toast.LENGTH_LONG);
//            }
//        });
//
//    }
//
//    public void checkAll(){
//
//        EditText email = (EditText)findViewById(R.id.IdEditText);
//        EditText password = (EditText)findViewById(R.id.passwordEditText);
//        EditText passwordConfirm = (EditText)findViewById(R.id.passwordConfirmEditText);
//
//        if(Util.validateEmail(email.getText().toString()))
//        {
//            if(password.getText().toString() == passwordConfirm.getText().toString())
//            {
//
//            }
//
//        }
//
//        if(R.id.passwordEditText != R.id.passwordConfirmEditText)
//        {
//            //비밀번호와 비밀번호 확인이 다른 경우
//            Toast toast = Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다.", Toast.LENGTH_LONG);
//
//
//        }
//        else if(R.id.passwordEditText == R.id.passwordConfirmEditText)
//        {
//
//        }
//
//
//    }
}
