package com.example.admin.shopnail.View.CustomerServiceHistory;

import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Model.ServicesOfShop;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerServiceHistoryView {

    public void updateListCustomerServiceHistoryByDate (ArrayList<Customer> listCustomerServiceHistory);

    public void showListCustomerServiceHistoryByID (ArrayList<ServiceHistory> listCustomerServiceHistoryByID);

    void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient);
}
