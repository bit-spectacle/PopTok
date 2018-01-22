package com.poptok.android.poptok.exception;

/**
 * Created by BIT on 2018-01-19.
 */

public class JSONResultException extends Exception {

    public JSONResultException(){
        super("!!!!!!!!!!JSON Result Exception Occurs!!!!!!!!!!!!");
    }

    public JSONResultException(String message){
        super(message);
    }
}
