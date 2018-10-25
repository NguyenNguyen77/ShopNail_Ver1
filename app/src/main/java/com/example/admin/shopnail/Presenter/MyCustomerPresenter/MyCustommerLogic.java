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
import com.example.admin.shopnail.Manager.ViewManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyCustommerLogic extends BaseMethod implements IMyCustomer, AsyncTaskCompleteListener<ResuiltObject> {

    Context mContext;
    MyCustomerView myCustomerView;
    String Date;
    List<GsonGetClient.SuccessBean.ClientsBean> arrClient;
    ViewManager mViewManager = ViewManager.getInstance();

    public MyCustommerLogic(Context mContext, MyCustomerView myCustomerView) {
        this.mContext = mContext;
        this.myCustomerView = myCustomerView;
    }

    @Override
    public void requestCustomerOrder(String date) {
        this.Date = date;
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(mContext, KeyManager.GET_CLIENT_OF_STAFF, UrlManager.GET_CLIENT_OF_STAFF, getJsonRequest().toString()));
    }

    @Override
    public void tranfertoDetailCustomer(int i) {
        mViewManager.setView(ViewManager.VIEW_KEY.MY_DETAIL_CUSTOMER, getGson().toJson(arrClient.get(i)), this.Date);
    }

    private JSONObject getJsonRequest() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(KeyManager.DATE, Date);
            mJsonObject.put(KeyManager.STAFF_ID, getStaffId(mContext));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.GET_CLIENT_OF_STAFF:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonGetClient mGsonGetClient = getGson().fromJson(s, GsonGetClient.class);
                    arrClient = mGsonGetClient.getSuccess().getClients();
                    myCustomerView.setAdapterClients(arrClient);
                } catch (Exception e) {
                    Log.d(KeyManager.VinhCNLog, s);
                    arrClient = new ArrayList<>();
                    myCustomerView.setAdapterClients(arrClient);
                }
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
