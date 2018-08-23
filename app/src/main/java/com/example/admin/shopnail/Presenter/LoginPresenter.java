package com.example.admin.shopnail.Presenter;

import com.example.admin.shopnail.Model.Employee;
import com.example.admin.shopnail.View.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkLogin(int idEmployee, String passWord){
        if(idEmployee!=0 && passWord!=null){
            loginView.onLoginResult("login success");
        }else {
            loginView.onLoginResult("Can't login");
        }
    }
    @Override
    public void onLogin(int idEmployee, String passWord) {

    }
}
