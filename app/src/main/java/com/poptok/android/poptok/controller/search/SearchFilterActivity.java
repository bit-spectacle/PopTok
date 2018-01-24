package com.poptok.android.poptok.controller.search;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.poptok.android.poptok.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.search_searchfilter)
public class SearchFilterActivity extends AppCompatActivity {

    private static final String TAG = "SearchFilterActivity";
    @ViewById
    TextView textFirst;
    @ViewById
    TextView textLast;

    @ViewById
    CalendarView calendarView;

    @ViewById
    LinearLayout calendarLayout;

    @ViewById
    RadioButton radioKindFriend;

    @ViewById
    RadioButton radioPostNormal;
    @ViewById
    RadioButton radioPostAppoint;
    @ViewById
    RadioButton radioPostTresure;

    @ViewById
    EditText textSearchWord;

    String nowDateSelect = "first";

    @AfterViews
    public void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initEvent();
    }

    private void initEvent() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                if (nowDateSelect.equals("first")) {
                    textFirst.setText(date);
                } else {
                    textLast.setText(date);
                }
                calendarLayout.setVisibility(View.GONE);
            }
        });
    }

    private void calendarLayoutToggle() {
        if (calendarLayout.getVisibility() == View.GONE) {
            calendarLayout.setVisibility(View.VISIBLE);
        } else {
            calendarLayout.setVisibility(View.GONE);
        }
    }

    @Click(R.id.textFirst)
    public void textFirstClick(View v) {
        nowDateSelect = "first";
        calendarLayoutToggle();
    }

    @Click(R.id.textLast)
    public void textLastClick(View v) {
        nowDateSelect = "last";
        calendarLayoutToggle();
    }

    @Click(R.id.btnOk)
    public void btnOkClicked(View v) {
        Log.d(TAG, String.format("%s", radioKindFriend.isChecked()));
    }
}
