package com.example.admin.shopnail.Presenter.MyDetailCustom;

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
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerView;

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
    public void requestCustomerProducts(String s) {
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
                try{
                    GsonProductCustomer mGsonProductHistories = getGson().fromJson(s, GsonProductCustomer.class);
                    List<GsonProductCustomer.SuccessBean.ProductsBean> listProduct  = mGsonProductHistories.getSuccess().getProducts();
                    myDetailCustomerView.setListProducts(listProduct);
                }catch (Exception e){

                }
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

    public JSONArray getJsonRequest(String s) {
        JSONArray mJsonArray = new JSONArray();
        mJsonArray.put(s);
        return mJsonArray;
    }
}
