package com.example.admin.shopnail.Model.CustomerInfo;

public class Customer implements ICustomer {
    public String name;
    public String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
