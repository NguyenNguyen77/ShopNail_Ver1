package com.example.admin.shopnail.Presenter;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.BaseMethod;
import com.example.admin.shopnail.KeyManager;
import com.example.admin.shopnail.Model.Employee;
import com.example.admin.shopnail.View.ILoginView;

import static java.lang.Boolean.TRUE;

public class LoginPresenter extends BaseMethod implements ILoginPresenter {

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
        setUserName(userName); setPassWord(passWord);
//        SendData(); //Send request to Websocket!!!

        new LoginTask().execute("http://142.93.29.45:8888/api/login");
    }


    class LoginTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return makePostRequestLogin(strings[0], getUserName(), getPassWord());
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(KeyManager.VinhCNLog, s);
            super.onPostExecute(s);
        }
    }








    public void SendData() {    //For test
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                loginView.onLoginResult(mResult);
            }
        }, 1000);
    }
}
