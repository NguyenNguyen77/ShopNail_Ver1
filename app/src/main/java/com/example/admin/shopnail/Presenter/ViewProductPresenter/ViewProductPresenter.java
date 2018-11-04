package com.example.admin.shopnail.Presenter.ViewProductPresenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonOrderResuilt;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonProductChoosed;
import com.example.admin.shopnail.View.ViewCartActivity.CartView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_ID;
import static com.example.admin.shopnail.Manager.KeyManager.DATE_ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.EXTRA;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_SERVICE_BY_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.PRICE;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUC_ID;
import static com.example.admin.shopnail.Manager.KeyManager.STAFF_ID;
import static com.example.admin.shopnail.Manager.KeyManager.TIME_ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID_KEY;
import static com.example.admin.shopnail.Manager.KeyManager.VALUES;
import static com.example.admin.shopnail.Manager.KeyManager.VinhCNLog;

public class ViewProductPresenter extends BaseMethod implements IViewProductPresenter, AsyncTaskCompleteListener<ResuiltObject> {

    Context mContext;
    CartView mCartView;
    List<GsonProductChoosed> arrProductChoosed;
    String jsonArrayChoosed;

    public ViewProductPresenter(Context mContext, CartView mCartView) {
        this.mContext = mContext;
        this.mCartView = mCartView;
    }

//    @Override
//    public List<ServicesOfShop> getListProduct() {
//        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
//        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
//        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
//        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
//        listService.add(Manicure);
//        listService.add(Gel_Manicure);
//        listService.add(Gel_Manicure_French_Tip);
//        return listService;
//    }

    @Override
    public void showProductChoosed(String jsonArrayProductChoose) {
        jsonArrayChoosed = jsonArrayProductChoose;
        GsonProductChoosed[] arrChoosed = getGson().fromJson(jsonArrayProductChoose, GsonProductChoosed[].class);
        arrProductChoosed = new ArrayList(Arrays.asList(arrChoosed));
        Log.d(KeyManager.VinhCNLog, jsonArrayProductChoose);
        mCartView.setAdapterProductChoosed(new ViewProductAdapter(mContext, arrProductChoosed));
        setSumPrice(arrProductChoosed);
    }

    @Override
    public void sendData() {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.ORDER_SERVICE_BY_STAFF, UrlManager.ORDER_SERVICE_BY_STAFF_URL, addJsonRequest().toString()));//For item All
    }

    JSONArray getArrayProduct(){
        JSONArray array =  new JSONArray();
        for (GsonProductChoosed pd : arrProductChoosed){
            try {
                JSONObject object = new JSONObject();
                object.put(PRODUC_ID, pd.getProductId());
                object.put(PRICE, pd.getPrice());
//                object.put(TIME_ORDER, pd.getTimeOrder());
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    JSONObject addJsonRequest(){
        JSONObject mJsonObject  = new JSONObject();
        try {
            mJsonObject.put(USER_ID_KEY, getClientID(mContext));
            mJsonObject.put(STAFF_ID, getStaffId(mContext));
            mJsonObject.put(EXTRA, mCartView.getExtraPrice());
            mJsonObject.put(DATE_ORDER, mCartView.getDateOrder());
            mJsonObject.put(VALUES, getArrayProduct());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  mJsonObject;
    }

    private void setSumPrice(List<GsonProductChoosed> arrProductChoosed) {
        int TotalPrice = 0;
        for (GsonProductChoosed object : arrProductChoosed) {
            TotalPrice = TotalPrice + Integer.parseInt(object.getPrice());
        }
        mCartView.setTotalExpectExtra(TotalPrice);
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest){
            case ORDER_SERVICE_BY_STAFF:
                try {
                    Log.d(VinhCNLog, s);
                    GsonOrderResuilt mGsonOrderResuilt = getGson().fromJson(s, GsonOrderResuilt.class);
                    mCartView.updateUIAfterOrder();
                    // here is resuilt
                    Log.d(KeyManager.VinhCNLog,"NguyenNK2 ===="+ String.valueOf(mGsonOrderResuilt.isStatus()));
                    break;
                }
                catch (Exception e){
                    mCartView.showErrorDialog(ViewManager.ERROR_CODE.GET_ORDER_FAIL);
                }

        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
