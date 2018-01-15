package com.poptok.android.poptok.controller.post.marker;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.poptok.android.poptok.R;
import com.poptok.android.poptok.model.post.PostMapItem;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by BIT on 2018-01-12.
 */

@EBean
public class DefaultCalloutBalloonAdapter implements CalloutBalloonAdapter {
    LayoutInflater layoutInflater;
    private View basicBalloon;
    private View friendBalloon;
    private View groupBalloon;
    private View secretBalloon;

    @RootContext
    Context context;

    @AfterInject
    public  void init() {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        basicBalloon = layoutInflater.inflate(R.layout.post_balloon_basic, null, false);
        friendBalloon = layoutInflater.inflate(R.layout.post_balloon_friend, null, false);
        groupBalloon = layoutInflater.inflate(R.layout.post_balloon_group, null, false);
        secretBalloon = layoutInflater.inflate(R.layout.post_balloon_secret, null, false);
    }

    @Override
    public View getCalloutBalloon(MapPOIItem poiItem){
        View view;
        PostMapItem item = (PostMapItem) poiItem.getUserObject();
        switch (item.getGroupCount()) {
            case 1 :
                view = basicBalloon;
                break;
            default:
                view = groupBalloon;
                break;
        }

        // ((ImageView) defaultCalloutBalloon.findViewById(R.id.badge)).setImageURI("./");
        //여기서 조건문 image가 없을 경우~ 조건 달아서 해줘야함
        Uri uri = Uri.parse(item.getImage());
//        ((ImageView) view.findViewById(R.id.balloon_image)).setImageURI(uri);
//        ((TextView) view.findViewById(R.id.title)).setText(poiItem.getItemName());
//        ((TextView) view.findViewById(R.id.desc)).setText(item.getGroupCount());
        ((ImageView) view.findViewById(R.id.imagePost)).setImageURI(uri);
        ((TextView) view.findViewById(R.id.textPost)).setText(poiItem.getItemName());
        //((TextView) view.findViewById(R.id.textTag)).setText(item.getGroupCount());

        //defaultMarker.setMapPoint(Point);
        return view;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
        //여기서 클릭한게 뭔지 알아내서 리스트를 보여줘야함
        String name = mapPOIItem.getItemName();
        //이름 가지고 경도위도 알아내서 같은 경도 위도에 있는 애들 쫙 뿌려줘야하니까


        return null;
    }
}
