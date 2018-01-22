package com.poptok.android.poptok.service.user;

/**
 * Created by BIT on 2018-01-19.
 */

public class JSONResult<DataType> {
    private String code;
    private String message;
    private String sessionId;
    private DataType data;

    public JSONResult(){

    }

    public JSONResult(String code, String message, String sessionId, DataType data) {
        this.code = code;
        this.message = message;
        this.sessionId = sessionId;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}