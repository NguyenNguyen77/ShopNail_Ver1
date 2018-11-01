package com.example.admin.shopnail.Presenter.MyDetailCustom;

public interface IMyDetailCustomer {
    void requestCustomerProducts(String s);

    void updateServiceRequest();

    void cancelService();

    boolean isCheckedSomething();

    void reuquestUpdate(String orderId, String s);
}
