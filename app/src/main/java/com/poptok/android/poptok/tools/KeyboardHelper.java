package com.poptok.android.poptok.tools;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHelper {
    public static void hideKeyboard(Context context, View... views) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        for(View v : views) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
