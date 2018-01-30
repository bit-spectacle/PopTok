package com.poptok.android.poptok.controller.post;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.AppBaseActivity;
import com.poptok.android.poptok.controller.BaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.post.PostWriteParam;
import com.poptok.android.poptok.model.store.StoreItem;
import com.poptok.android.poptok.model.upload.UploadParam;
import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.service.post.IPostItemFinder;
import com.poptok.android.poptok.service.post.PostWriteAsyncTask;
import com.poptok.android.poptok.service.upload.FileUploadAsyncTask;
import com.poptok.android.poptok.service.upload.IUploader;
import com.poptok.android.poptok.tools.KeyboardHelper;
import com.poptok.android.poptok.tools.RealPathUtil;
import com.poptok.android.poptok.view.post.PostWrite;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.core.io.FileSystemResource;

@EActivity(R.layout.post_write)
public class PostWriteActivity extends BaseActivity implements IAsyncResultHandler<JSONResult<String >> {

    private static final String TAG = "PostWriteActivity";
    private final int GALLERY_CODE = 1112;

    @RestService
    IPostItemFinder postItemFinder;
    @RestService
    IUploader iUploader;

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
    FileUploadAsyncTask fileUploadAsyncTask;

    boolean isKindAppointChecked = false;
    boolean isOpenChecked = true;

    PostWriteParam param;

    @AfterViews
    public void init() {
        param = new PostWriteParam();

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
        KeyboardHelper.hideKeyboard(this, editKakaoLink, editContent, editHash);
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
        String tag = editHash.getText().toString().replace(" ", "");
        if (tag != null && tag.length() > 0) {
            String[] splited = tag.split(",");
            String resultTag = "";
            for(String word : splited) {
                if(word.startsWith("#") == false) {
                    word = "#" + word;
                }
                if(resultTag.length() > 0) resultTag += ",";
                resultTag += word;
            }
            param.setTag(resultTag);
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

        // 주변 상점이나 내위치 선택
        StoreItem storeItem = postWrite.getStoreItem();
        param.setLocationNo(storeItem.getLocationNo());
        param.setLatitude(storeItem.getLatitude());
        param.setLongitude(storeItem.getLongitude());

        postWriteAsyncTask = new PostWriteAsyncTask(postItemFinder, this);
        postWriteAsyncTask.execute(param);
    }

    public void writeResultHandler(JSONResult<Integer> result) {
        Log.d(TAG, String.format("%s", result.getCode()));

        if(imageUri != null) {
            String realPath = RealPathUtil.getRealPath(this, imageUri);
            FileSystemResource image = new FileSystemResource(realPath);
            UploadParam param = new UploadParam("post", String.valueOf(result.getData()), image);

            fileUploadAsyncTask = new FileUploadAsyncTask(iUploader, this);
            fileUploadAsyncTask.execute(param);
        }
        else {
            goToMain();
        }
    }

    private void goToMain() {
        Intent intent = new Intent(this, AppBaseActivity.class);
        startActivity(intent);
        this.finish();
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

    @Override
    public void resultHandler(JSONResult<String> result) {
        Log.d(TAG, result.getData());
        goToMain();
    }
}
