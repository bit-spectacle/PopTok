package com.poptok.android.poptok.controller.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.exception.JSONResultException;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.User;
import com.poptok.android.poptok.service.SafeAsyncTask;
import com.poptok.android.poptok.service.user.JSONResult;
import com.poptok.android.poptok.service.user.UserProvider;
import com.poptok.android.poptok.service.user.UserThread;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.user_login)
public class LoginActivity extends AppCompatActivity {

    private static String API_KEY="f1ce1afe4bd1995ca9fe8c5957fcd78b";
    private static final String LOG_TAG = "LoginActivity : ";

    @ViewById(R.id.poptokTextView)
    TextView poptokTextView;

    @ViewById(R.id.userNameEditText)
    EditText userNameEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @Bean
    UserThread userThread;

    @Bean
    AuthStore authStore;

    Context context;




//    @AfterViews
//    void init(){
//        context = this.getApplicationContext();
//        userThread.setMainHandler(loginHandler);
//        userThread.setDaemon(true);
//        userThread.start();
//    }

    private class JSONResultLogin extends JSONResult<User> {}

    @Click
    public void loginButton(View v){
        int viewId = v.getId();
//        switch(viewId){
//            case R.id.loginButton :
//                break;
//            case R.id.cancelButton:
//                break;
//        }

        if(viewId == R.id.loginButton){
            final String email = userNameEditText.getText().toString();
            final String password = passwordEditText.getText().toString();
            new AuthAsyncTask(email, password).execute();
        }

    }



    @Click
    public void cancelButton(View v){
        //이게 여기 있을 필요가 있는가?

    }

    @Click
    public void joinText(View v){

        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);

    }

    @Click
    public void findIdText(View v){

    }

    @Click
    public void findPasswordText(View v){

    }


    /**
     *  login api 통신 하는 태스크
     */
    private class AuthAsyncTask extends SafeAsyncTask<User> {

        private String email;
        private String password;

        public AuthAsyncTask(String email, String password){
            this.email = email;
            this.password = password;
        }

        @Override
        public User call() throws Exception {
            User user = new UserProvider(LoginActivity.this).login(email, password);
            return user;
        }

        @Override
        protected void onException(Exception exception){
            if(exception instanceof JSONResultException){
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }else{

            }
        }

        @Override
        public void onSuccess(User user){
            System.out.println("======================================"+user.getUserNo());

            SharedPreferences sharedPreference = getSharedPreferences( "poptok", MODE_PRIVATE );
            SharedPreferences.Editor sharedPreferenceEditor = sharedPreference.edit();
            sharedPreferenceEditor.putLong( "userNo", user.getUserNo() );
            sharedPreferenceEditor.putString( "email", email );
            sharedPreferenceEditor.putString( "password", password );
            sharedPreferenceEditor.commit();

            Intent intent = new Intent( LoginActivity.this, JoinActivity_.class );

            startActivity( intent );

            finish();
        }
    }

}
