package com.poptok.android.poptok.controller.user;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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


    @AfterViews
    void init(){

        //중복확인 및 비밀번호 체크

    }

    void show(){
        final EditText editText = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("닉네임 중복 확인");
        builder.setMessage("사용할 닉네임을 입력해주세요");
        builder.setView(editText);
        builder.setPositiveButton("중복확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getApplicationContext(), " 닉네임 변경이 취소되었습니다." , Toast.LENGTH_LONG);
            }
        });

    }

    public void checkAll(){

        EditText email = (EditText)findViewById(R.id.IdEditText);
        EditText password = (EditText)findViewById(R.id.passwordEditText);
        EditText passwordConfirm = (EditText)findViewById(R.id.passwordConfirmEditText);



//
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

    }
}
