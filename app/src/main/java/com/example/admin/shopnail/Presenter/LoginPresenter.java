package com.example.admin.shopnail.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.Login.GsonLogin;
import com.example.admin.shopnail.View.ILoginView;
import com.google.gson.Gson;

public class LoginPresenter extends BaseMethod implements ILoginPresenter, AsyncTaskCompleteListener<ResuiltObject> {

    Context context;
    private ILoginView loginView;
    private boolean mResult = false;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
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
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, KeyManager.LOGIN, UrlManager.LOGIN_URL));
    }


    public void SendData() {    //For test
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

            }
        }, 1000);
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.LOGIN:
                try{
                    GsonLogin mGsonLogin  = getGson().fromJson(s, GsonLogin.class);
                    loginView.onLoginResult(mGsonLogin.isStatus());
                }catch (Exception e){
                    loginView.onLoginResult(false);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {
        loginView.onLoginResult(false);
    }
}
