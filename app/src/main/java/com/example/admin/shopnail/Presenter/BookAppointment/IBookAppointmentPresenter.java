package com.example.admin.shopnail.Presenter.BookAppointment;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;

public interface IBookAppointmentPresenter {
    public void reqGetStaffList ();
    public void reqGetServiceList ();
    public void reqBookOnline (String fullName, String phone, String date, BookServiceAdapter serviceAdapter);
}
