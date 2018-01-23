package com.poptok.android.poptok.service.location;

import com.poptok.android.poptok.model.ApiResult;
import com.poptok.android.poptok.model.location.LocationLocalLog;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;


@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = { CookiePreserveHttpRequestInterceptor.class })
public interface ILocationReporter {

    @Get("/report/location/{userNo}")
    ApiResult reportLocation(@Path int userNo);

    @Post("/report/location/{userNo}")
    ApiResult reportLocation(@Path int userNo, @Body List<LocationLocalLog> locationLogList);
}
