package com.example.admin.shopnail.Presenter.StaffInformation;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangePass;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.View.ERROR_CODE;
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
    public void requestChangePassword(final String oldPass, final String newPass, final String confirmNewPass) {
//        ERROR_CODE.CHANGE_PASS_RESULT_CODE result = ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG;
//        //Send request to Server
//        result = ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK;
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 2000);
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.CHANGE_PASSWORD, UrlManager.CHANGE_PASSWORD_URL, getParamBuilder(oldPass, newPass, confirmNewPass)));
    }

    // mIStaffInforView.onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK);
    Uri.Builder getParamBuilder(String oldPass, String newPass, String confirmNewPass) {
        Uri.Builder mBuilder = new Uri.Builder();
        mBuilder.appendQueryParameter(KeyManager.ID, getDefaults(KeyManager.USER_ID, mContext));
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD, oldPass);
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD_NEW, newPass);
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD_CONFIRMATION, confirmNewPass);
        return mBuilder;
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
            case KeyManager.CHANGE_PASSWORD:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonChangePass mGsonChangePass = getGson().fromJson(s, GsonChangePass.class);
                    mIStaffInforView.onChangePasswordResult(mGsonChangePass.isStatus() ? ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK : ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG);
                } catch (Exception e) {
                    mIStaffInforView.onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG);
                }
                break;
        }

    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
