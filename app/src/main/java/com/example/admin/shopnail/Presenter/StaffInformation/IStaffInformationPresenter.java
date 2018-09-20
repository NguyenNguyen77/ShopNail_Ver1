package com.example.admin.shopnail.Presenter.StaffInformation;

import com.example.admin.shopnail.View.ERROR_CODE;

public interface IStaffInformationPresenter {
    void requestChangePassword(String oldPass, String newPass, String confirmNewPass);

    String getStaffID();

    String getStaffName();

    String getStaffPhoneNumber();

    void requestInfor(String defaults);
}
