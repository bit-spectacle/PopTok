package com.poptok.android.service;


import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//ID & 비밀번호 정규식 설정하는 Class
public class Util{

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[_a-zA-Z0-9-.]+@[a-zA-Z0-9-]+.[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX_ALPHA_NUM =
            Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        //여기에 이메일 중복체크하면 좋을 듯.
        return matcher.find();
    }

    public static boolean validatePassword(String pwStr){
        Matcher matcher = VALID_PASSWORD_REGEX_ALPHA_NUM.matcher(pwStr);
        return matcher.matches();
    }

}
