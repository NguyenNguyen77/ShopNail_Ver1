package com.example.admin.shopnail.Model;

public class ServiceHistory implements IServiceHistory {

    public String mServiceName;
    public float mServicePrice;
    public float mServicePriceExtra;
    public boolean isExtra;

    public ServiceHistory(String serviceName, float servicePrice, boolean isExtra, float servicePriceExtra) {
        this.mServiceName = serviceName;
        this.mServicePrice = servicePrice;
        this.mServicePriceExtra = servicePriceExtra;
        this.isExtra = isExtra;
    }
}
