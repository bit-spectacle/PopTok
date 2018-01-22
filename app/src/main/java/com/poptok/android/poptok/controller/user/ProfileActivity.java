package com.poptok.android.poptok.controller.user;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-01-22.
 */


@EActivity(R.layout.user_profile)
public class ProfileActivity extends AppCompatActivity {

    @ViewById(R.id.btnDetailBack)
    Button btnDetailBack;




}
