package com.example.admin.shopnail.Presenter;

public interface ILoginPresenter {
    void onLogin(int idEmployee, String passWord);
    void checkLogin(int idEmployee, String passWord);
}
