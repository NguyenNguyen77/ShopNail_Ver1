package com.example.admin.shopnail.Model;

public class ServiceHistory implements IServiceHistory {

    public String mServiceName;
    public float mServicePrice;

    public ServiceHistory(String serviceName, float servicePrice) {
        this.mServiceName = serviceName;
        this.mServicePrice = servicePrice;
    }
}
