package com.example.admin.shopnail.Presenter.AccountCustomerPresenter;

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
import com.example.admin.shopnail.Model.LoginForCustomer.GsonCMOldLogin;
import com.example.admin.shopnail.Model.LoginForCustomer.GsonCustomerCreate;
import com.example.admin.shopnail.View.LoginCustomer.ILoginForCustomerView;

import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_ID;
import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.CREATE_ACCOUNT_CUSTOMER;
import static com.example.admin.shopnail.Manager.KeyManager.CUSTOMER_PHONE_NUMBER;

public class AccountCustomerPresenter extends BaseMethod implements IAccountCustomer, AsyncTaskCompleteListener<ResuiltObject> {

    ILoginForCustomerView iLoginForCustomerView;
    Context mContext;
    private boolean mResult = false;
    String phoneNumber;

//    public AccountCustomerPresenter(ILoginForCustomerView iLogin) {
//        this.iLoginForCustomerView = iLogin;
//    }


    public AccountCustomerPresenter(ILoginForCustomerView iLoginForCustomerView, Context mContext) {
        this.iLoginForCustomerView = iLoginForCustomerView;
        this.mContext = mContext;
    }

    @Override
    public void createAccountForCustomer(String nameCustomer, String phoneCustomer) {
        createAccount(nameCustomer, phoneCustomer);
    }


    @Override
    public boolean checkLoginForCustomer(String phoneCustome) {
        if (phoneCustome != "") {
            mResult = true;
        } else {
            mResult = false;
        }
        return mResult;
    }

    @Override
    public void sendRequestLoginForCustomer(String phoneCustomer) {
//        SendData(); //Send request to Websocket!!!
        phoneNumber = phoneCustomer;
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.LOGIN_OLD_CUSTOMER, UrlManager.LOGIN_OLD_CUSTOMER_URL, getParamBuilder(phoneCustomer)));
    }


    Uri.Builder getParamBuilder(String phoneCustomer) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(KeyManager.PHONE, phoneCustomer);
        return builder;
    }

//    private void SendData() {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//                iLoginForCustomerView.onLoginResult(mResult);
//            }
//        }, 2000);
//    }


    private void createAccount(String fullname, String phone) {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//                iLoginForCustomerView.onLoginResult(mResult);
//            }
//        }, 2000);
        new NailTask(this).execute(new CaseManager(mContext, CREATE_ACCOUNT_CUSTOMER, UrlManager.CREATE_ACCOUNT_CUSTOMER_URL, getParamBuilder(fullname, phone)));
    }


    Uri.Builder getParamBuilder(String fullname, String phone) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(KeyManager.FULL_NAME, fullname);
        builder.appendQueryParameter(KeyManager.PHONE, phone);
        return builder;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        Log.d(KeyManager.VinhCNLog, s);
        switch (CaseRequest) {
            case CREATE_ACCOUNT_CUSTOMER:
                try {
                    GsonCustomerCreate mGsonCustomerCreate = getGson().fromJson(s, GsonCustomerCreate.class);
                    iLoginForCustomerView.onLoginResult(mGsonCustomerCreate.isStatus());
                } catch (Exception e) {
                    iLoginForCustomerView.onLoginResult(false);
                }
                break;
            case KeyManager.LOGIN_OLD_CUSTOMER:
                try {
                    GsonCMOldLogin mGsonCMOldLogin = getGson().fromJson(s, GsonCMOldLogin.class);
                    setDefaults(CLIENT_NAME, mGsonCMOldLogin.getSuccess().getFullname(), mContext);
                    setDefaults(CLIENT_ID, String.valueOf(mGsonCMOldLogin.getSuccess().getUser_id()), mContext);
                    setDefaults(CUSTOMER_PHONE_NUMBER, phoneNumber, mContext);
                    iLoginForCustomerView.onLoginResult(mGsonCMOldLogin.isStatus());
                } catch (Exception e) {
                    iLoginForCustomerView.onLoginResult(false);
                }
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
