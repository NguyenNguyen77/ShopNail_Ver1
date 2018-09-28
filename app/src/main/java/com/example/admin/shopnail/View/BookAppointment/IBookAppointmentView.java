package com.example.admin.shopnail.View.BookAppointment;

import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.Employee;

import java.util.ArrayList;

public interface IBookAppointmentView {

    public void updateStaffList (ArrayList<String> staffList);
    public void updateServiceList (ArrayList<String> serviceList);
    public void onReqCallback (boolean result);
}
