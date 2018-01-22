package com.poptok.android.poptok.exception;

/**
 * Created by BIT on 2018-01-19.
 */

public class HttpResponseException extends Exception {

    public HttpResponseException(){
        super("!!!!!!!!!!!!!!Http Response Exception!!!!!!!!!");
    }

    public HttpResponseException(String message){
        super(message);
    }


}
