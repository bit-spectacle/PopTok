package com.poptok.android.poptok.service.hash;

import com.poptok.android.poptok.model.hash.TagItem;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class, FormHttpMessageConverter.class},
        interceptors = { CookiePreserveHttpRequestInterceptor.class })
public interface IHashfinder {

    @Get("/hash/list/{count}")
    List<TagItem> findHashList(@Path int count);
}
