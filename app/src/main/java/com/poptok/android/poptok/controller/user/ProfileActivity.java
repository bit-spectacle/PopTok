package com.poptok.android.poptok.controller.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.service.user.UserProfileAsyncTask;
import com.poptok.android.poptok.view.user.UserProfile;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BIT on 2018-01-22.
 */


@EActivity(R.layout.user_profile)
public class ProfileActivity extends BaseActivity {

    @ViewById(R.id.profileImage)
    CircleImageView profileImage;

    @ViewById(R.id.userNickNameTextView)
    TextView userNicknameTextView;

    @ViewById(R.id.userStatusTextView)
    TextView userStatusTextView;

    @ViewById(R.id.changeImageButton)
    Button changeImageButton;

    @ViewById(R.id.changeStatusButton)
    Button changeStatusButton;

    @ViewById(R.id.changeNickNameButton)
    Button changeNickNameButton;

    @ViewById(R.id.backButton)
    ImageButton backButton;

    @RestService
    IUserFinder userFinder;

    @Bean
    UserProfile userProfile;

    UserProfileAsyncTask userProfileAsyncTask;

    @Bean
    AuthStore authStore;

    String src = null;
    private int PICK_IMAGE_REQUEST = 1;

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        userProfileAsyncTask = new UserProfileAsyncTask(this, userFinder);
        userProfileAsyncTask.execute(authStore.getUserInfo().getEmail());
    }

    @Click
    public void backButton(){
        onBackPressed();
    }

    @Click
    public void changeImageButton(){
        //권한체크 후 갤러리 실행

        if(checkPermission()){
            //권한 허용
            Toast.makeText(getApplicationContext(), "권한 허가 ", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
//            startActivity(intent);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

//            Intent intent = new Intent(Intent.ACTION_PICK);
//            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, GET_PICTURE_URI);

//            Uri uri = data.getData();



        }
        else{
            //권한 요청
            onBackPressed();
            Toast.makeText(getApplicationContext(), "권한 허가 거부 ", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Profile : " , "" + resultCode);
        if (resultCode == RESULT_OK ) {

            try {
                final Uri imageUri = data.getData();
                Log.i("Profile : " , ""+data.getData());
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Log.i("Profile : ", ""+imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profileImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(/*PostImage.*/this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


    public boolean checkPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            else{
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
                     ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    return true;

                return false;
            }
        }
        else{

            return true;
        }
    }

    @Click
    public void changeStatusButton(){
        //API 호출해줘야함
        Intent intent = new Intent(this, ChangeStatusActivity_.class);
        startActivity(intent);


    }

    @Click
    public void changeNickNameButton(){

        //ChangeNickNameActivity_.intent(this).start();
        Intent intent = new Intent(this, ChangeNickNameActivity_.class);
        startActivity(intent);


    }

    public void setView(JSONResult<UserInfo> jsonResult) {
        userProfile.setView(jsonResult);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= 23) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v("ProfileActivity : ", "Permission: " + permissions[0] + "was " + grantResults[0]);
                //resume tasks needing this permission
            }
        }
    }
}
