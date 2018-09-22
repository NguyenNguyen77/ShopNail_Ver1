package com.example.admin.shopnail.Model.StaffInfor;

public class GsonChangePass {

    /**
     * status : true
     * code : 200
     * message : Your password is updated.
     */

    private boolean status;
    private int code;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
