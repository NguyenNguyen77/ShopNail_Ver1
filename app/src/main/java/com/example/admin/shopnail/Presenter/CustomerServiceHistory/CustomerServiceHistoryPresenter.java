package com.example.admin.shopnail.Presenter.CustomerServiceHistory;

import android.os.Handler;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.View.CustomerServiceHistory.ICustomerServiceHistoryView;

import java.util.ArrayList;
import java.util.Date;

public class CustomerServiceHistoryPresenter implements ICustomerServiceHistoryPresenter {

    private ArrayList<Customer> mArrayOfUsers;

    private ICustomerServiceHistoryView mCustomerServiceHistoryView;
    public CustomerServiceHistoryPresenter(ICustomerServiceHistoryView customerServiceHistoryView) {
        this.mCustomerServiceHistoryView = customerServiceHistoryView;
    }

    @Override
    public void loadCustomerServiceHistoryByDate(Date selected) {
        mArrayOfUsers = new ArrayList<Customer>();
        mArrayOfUsers.add(new Customer("KhoaNguyen", "0973603509"));
        mArrayOfUsers.add(new Customer("Peter", "0123456789"));
        mArrayOfUsers.add(new Customer("Tom", "0123456789"));
        mArrayOfUsers.add(new Customer("Jerry", "0123456789"));
        // For test only: show inprogress dialog
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                mCustomerServiceHistoryView.updateListCustomerServiceHistoryByDate(mArrayOfUsers);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
        //For test only
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
