package com.example.admin.shopnail.Presenter.CustomerServiceHistory;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.View.CustomerServiceHistory.ICustomerServiceHistoryView;

import java.util.ArrayList;
import java.util.Date;

public class CustomerServiceHistoryPresenter implements ICustomerServiceHistoryPresenter {

    private ICustomerServiceHistoryView mCustomerServiceHistoryView;
    public CustomerServiceHistoryPresenter(ICustomerServiceHistoryView customerServiceHistoryView) {
        this.mCustomerServiceHistoryView = customerServiceHistoryView;
    }
    @Override
    public void loadCustomerServiceHistoryByDate(Date selected) {
        ArrayList<Customer> arrayOfUsers = new ArrayList<Customer>();
        arrayOfUsers.add(new Customer("KhoaNguyen", "0973603509"));
        arrayOfUsers.add(new Customer("Peter", "0123456789"));
        arrayOfUsers.add(new Customer("Tom", "0123456789"));
        arrayOfUsers.add(new Customer("Jerry", "0123456789"));
        mCustomerServiceHistoryView.updateListCustomerServiceHistoryByDate(arrayOfUsers);
    }

    @Override
    public void loadCustomerServiceHistoryByID(String name, String phone) {
        ArrayList<ServiceHistory> arrayOfServiceHistories = new ArrayList<ServiceHistory>();
        arrayOfServiceHistories.add(new ServiceHistory("Hair", 50, true, 30));
        arrayOfServiceHistories.add(new ServiceHistory("Nail clipper", 20, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Nail polish", 30, true, 10));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, true, 20));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, true, 20));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        mCustomerServiceHistoryView.showListCustomerServiceHistoryByID(arrayOfServiceHistories);

    }
}
