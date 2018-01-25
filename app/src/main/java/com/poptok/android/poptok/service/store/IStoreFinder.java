package com.poptok.android.poptok.service.store;

import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = { CookiePreserveHttpRequestInterceptor.class })
public interface IStoreFinder {

//    @Get("/report/location/{userNo}")
//    ApiResult reportLocation(@Path int userNo);
}
