package com.example.admin.shopnail.Presenter.AccountCustomerPresenter;

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
import com.example.admin.shopnail.Model.LoginForCustomer.GsonCustomerCreate;
import com.example.admin.shopnail.View.SelectService.ILoginForCustomerView;
import com.example.admin.shopnail.View.SelectService.LoginForCustomerActivity;

import static com.example.admin.shopnail.Manager.KeyManager.CREATE_ACCOUNT_CUSTOMER;

public class AccountCustomerPresenter extends BaseMethod implements IAccountCustomer, AsyncTaskCompleteListener<ResuiltObject> {

    ILoginForCustomerView iLoginForCustomerView;
    Context mContext;
    private boolean mResult = false;

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
        if (phoneCustome != "" ) {
            mResult = true;
        } else {
            mResult = false;
        }
        return mResult;
    }

    @Override
    public void sendRequestLoginForCustomer(String phoneCustomer) {
        SendData(); //Send request to Websocket!!!
    }

    private void SendData() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                iLoginForCustomerView.onLoginResult(mResult);
            }
        }, 2000);
    }


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


    Uri.Builder getParamBuilder(String fullname, String phone){
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(KeyManager.FULL_NAME, fullname);
        builder.appendQueryParameter(KeyManager.PHONE_NUMBER, phone);
        return  builder;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
            switch (CaseRequest){
                case CREATE_ACCOUNT_CUSTOMER:
                    Log.d(KeyManager.VinhCNLog, s);
                    try{
                        GsonCustomerCreate mGsonCustomerCreate = getGson().fromJson(s, GsonCustomerCreate.class);
                        iLoginForCustomerView.onLoginResult(mGsonCustomerCreate.isStatus());
                    }catch (Exception e){
                        iLoginForCustomerView.onLoginResult(false);
                    }
                    break;
            }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
