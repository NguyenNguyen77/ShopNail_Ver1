package com.example.admin.shopnail.View.BookAppointment;

import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.Employee;

import java.text.ParseException;
import java.util.ArrayList;

public interface IBookAppointmentView {

    public void updateStaffList (ArrayList<String> staffList);
    public void updateServiceList (ArrayList<String> serviceList);
    public void onReqCallback (boolean result);
    public void showErrorDialog (ViewManager.ERROR_CODE errorCode);
    public void checkTimeBookOnline (String staffName, int pos, String timeOrder, boolean isSendReqCheckTime);
    public void updateOrderTime () throws ParseException;
    public void updateConfigTime (String open, String close) throws ParseException;

    public boolean checkInputTime (String inputTime, int pos);
    public void onBookingOnlineResult (boolean result, String msg);
    public void updateStatusButtonAddMoreServices ();
}
