//package com.poptok.android.poptok.service.user;
//
//import android.os.AsyncTask;
//
//import com.poptok.android.poptok.controller.user.ProfileActivity;
//import com.poptok.android.poptok.model.JSONResult;
//import com.poptok.android.poptok.model.auth.AuthStore;
//
///**
// * Created by BIT on 2018-01-29.
// */
//
//public class ChangeProfileImageAsyncTask  extends AsyncTask<String, String, JSONResult<String>> {
//    IUserFinder iUserFinder;
//    AuthStore authStore;
//    ProfileActivity profileActivity;
//
//    public ChangeProfileImageAsyncTask(AuthStore authStore, IUserFinder iUserFinder, ProfileActivity activity){
//        this.authStore = authStore;
//        this.iUserFinder = iUserFinder;
//        this.profileActivity = activity;
//    }
//
//    @Override
//    protected JSONResult<String> doInBackground(String... imageUrl) {
//        JSONResult<String> jsonResult = iUserFinder.updateImage(authStore.getUserInfo().getUserNo(), imageUrl[0]);
//        authStore.setUserImage(imageUrl[0]);
//        return jsonResult;
//    }
//
//    @Override
//    protected void onPostExecute(JSONResult<String> jsonResult) {
//        super.onPostExecute(jsonResult);
//        this.profileActivity.uploadImageHandler(jsonResult);
//    }
//
//
//
//
//}
