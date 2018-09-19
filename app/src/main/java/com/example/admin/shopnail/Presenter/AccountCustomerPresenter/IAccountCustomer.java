package com.example.admin.shopnail.Presenter.AccountCustomerPresenter;

public interface IAccountCustomer {
    void createAccountForCustomer(String nameCustomer,String phoneCustomer);

    boolean checkLoginForCustomer(String phoneCustomer);

    void sendRequestLoginForCustomer(String phoneCustomer);
}
