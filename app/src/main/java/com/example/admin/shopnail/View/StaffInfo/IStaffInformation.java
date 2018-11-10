package com.example.admin.shopnail.View.StaffInfo;

import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.View.ERROR_CODE;

public interface IStaffInformation {
    public void onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE resultCode);

    void showError();

    void setStaffInfor(GsonStaffInfor.SuccessBean success);
    void updatePermission ();
}