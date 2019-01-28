package com.example.admin.shopnail.Presenter.BookAppointment;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;

public interface IBookAppointmentPresenter {
    public void reqGetStaffList ();
    public void reqGetServiceList ();
    public void reqBookOnline (String fullName, String email, String phone, String date, BookServiceAdapter serviceAdapter);
    public void checkTimeBookOnline (String staffName, int selectStaff, String date, String timeorder, int productId);
    public void getConfigTimeBookOnline();
}
