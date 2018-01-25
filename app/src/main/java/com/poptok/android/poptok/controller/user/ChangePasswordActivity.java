package com.poptok.android.poptok.controller.user;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.user_changepassword)
public class ChangePasswordActivity extends BaseActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.user_changepassword);

    }

    public void changePasswordButtonClick(){


//        Toast toast = Toast.makeText(getApplicationContext(), " 비밀번호 변경이 완료되었습니다." , Toast.LENGTH_LONG);
        //비밀번호 변경이 완료되었다는 메시지를 띄우는 곳
        //********************************************
        //비밀번호 변경 실패시 실패메시지를 띄우도록 변경해야함
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("비밀번호 변경");
        alertDialogBuilder.setMessage("비밀번호 변경이 완료되었습니다.")
                .setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        ChangePasswordActivity.this.finish();
                    }
        });







    }
}
