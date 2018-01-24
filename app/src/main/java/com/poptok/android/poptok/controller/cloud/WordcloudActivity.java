package com.poptok.android.poptok.controller.cloud;

import android.support.v7.app.AppCompatActivity;

import com.poptok.android.poptok.R;

import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by BIT on 2018-01-24.
 */

@EActivity(R.layout.cloud_word)
public class WordcloudActivity extends AppCompatActivity {

    @ViewById
    WordCloudView wordCloud;

    List<WordCloud> list;

    @AfterViews
    public void init() {
        list = getWordList();
        wordCloud.setDataSet(list);
        wordCloud.setSize(600,350);
        wordCloud.setColors(ColorTemplate.MATERIAL_COLORS);
        wordCloud.notifyDataSetChanged();
    }

    private List<WordCloud> getWordList() {
        String[] data = "맛,소문,터,가격,번,친구,나,단골,단골손님,손님,처음,피자,종류,한판,치킨,양념,양념치킨,비교,불가".split(",");
        List<WordCloud> list = new ArrayList<>();
        Random random = new Random();
        for (String s : data) {
            list.add(new WordCloud(s,random.nextInt(50)));
        }
        return  list;
    }

}
