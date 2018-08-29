package com.example.admin.shopnail.Presenter;

public interface ILoginPresenter {
    void onLogin(int idEmployee, String passWord);

    boolean checkLogin(String idEmployee, String passWord);

    void sendRequestLogin(String userName, String passWord);
}
