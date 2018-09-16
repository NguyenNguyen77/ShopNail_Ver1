package com.example.admin.shopnail.Presenter.SelectServicePresenter;

import com.example.admin.shopnail.Model.ServicesOfShop;

import java.util.ArrayList;
import java.util.List;

public class SelectServicePresenter implements ISelectServicePresenter {
    @Override
    public List<ServicesOfShop> getListDataAcrylic() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("Manicure", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("Gel Manicure", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("Gel Manicure w/ French Tip", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("Spa Pedicure (Sea Salt & Hot Towel)", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("Spa Pedicure w/ Gel Polish (Sea Salt & Hot Towel)", 10, "http//...");

        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }

    @Override
    public List<ServicesOfShop> getListNaturalNails() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("ddddddddddddd", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("eeeeeeeeeeeeee", 10, "http//...");

        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }

    @Override
    public List<ServicesOfShop> getListWaxingFacial() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("xxxxxxxxxxxxxxx", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("yyyyyyyyyy", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("zzzzzzzzzzzzz", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("ooooooooooooooooooo", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("llllllllllllllll", 10, "http//...");

        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }
}
