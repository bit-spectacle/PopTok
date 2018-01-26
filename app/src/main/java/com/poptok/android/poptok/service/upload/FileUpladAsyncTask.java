package com.poptok.android.poptok.service.upload;

import android.app.Activity;
import android.os.AsyncTask;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.upload.UploadParam;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class FileUpladAsyncTask extends AsyncTask<UploadParam, String, JSONResult<String>> {

    IUploader iUploader;
    IUploadResult<JSONResult<String>> iUploadResult;

    public FileUpladAsyncTask(IUploader iUploader, IUploadResult<JSONResult<String>> iUploadResult) {
        this.iUploader = iUploader;
        this.iUploadResult = iUploadResult;
    }

    @Override
    protected JSONResult<String> doInBackground(UploadParam... uploadParams) {
        UploadParam param = uploadParams[0];
        //JSONResult<String> jsonResult = iUploader.uploadFile(param.getDestination(), param.getPk(), param.getImage());

        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.set("upfile", param.getImage());
        iUploader.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
        JSONResult<String> jsonResult = iUploader.uploadFile(param.getDestination(), param.getPk(), data);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<String> jsonResult) {
        super.onPostExecute(jsonResult);
        iUploadResult.uplodResultHandler(jsonResult);
    }
}
