package com.poptok.android.poptok.controller.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.upload.UploadParam;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.upload.FileUploadAsyncTask;
import com.poptok.android.poptok.service.upload.IUploader;
import com.poptok.android.poptok.service.user.IUserFinder;
import com.poptok.android.poptok.service.user.UserProfileAsyncTask;
import com.poptok.android.poptok.tools.RealPathUtil;
import com.poptok.android.poptok.view.user.UserProfile;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by BIT on 2018-01-22.
 */


@EActivity(R.layout.user_profile)
public class ProfileActivity extends BaseActivity implements IAsyncResultHandler<JSONResult<String >> {

    @ViewById(R.id.profileImage)
    ImageView profileImage;

    @ViewById(R.id.textProfileNick)
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

    @RestService
    IUploader iUploader;

    @Bean
    UserProfile userProfile;


    Uri imageUri;

    UserProfileAsyncTask userProfileAsyncTask;
    FileUploadAsyncTask fileUploadAsyncTask;
//    ChangeProfileImageAsyncTask changeProfileImageAsyncTask;

    private final int GALLERY_CODE = 1112;


    @Bean
    AuthStore authStore;

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

//
//        Glide.with(this).load(authStore.getUserInfo().getProfileImage())
//                .apply(new RequestOptions().placeholder(R.drawable.poptok_logo))
//                .into(profileImage);
//        Glide.with(this).load(authStore.getUserInfo().getProfileImage())
//                .apply(new RequestOptions().signature(new ObjectKey(UUID.randomUUID().toString())))
//                .into(profileImage);
//        Glide.with(this).load(authStore.getUserInfo().getProfileImage()).apply(
//                new RequestOptions().bitmapTransform(
//                        new CropCircleTransformation()).centerCrop().override(600,600)).into(profileImage);
//        Glide.with(this).load(authStore.getUserInfo().getProfileImage()).apply(new RequestOptions().centerCrop()
//                .override(600,600))
//                .into(profileImage);

//        profileImage.setVisibility(View.VISIBLE);
//        String profileImage = authStore.getUserInfo().getProfileImage();

//        URL url = new URL(profileImage);
//
//        profileImage.setImageResource(URLEncoder.encode(authStore.getUserInfo().getProfileImage(),"UTF-8"));

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

        if(checkPermission()) {
            //권한 허용
            Toast.makeText(getApplicationContext(), "권한 허가 ", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_CODE);
        }
        else{
            //권한 요청
            onBackPressed();
            Toast.makeText(getApplicationContext(), "권한 허가 거부 ", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    imageUri = data.getData();

                    if(imageUri != null ){
                        String realPath = RealPathUtil.getRealPath(this, imageUri);
                        FileSystemResource image = new FileSystemResource(realPath);
                        UploadParam param = new UploadParam("user", String.valueOf(authStore.getUserInfo().getUserNo()), image);

                        fileUploadAsyncTask = new FileUploadAsyncTask(iUploader, this);
                        fileUploadAsyncTask.execute(param);

                    }else
                    {
                        Toast.makeText(getApplicationContext(), "이미지 변경 실패 ", Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    break;
            }
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




    @Override
    public void resultHandler(JSONResult<String> result){
        Log.i("ProfileActivity : ", ""+result.getData());
        authStore.setUserImage(result.getData());
        finish();
        ProfileActivity_.intent(this).start();

        Glide.with(this).load(result.getData()).apply(
                new RequestOptions().centerCrop().override(600,600)
                        ).into(profileImage);

        //.signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
//
//        Glide.with(this).load(result.getData())
//                .apply(new RequestOptions().signature(new ObjectKey(UUID.randomUUID().toString())))
//                .into(profileImage);


//        Uri imagesrc = Uri.parse(result.getData());
//        profileImage.setImageURI(imagesrc);
        profileImage.invalidate();
//.into(profileImage);
//        ProfileActivity_.intent(this);
//        startActivity(this, profileActivity_.class);
//        goToProfile();

    }

    private void goToProfile() {
        Intent intent = new Intent(this, ProfileActivity_.class);
        startActivity(intent);
    }
}
