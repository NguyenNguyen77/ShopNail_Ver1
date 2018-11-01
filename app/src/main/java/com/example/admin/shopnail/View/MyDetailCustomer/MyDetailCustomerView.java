package com.example.admin.shopnail.View.MyDetailCustomer;

import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;

import java.util.List;

public interface MyDetailCustomerView {
    String getOrderID();

    String getTimeName();

    void setListProducts(List<GsonProductCustomer.SuccessBean.ProductsBean> listProduct);

    ViewManager getViewManager();

    void closeProgress();

    void OpenDialogUpdate(String orderId);
}
