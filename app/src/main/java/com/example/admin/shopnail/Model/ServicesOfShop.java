package com.example.admin.shopnail.Model;

public class ServicesOfShop {
    public String nameService;
    public float priceService;
    public String srcIconService;

    public ServicesOfShop(String nameService, float priceService, String srcIconService) {
        this.nameService = nameService;
        this.priceService = priceService;
        this.srcIconService = srcIconService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public float getPriceService() {
        return priceService;
    }

    public void setPriceService(float priceService) {
        this.priceService = priceService;
    }

    public String getSrcIconService() {
        return srcIconService;
    }

    public void setSrcIconService(String srcIconService) {
        this.srcIconService = srcIconService;
    }
}
