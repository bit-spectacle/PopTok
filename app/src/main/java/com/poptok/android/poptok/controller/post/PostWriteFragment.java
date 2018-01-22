package com.poptok.android.poptok.controller.post;


import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.controller.user.LoginActivity_;
import com.poptok.android.poptok.model.auth.AuthStore;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.post_write)
public class PostWriteFragment extends Fragment {

    @ViewById(R.id.txtWriteTest)
    TextView txtWriteTest;

    @Click(R.id.txtWriteTest)
    public void onClickTest2(View view) {
        Log.d("onClickTest2", "onClickTest2");
    }

    @Bean
    AuthStore authStore;

    @AfterViews
    public void init() {

        if(authStore.isLogin() == false ) {
            //Intent loginIntent = new Intent(this.getActivity(), LoginActivity_.class);
            LoginActivity_.intent(this.getActivity()).start();
        }
    }

}
