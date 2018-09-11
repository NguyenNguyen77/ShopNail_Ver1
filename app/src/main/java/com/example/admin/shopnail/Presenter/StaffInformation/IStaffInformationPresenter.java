package com.example.admin.shopnail.Presenter.StaffInformation;

import com.example.admin.shopnail.View.ERROR_CODE;

public interface IStaffInformationPresenter {
    public void requestChangePassword (String oldPass, String newPass, String confirmNewPass);
    public String getStaffID ();
    public String getStaffName();
    public String getStaffPhoneNumber();
}
