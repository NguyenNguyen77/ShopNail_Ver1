package com.example.admin.shopnail.Presenter.StaffInformation;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.View.ERROR_CODE;
import com.example.admin.shopnail.View.ILoginView;
import com.example.admin.shopnail.View.StaffInfo.IStaffInformation;

public class StaffInformationPresenter extends BaseMethod implements IStaffInformationPresenter, AsyncTaskCompleteListener<ResuiltObject> {


    private IStaffInformation mIStaffInforView;
    private boolean mResult = false;
    Context mContext;

    public StaffInformationPresenter(IStaffInformation staffInformationView, Context context) {
        this.mIStaffInforView = staffInformationView;
        mContext = context;
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

    @Override
    public void requestInfor(String defaults) {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_USER_BY_ID, UrlManager.GET_USER_BY_ID_URL + defaults, getParamBuilder()));
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.GET_USER_BY_ID:
                try {
                    GsonStaffInfor mGsonStaffInfor = getGson().fromJson(s, GsonStaffInfor.class);
                    mIStaffInforView.setStaffInfor(mGsonStaffInfor.getSuccess());
                } catch (Exception e) {
                    mIStaffInforView.showError();
                }
                break;
        }

    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
