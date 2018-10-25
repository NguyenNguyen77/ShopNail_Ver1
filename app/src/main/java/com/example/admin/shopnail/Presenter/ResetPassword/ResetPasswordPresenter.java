package com.example.admin.shopnail.Presenter.ResetPassword;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.ResetPassword.GsonResuilt;
import com.example.admin.shopnail.View.ResetPassword.ResetPasswordView;

import static com.example.admin.shopnail.Manager.KeyManager.FORGOT_PASSWORD;

public class ResetPasswordPresenter extends BaseMethod implements IResetPassword, AsyncTaskCompleteListener<ResuiltObject> {

    ResetPasswordView mResetPasswordView;
    Context mContext;

    public ResetPasswordPresenter(ResetPasswordView mResetPasswordView, Context mContext) {
        this.mResetPasswordView = mResetPasswordView;
        this.mContext = mContext;
    }

    @Override
    public void ResetPassWord(String s) {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.FORGOT_PASSWORD, UrlManager.FORGOT_PASSWORD_URL, getParamBuilder(s)));
    }


    Uri.Builder getParamBuilder(String phoneCustomer) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(KeyManager.PHONE, phoneCustomer);
        return builder;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case FORGOT_PASSWORD:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonResuilt gsonResuilt = getGson().fromJson(s, GsonResuilt.class);
                    mResetPasswordView.showToastResuilt(gsonResuilt.isStatus() ? gsonResuilt.getSuccess().getMessage() : "Fail to reset");
                } catch (Exception e) {
                    mResetPasswordView.showToastResuilt("This account is out of data");
                }
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
