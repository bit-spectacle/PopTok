package com.poptok.android.poptok.service.recommend;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.location.LocationList;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by BIT on 2018-02-07.
 */
@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = {CookiePreserveHttpRequestInterceptor.class})
public interface IRecommendFinder {

    @Get("/recommend/recommendLocation/{userNo}")
    JSONResult<List<LocationList>> getRecommendLocation(@Path int userNo);

//    @Get("/recommend/GetLocationInfo/{locationNo}")
//    JSONResult<LocationList> getLocationInfo(@Path int locationNo);
//
//    @Get("/recommend/ShowLocation/{locationNo}")
//    JSONResult<LocationList> showLocation(@Path int locationNo);




}
