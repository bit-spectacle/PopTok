package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-25.
 */

public class ChangeStatusParam {
    public String status;
    public int userNo;

    public ChangeStatusParam(String status , int userNo){
        this.status = status;
        this.userNo = userNo;
    }

    public static ChangeStatusParam getChangeStatusParam(String status , int userNo)
    {
        ChangeStatusParam changeStatusParam = new ChangeStatusParam(status, userNo);
        return changeStatusParam;
    }

}
