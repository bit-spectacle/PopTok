package com.poptok.android.poptok.controller.post;


import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.poptok.android.poptok.R;

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

}
