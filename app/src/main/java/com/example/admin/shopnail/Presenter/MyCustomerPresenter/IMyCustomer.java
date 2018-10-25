package com.example.admin.shopnail.Presenter.MyCustomerPresenter;

import com.example.admin.shopnail.Model.MyCustomer.TimeSelect;

import java.util.List;

public interface IMyCustomer {

    void requestCustomerOrder(String format);

    void tranfertoDetailCustomer(int i);

    void openDetailCustomer(int which, List<TimeSelect> arrTimeSelect);
}
