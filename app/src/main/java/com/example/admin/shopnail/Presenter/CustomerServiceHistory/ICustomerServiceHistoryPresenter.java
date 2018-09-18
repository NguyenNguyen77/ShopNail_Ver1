package com.example.admin.shopnail.Presenter.CustomerServiceHistory;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;

import java.util.ArrayList;
import java.util.Date;

public interface ICustomerServiceHistoryPresenter {

    public void loadCustomerServiceHistoryByDate(Date selected);
    public void loadCustomerServiceHistoryByID (String name, String phone);
}
