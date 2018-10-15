package com.example.admin.shopnail.Presenter.ViewProductPresenter;

import com.example.admin.shopnail.Model.ServicesOfShop;

import java.util.List;

public interface IViewProductPresenter {
    List<ServicesOfShop> getListProduct();
    void showProductChoosed(String stringExtra);
}
