package com.example.admin.shopnail.Presenter.ViewProductPresenter;

import com.example.admin.shopnail.Model.ServicesOfShop;

import java.util.ArrayList;
import java.util.List;

public class ViewProductPresenter implements IViewProductPresenter {

    @Override
    public List<ServicesOfShop> getListProduct() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        return listService;
    }
}
