package com.poptok.android.poptok.service.post;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.poptok.android.poptok.model.post.PostItem;

import java.util.List;

@Rest(rootUrl = "http://192.168.1.9:3000", converters = {MappingJackson2HttpMessageConverter.class})
public interface IPostItemFinder {

    @Get("/posting")
    List<PostItem> findAll();
}
