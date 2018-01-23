package com.poptok.android.poptok.controller;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.poptok.android.poptok.controller.user.LoginActivity_;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.auth.AuthStore_;

/**
 * Created by BIT on 2018-01-22.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthStore authStore = AuthStore_.getInstance_(this.getActivity());
        Log.d("BaseFragment", String.format("onCreate %s", authStore.isLogin()));
        if(authStore.isLogin() == false) {
            LoginActivity_.intent(this).start();
            Activity activity = getActivity();
            activity.getFragmentManager().beginTransaction().remove(this).commit();
        }
    }


}
