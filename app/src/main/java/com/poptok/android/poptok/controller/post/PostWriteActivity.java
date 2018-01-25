package com.poptok.android.poptok.controller.post;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.post.PostWriteParam;
import com.poptok.android.poptok.service.post.IPostItemFinder;
import com.poptok.android.poptok.service.post.PostWriteAsyncTask;
import com.poptok.android.poptok.view.post.PostWrite;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.IOException;

@EActivity(R.layout.post_write)
public class PostWriteActivity extends BaseActivity {

    private static final String TAG = "PostWriteActivity";
    private final int GALLERY_CODE = 1112;

    InputMethodManager imm;

    @RestService
    IPostItemFinder postItemFinder;

    @ViewById(R.id.radioGroupKind)
    RadioGroup radioGroupKind;
    @ViewById(R.id.radioKindNormal)
    RadioButton radioKindNormal;
    @ViewById(R.id.radioKindAppoint)
    RadioButton radioKindAppoint;
    @ViewById(R.id.editKakaoLink)
    EditText editKakaoLink;

    @ViewById(R.id.radioGroupOpen)
    RadioGroup radioGroupOpen;

    @ViewById(R.id.editContent)
    EditText editContent;
    @ViewById(R.id.editHash)
    EditText editHash;

    @Bean
    AuthStore authStore;

    @Bean
    PostWrite postWrite;

    Uri imageUri;

    PostWriteAsyncTask postWriteAsyncTask;

    boolean isKindAppointChecked = false;
    boolean isOpenChecked = true;

    PostWriteParam param;

    @AfterViews
    public void init() {
        param = new PostWriteParam();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initEvent();
    }

    private void initEvent() {
        radioGroupKind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioKindNormal) {
                    editKakaoLink.setVisibility(View.GONE);
                    isKindAppointChecked = false;
                } else {
                    editKakaoLink.setVisibility(View.VISIBLE);
                    isKindAppointChecked = true;
                }
            }
        });

        radioGroupOpen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioOpenAll) {
                    isOpenChecked = true;
                } else {
                    isOpenChecked = false;
                }
            }
        });
    }

    @Click(R.id.layoutPostWrite)
    public void layoutPostWriteClicked(View v) {
        hideKeyboard();
    }


    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(editKakaoLink.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editContent.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editHash.getWindowToken(), 0);
    }

    @Click(R.id.btnWriteOk)
    public void btnWriteOkClick(View v) {

        param.setUserNo(authStore.getUserInfo().getUserNo());

        // 컨텐츠 내용
        String content = editContent.getText().toString();
        if (content == null || content.trim().length() == 0) {
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_LONG).show();
            editContent.requestFocus();
            return;
        }
        param.setContent(content);

        // 해시
        String tag = editHash.getText().toString();
        if (tag != null && tag.length() > 0) {
            param.setTag(tag);
        }

        // 글 종류 일반/모임
        if (isKindAppointChecked) {
            String kakaoLink = editKakaoLink.getText().toString();
            if (kakaoLink == null || kakaoLink.length() == 0) {
                Toast.makeText(this, "카카오 오픈 채팅 링크를 입력해주세요.", Toast.LENGTH_LONG).show();
                editKakaoLink.requestFocus();
                return;
            } else {
                param.setKakaoLink(kakaoLink);
            }
        }
        int posttype = isKindAppointChecked ? 1 : 0;
        param.setPosttype(posttype);

        // 오픈 타입 모두/친구
        int opentype = isOpenChecked ? 0 : 1;
        param.setOpentype(opentype);

        // 이미지
        param.setImage("");

        // Todo 상점 번호
        param.setLocationNo(0);

        // Todo 위치
        LatLng location = new LatLng(0, 0);
        param.setLatitude(location.latitude);
        param.setLongitude(location.longitude);

        postWriteAsyncTask = new PostWriteAsyncTask(postItemFinder, this);
        postWriteAsyncTask.execute(param);
    }

    public void writeResultHandler(JSONResult<Integer> result) {
        Log.d(TAG, String.format("%s", result.getCode()));

        //Bitmap image = getBitmap(this.imageUri);
//        File imageFile = new File(String.valueOf(this.imageUri));
//        if(imageFile != null && imageFile.exists()) {
//            FileSystemResource fileSystemResource = new FileSystemResource(imageFile);
//
//        }
    }


    @Click
    public void btnSelectImageClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    imageUri = data.getData();
                    break;
                default:
                    break;
            }
        }
    }

    private Bitmap getBitmap(Uri imgUri) {

        String imagePath = getRealPathFromURI(imgUri); // path 경로
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//경로를 통해 비트맵으로 전환
        //ivImage.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기
        return bitmap;

    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
}
