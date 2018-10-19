package com.example.admin.shopnail.Presenter.MyCustomerPresenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.View.MyCustomer.MyCustomerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCustommerLogic extends BaseMethod implements IMyCustomer, AsyncTaskCompleteListener<ResuiltObject> {

    Context mContext;
    MyCustomerView myCustomerView;

    public MyCustommerLogic(Context mContext, MyCustomerView myCustomerView) {
        this.mContext = mContext;
        this.myCustomerView = myCustomerView;
    }

    @Override
    public void requestCustomerOrder() {
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(mContext, KeyManager.GET_CLIENT_OF_STAFF, UrlManager.GET_CLIENT_OF_STAFF, getJsonRequest().toString()));
    }

    private JSONObject getJsonRequest(){
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(KeyManager.DATE, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            mJsonObject.put(KeyManager.STAFF_ID, getStaffId(mContext));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest){
            case KeyManager.GET_CLIENT_OF_STAFF:
                Log.d(KeyManager.VinhCNLog, s);
                GsonGetClient mGsonGetClient = getGson().fromJson(s, GsonGetClient.class);
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
