package com.example.admin.shopnail.Presenter.SelectServicePresenter;

import com.example.admin.shopnail.Model.ServicesOfShop;

import java.util.List;

public interface ISelectServicePresenter {
    List<ServicesOfShop> getListDataAcrylic();
    List<ServicesOfShop> getListNaturalNails();
    List<ServicesOfShop> getListWaxingFacial();

    void RequestCategory();

    void requestProduct(int i);

    void startScroll();
}
