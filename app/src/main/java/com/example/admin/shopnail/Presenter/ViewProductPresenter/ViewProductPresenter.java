package com.example.admin.shopnail.Presenter.ViewProductPresenter;

import android.content.Context;
import android.util.Log;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonProductChoosed;
import com.example.admin.shopnail.View.ViewCartActivity.CartView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ViewProductPresenter extends BaseMethod implements IViewProductPresenter {

    Context mContext;
    CartView mCartView;
    List<GsonProductChoosed> arrProductChoosed;

    public ViewProductPresenter(Context mContext, CartView mCartView) {
        this.mContext = mContext;
        this.mCartView = mCartView;
    }

    @Override
    public List<ServicesOfShop> getListProduct() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        return listService;
    }

    @Override
    public void showProductChoosed(String jsonArrayProductChoose) {
        GsonProductChoosed[] arrChoosed = getGson().fromJson(jsonArrayProductChoose, GsonProductChoosed[].class);
//        List<GsonProductChoosed> arrProductChoosed = Arrays.asList(arrChoosed);
        arrProductChoosed = new ArrayList(Arrays.asList(arrChoosed));
        ViewProductAdapter viewProductAdapter = new ViewProductAdapter(mContext, arrProductChoosed);
        Log.d(KeyManager.VinhCNLog, jsonArrayProductChoose);
        mCartView.setAdapterProductChoosed(viewProductAdapter);


    }
}
