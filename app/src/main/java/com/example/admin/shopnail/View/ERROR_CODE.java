package com.example.admin.shopnail.View;

public class ERROR_CODE {
    public enum CHANGE_PASS_RESULT_CODE {
        RESULT_OK,
        RESULT_OLD_PASS_FAILED, //Old not corrected
        RESULT_NEW_PASS_NOT_MATCH,  //New & confirm not match
        RESULT_NG,  //Low password, ...

    };
}
