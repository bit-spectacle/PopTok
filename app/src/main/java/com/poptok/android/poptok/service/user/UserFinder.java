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

    @Get("/users/userinfo/{email}")
    List<User> findUser(@Path String email);

//    @Get("/auth/login/{email}/{password}")
//    JSONResult userLogin(@Path String email, @Path String password);

    @Get("/auth/join/{email}/{password}/{nickname}")
    List<User> userJoin(@Path String email, @Path String password, @Path String nickname);

    @Get("/auth/logout/{userNo}")
    List<User> userLogout(@Path int userNo );



}
