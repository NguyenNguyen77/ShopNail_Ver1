package com.example.admin.shopnail.Model.MyCustomer;

public class TimeSelect {
    String OrderID;
    String TimeName;

    public TimeSelect(String orderID, String timeName) {
        OrderID = orderID;
        TimeName = timeName;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getTimeName() {
        return TimeName;
    }

    public void setTimeName(String timeName) {
        TimeName = timeName;
    }
}
