package com.example.admin.shopnail.Presenter;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.Login.GsonLogin;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;
import com.example.admin.shopnail.View.Login.ILoginView;

import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.PHONE;
import static com.example.admin.shopnail.Manager.KeyManager.TOKEN;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.VinhCNLog;

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
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, KeyManager.LOGIN, UrlManager.LOGIN_URL, getParamBuilder()));
    }

    @Override
    public void requestLoginOutSide(String mUserName) {
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, KeyManager.LOGIN_OUT_SIDE, UrlManager.LOGIN_OUT_SIDE_URL, getParamBuilderLoginOutSide(mUserName)));
    }

    private Uri.Builder getParamBuilderLoginOutSide(String mUserName) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(PHONE, mUserName);
        return builder;
    }

    public Uri.Builder getParamBuilder(){
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter(USER_NAME, getDefaults(USER_NAME, context));
        builder.appendQueryParameter(PASS_WORD, getDefaults(PASS_WORD, context));
        return builder;
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
                    setDefaults(USER_ID, String.valueOf(mGsonLogin.getSuccess().getId()), context);
                    setDefaults(TOKEN, mGsonLogin.getSuccess().getToken(), context);
                    loginView.onLoginResult(mGsonLogin.isStatus());
                }catch (Exception e){
                    loginView.onLoginResult(false);
                }
                break;
            case KeyManager.LOGIN_OUT_SIDE:
                Log.d(VinhCNLog, s);
                try{
                    GsonLoginOutSide mGsonLoginOutSide = getGson().fromJson(s, GsonLoginOutSide.class);
                    if (mGsonLoginOutSide.isStatus()==true){
                        loginView.onLoginOutSideSuccess(mGsonLoginOutSide);
                    }else {
                        loginView.onLoginOutSideFail("Login fail");
                    }
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
