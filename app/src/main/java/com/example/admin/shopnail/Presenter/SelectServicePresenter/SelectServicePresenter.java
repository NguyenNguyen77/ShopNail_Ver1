package com.example.admin.shopnail.Presenter.SelectServicePresenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.admin.shopnail.Adapter.CategoryAdapter;
import com.example.admin.shopnail.Adapter.SelectServiceAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.CustomViewListExpand.SingleToast;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonCategory;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonProductsByCategory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.View.SelectService.ISelectServiceView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.example.admin.shopnail.Manager.KeyManager.LIMIT_PRODUCT;

public class SelectServicePresenter extends BaseMethod implements ISelectServicePresenter, AsyncTaskCompleteListener<ResuiltObject> {

    ISelectServiceView mISelectServiceView;
    Context mContext;
    ArrayAdapter<String> adapterCategory;
    List<String> paths;
    List<GsonCategory.SuccessBean.DataBean> arrCategory;
    List<GsonCategory.SuccessBean.DataBean> arrCategoryJson;
    GsonCategory.SuccessBean.DataBean categoryAll;
    int categoryID;
    List<GsonProductsByCategory.SuccessBean.DataBean> arrProduct = new ArrayList<>();
    SelectServiceAdapter adapter;

    public SelectServicePresenter(ISelectServiceView mISelectServiceView, Context mContext) {
        this.mISelectServiceView = mISelectServiceView;
        this.mContext = mContext;
    }

    @Override
    public List<ServicesOfShop> getListDataAcrylic() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("Manicure", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("Gel Manicure", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("Gel Manicure w/ French Tip", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("Spa Pedicure (Sea Salt & Hot Towel)", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("Spa Pedicure w/ Gel Polish (Sea Salt & Hot Towel)", 10, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }

    @Override
    public List<ServicesOfShop> getListNaturalNails() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("ddddddddddddd", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("eeeeeeeeeeeeee", 10, "http//...");

        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }

    @Override
    public List<ServicesOfShop> getListWaxingFacial() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("xxxxxxxxxxxxxxx", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("yyyyyyyyyy", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("zzzzzzzzzzzzz", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("ooooooooooooooooooo", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("llllllllllllllll", 10, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }


    @Override
    public void RequestCategory() {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_CATEGORY_LIST, UrlManager.GET_CATEGORY_LIST_URL, getParamBuidler()));
    }


    @Override
    public void requestProduct(int pos) {
//        if (pos == 0) {
//            new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_PRODUCTS_BY_CATEGORY, UrlManager.GET_PRODUCTS_BY_CATEGORY_URL, getParamBuidler()));//For item All
//        } else {
//            if (pos >= arrCategory.size()) {
//                return;
//            }
//            String url = UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + arrCategory.get(pos - 1).getId();
//            new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_PRODUCTS_BY_CATEGORY, UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + arrCategory.get(pos - 1).getId(), getParamBuidler()));
//        }
//        String url = arrCategory.get(pos).getId() == 0 ? UrlManager.GET_PRODUCTS_BY_CATEGORY_URL : UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + arrCategory.get(pos).getId();
        setPositionPage(1);
        categoryID = arrCategory.get(pos).getId();
        String url = arrCategory.get(pos).getId() == 0 ? (UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + LIMIT_PRODUCT + "/" + getPositionPage()) : ((UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + LIMIT_PRODUCT + "/" + getPositionPage()) + "/" + arrCategory.get(pos).getId());
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_PRODUCTS_BY_CATEGORY, url, getParamBuidler()));//For item All


    }

    @Override
    public void startScroll() {
        setPositionPage(getPositionPage() + 1);
        String url = categoryID == 0 ? (UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + LIMIT_PRODUCT + "/" + getPositionPage()) : ((UrlManager.GET_PRODUCTS_BY_CATEGORY_URL + "/" + LIMIT_PRODUCT + "/" + getPositionPage()) + "/" + categoryID);
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_PRODUCTS_BY_CATEGORY, url, getParamBuidler()));//For item All
    }

    Uri.Builder getParamBuidler() {
        Uri.Builder builder = new Uri.Builder();
        return builder;
    }




    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.GET_CATEGORY_LIST:
                GsonCategory mGsonCategory = getGson().fromJson(s, GsonCategory.class);
                categoryAll = new GsonCategory.SuccessBean.DataBean();
                categoryAll.setId(0);
                categoryAll.setName("All");
                arrCategoryJson = mGsonCategory.getSuccess().getData();
                arrCategory = new ArrayList<GsonCategory.SuccessBean.DataBean>();
                arrCategory.add(categoryAll);
                for (int i = 0; i < arrCategoryJson.size(); i++) {
                    arrCategory.add(arrCategoryJson.get(i));
                }
                mISelectServiceView.setCategoryAdapter(new CategoryAdapter(arrCategory, mContext));
//                adapterCategory = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, paths);
//                adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                mISelectServiceView.setCategoryAdapter(adapterCategory);
                break;
            case KeyManager.GET_PRODUCTS_BY_CATEGORY:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonProductsByCategory mGsonProductsByCategory = getGson().fromJson(s, GsonProductsByCategory.class);
                    if (getPositionPage() == 1) {
                        arrProduct.clear();
                        arrProduct.addAll(mGsonProductsByCategory.getSuccess().getData());
                        adapter = new SelectServiceAdapter(mContext, arrProduct, mISelectServiceView.getArrayChecked());
                        mISelectServiceView.setProductsByCategoryAdapter(adapter);
                    } else {
                        arrProduct.addAll(mGsonProductsByCategory.getSuccess().getData());
                        adapter.notifyDataSetChanged();
                        mISelectServiceView.disibleProgressbar();
                    }
                } catch (Exception e) {
                    Log.d(KeyManager.VinhCNLog, e.toString());
                    if (getPositionPage()==1){
                        List<GsonProductsByCategory.SuccessBean.DataBean> arrProduct = new ArrayList<>();
                        mISelectServiceView.setProductsByCategoryAdapter(new SelectServiceAdapter(mContext, arrProduct, mISelectServiceView.getArrayChecked()));
                    }
                }
                mISelectServiceView.dismissProgress();
                break;
            default:
                break;
        }


    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
