package com.example.admin.shopnail.View.MyDetailCustomer;

import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonDetailCustomer;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;

import java.util.List;

public interface MyDetailCustomerView {
    String getOrderID();

    String getTimeName();

    void setListProducts(List<GsonDetailCustomer.SuccessBean.CustomersBean.OrdersBean> listProduct);

    ViewManager getViewManager();

    void closeProgress();

    void OpenDialogUpdate(String orderId);

    void setInfor(GsonDetailCustomer.SuccessBean.CustomersBean customersBean);

    int getClientID();
}
