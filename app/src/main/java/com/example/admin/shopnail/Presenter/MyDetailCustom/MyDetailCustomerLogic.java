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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.CANCEL_SERVICE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUCTS;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUC_ID;
import static com.example.admin.shopnail.Manager.KeyManager.STAFF_ID;
import static com.example.admin.shopnail.Manager.KeyManager.STATUS;
import static com.example.admin.shopnail.Manager.KeyManager.UPDATE_STATUS_SERVICE;

public class MyDetailCustomerLogic extends BaseMethod implements IMyDetailCustomer, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    MyDetailCustomerView myDetailCustomerView;
    List<GsonProductCustomer.SuccessBean.ProductsBean> listProduct;

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
    public void updateServiceRequest() {
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                new CaseManager(mContext,
                        UPDATE_STATUS_SERVICE,
                        UrlManager.UPDATE_STATUS_SERVICE_URL,
                        getJsonUpdateService().toString()));
    }

    @Override
    public void cancelService() {
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                new CaseManager(mContext,
                        CANCEL_SERVICE,
                        UrlManager.CANCEL_SERVICE_SERVICE_URL,
                        getCancelServiceJson().toString()));
    }

    private JSONObject getCancelServiceJson() {
        JSONObject object = new JSONObject();
        for (int i = 0; i < listProduct.size(); i++){
            GsonProductCustomer.SuccessBean.ProductsBean pds = listProduct.get(i);
            JSONArray array = new JSONArray();
            try {
                object.put(ORDER_ID, pds.getOrderId());
                for (GsonProductCustomer.SuccessBean.ProductsBean.ProductBean pd : listProduct.get(i).getProduct()){
                    if (pd.isStatus()){
                        JSONObject child = new JSONObject();
                        child.put(PRODUC_ID, pd.getProductId());
                        array.put(child);
                    }
                }
                object.put(PRODUCTS, array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest){
            case GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY:
                try{
                    GsonProductCustomer mGsonProductHistories = getGson().fromJson(s, GsonProductCustomer.class);
                    listProduct  = mGsonProductHistories.getSuccess().getProducts();
                    myDetailCustomerView.setListProducts(listProduct);
                }catch (Exception e){

                }
                break;
            case UPDATE_STATUS_SERVICE:
                Log.d(KeyManager.VinhCNLog, s);
                break;
            case CANCEL_SERVICE:
                Log.d(KeyManager.VinhCNLog, s);
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

    public JSONObject getJsonUpdateService() {
        JSONObject object = new JSONObject();
        for (int i = 0; i < listProduct.size(); i++){
            GsonProductCustomer.SuccessBean.ProductsBean pds = listProduct.get(i);
            JSONArray array = new JSONArray();
            try {
                object.put(ORDER_ID, pds.getOrderId());
                for (GsonProductCustomer.SuccessBean.ProductsBean.ProductBean pd : listProduct.get(i).getProduct()){
                    JSONObject child = new JSONObject();
                    child.put(PRODUC_ID, pd.getProductId());
                    child.put(STATUS, pd.isStatus() ? 1 : 0);
                    array.put(child);
                }
                object.put(PRODUCTS, array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
