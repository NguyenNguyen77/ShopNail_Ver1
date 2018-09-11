package com.example.admin.shopnail.Presenter.StaffInformation;

import android.os.Handler;

import com.example.admin.shopnail.View.ERROR_CODE;
import com.example.admin.shopnail.View.ILoginView;
import com.example.admin.shopnail.View.StaffInfo.IStaffInformation;

public class StaffInformationPresenter implements IStaffInformationPresenter {


    private IStaffInformation mIStaffInforView;
    private boolean mResult = false;

    public StaffInformationPresenter(IStaffInformation staffInformationView) {
        this.mIStaffInforView = staffInformationView;
    }


    @Override
    public void requestChangePassword(String oldPass, String newPass, String confirmNewPass) {
        ERROR_CODE.CHANGE_PASS_RESULT_CODE result = ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG;
        //Send request to Server
        result = ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIStaffInforView.onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK);
            }
        }, 2000);
    }

    @Override
    public String getStaffID() {
        String staffID = "8076";
        return staffID;
    }

    @Override
    public String getStaffName() {
        String staffName = "KhoaNguyen";
        return staffName;
    }

    @Override
    public String getStaffPhoneNumber() {
        String staffPhoneNumber = "0973603509";
        return staffPhoneNumber;
    }
}
