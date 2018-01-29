package com.poptok.android.poptok.service.upload;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.post.PostWriteParam;
import com.poptok.android.poptok.service.Config;
import com.poptok.android.poptok.service.interceptor.CookiePreserveHttpRequestInterceptor;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Part;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;


@Rest(rootUrl = Config.apiUrl,
        converters = {MappingJackson2HttpMessageConverter.class, FormHttpMessageConverter.class},
        interceptors = { CookiePreserveHttpRequestInterceptor.class })
public interface IUploader extends RestClientHeaders {

    @Post("/upload/{destination}/{pk}")
    @RequiresHeader(HttpHeaders.CONTENT_TYPE)
    JSONResult<String> uploadFile(@Path String destination, @Path String pk, @Part("upfile") FileSystemResource image);

    @Post("/upload/{destination}/{pk}")
    @RequiresHeader(HttpHeaders.CONTENT_TYPE)
    JSONResult<String> uploadFile(@Path String destination, @Path String pk, @Body MultiValueMap<String, Object> data);
}
