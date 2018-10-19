package com.example.admin.shopnail.View.MyCustomer;

import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;

import java.util.List;

public interface MyCustomerView {

    void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient);
}
