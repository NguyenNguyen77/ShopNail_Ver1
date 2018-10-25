package com.example.admin.shopnail.Presenter.MyDetailCustom;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Half;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductHistories;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerView;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY;

public class MyDetailCustomerLogic extends BaseMethod implements IMyDetailCustomer, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    MyDetailCustomerView myDetailCustomerView;

    public MyDetailCustomerLogic(Context mContext, MyDetailCustomerView myDetailCustomerView) {
        this.mContext = mContext;
        this.myDetailCustomerView = myDetailCustomerView;
    }

    @Override
    public void requestCustomerProducts(List<String> s) {
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                new CaseManager(mContext,
                GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY,
                UrlManager.GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY_URL,
                getJsonRequest(s).toString()));
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest){
            case GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY:
                GsonProductHistories mGsonProductHistories = getGson().fromJson(s, GsonProductHistories.class);
                // TODO: 10/24/2018 conflict idea
                Log.d(KeyManager.VinhCNLog, s);
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

    public JSONArray getJsonRequest(List<String> s) {
        JSONArray mJsonArray = new JSONArray();
        for (String id: s){
            mJsonArray.put(id);
        }
        return mJsonArray;
    }
}
