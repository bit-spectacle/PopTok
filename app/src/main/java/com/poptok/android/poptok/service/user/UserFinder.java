package com.poptok.android.poptok.service.user;

import com.poptok.android.poptok.model.user.User;
import com.poptok.android.poptok.service.HttpBasicAuthenticatorInterceptor;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.1.39:3000",
        converters = {MappingJackson2HttpMessageConverter.class},
        interceptors = {HttpBasicAuthenticatorInterceptor.class})
public interface UserFinder {

    @Get("/Users/userinfo/{email}")
    List<User> findUser(@Path String email);

    @Get("/Users/login/{email}/{password}")
    List<User> userLogin(@Path String email, @Path String password);

}
