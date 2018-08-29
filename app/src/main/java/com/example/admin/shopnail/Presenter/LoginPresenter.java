package com.example.admin.shopnail.Presenter;

import android.os.Handler;
import android.widget.Toast;

import com.example.admin.shopnail.Model.Employee;
import com.example.admin.shopnail.View.ILoginView;

import static java.lang.Boolean.TRUE;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;
    private boolean mResult = false;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public boolean checkLogin(String idEmployee, String passWord) {
        if (idEmployee != null && passWord != null) {
            mResult = true;
        } else {
            mResult = false;
        }
        return mResult;
    }

    @Override
    public void onLogin(int idEmployee, String passWord) {

    }

    @Override
    public void sendRequestLogin(String userName, String passWord) {
        SendData(); //Send request to Websocket!!!
    }

    public void SendData() {    //For test
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                loginView.onLoginResult(mResult);
                handler.postDelayed(this, 2000);
            }
        }, 1500);
    }
}
