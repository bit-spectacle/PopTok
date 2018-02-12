package com.poptok.android.poptok.view.recommend;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.location.LocationList;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by BIT on 2018-02-07.
 */
@EViewGroup(R.layout.user_placeitem)
public class RecommendLocationView extends LinearLayout {

    @ViewById(R.id.recommendPlaceName)
    TextView recommendPlaceName;

    public RecommendLocationView(Context context) {
        super(context);
    }

    public void bind(LocationList locationList){
        Log.i("LocationView : " , "bind called()");

        recommendPlaceName.setText(locationList.getBusinessName());
    }
}
