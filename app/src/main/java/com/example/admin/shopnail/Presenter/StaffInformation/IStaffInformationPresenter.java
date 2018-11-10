package com.example.admin.shopnail.Presenter.StaffInformation;

import com.example.admin.shopnail.View.ERROR_CODE;

public interface IStaffInformationPresenter {
    void requestChangePassword(String oldPass, String newPass, String confirmNewPass);

    void requestInfor(String defaults);
    void requestChangeAvatar (String path);
}
