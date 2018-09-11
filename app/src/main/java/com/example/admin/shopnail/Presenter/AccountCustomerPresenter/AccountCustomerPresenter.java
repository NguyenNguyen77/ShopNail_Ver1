package com.example.admin.shopnail.Presenter.AccountCustomerPresenter;

import android.content.Context;
import android.os.Handler;

import com.example.admin.shopnail.View.SelectService.ILoginForCustomerView;
import com.example.admin.shopnail.View.SelectService.LoginForCustomerActivity;

public class AccountCustomerPresenter implements IAccountCustomer{

    ILoginForCustomerView iLoginForCustomerView;
    private boolean mResult = false;

//    public AccountCustomerPresenter(ILoginForCustomerView iLogin) {
//        this.iLoginForCustomerView = iLogin;
//    }

    public AccountCustomerPresenter(ILoginForCustomerView applicationContext) {
        this.iLoginForCustomerView = applicationContext;
    }

    @Override
    public void createAccountForCustomer(String nameCustomer, int phoneCustomer) {
        createAccount();
    }


    @Override
    public boolean checkLoginForCustomer(int phoneCustome) {
        if (phoneCustome != 0 ) {
            mResult = true;
        } else {
            mResult = false;
        }
        return mResult;
    }

    @Override
    public void sendRequestLoginForCustomer(int phoneCustomer) {
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


    private void createAccount() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                iLoginForCustomerView.onLoginResult(mResult);
            }
        }, 2000);
    }
}
