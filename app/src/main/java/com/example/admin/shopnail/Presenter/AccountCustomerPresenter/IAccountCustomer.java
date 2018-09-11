package com.example.admin.shopnail.Presenter.AccountCustomerPresenter;

public interface IAccountCustomer {
    void createAccountForCustomer(String nameCustomer,int phoneCustomer);

    boolean checkLoginForCustomer(int phoneCustomer);

    void sendRequestLoginForCustomer(int phoneCustomer);
}
