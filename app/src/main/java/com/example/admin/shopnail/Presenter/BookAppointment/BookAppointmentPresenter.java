package com.example.admin.shopnail.Presenter.BookAppointment;

import com.example.admin.shopnail.View.BookAppointment.IBookAppointmentView;

import java.util.ArrayList;

public class BookAppointmentPresenter implements IBookAppointmentPresenter {
    private IBookAppointmentView mBookAppointmentView;

    public BookAppointmentPresenter(IBookAppointmentView bookAppointmentView) {
        this.mBookAppointmentView = bookAppointmentView;
    }

    @Override
    public void reqGetStaffList() {
        //Send request to server: get Staff List for select at Book Appointment screen

        //Stub
        ArrayList<String> arrayOfStaff = new ArrayList<String>();
        arrayOfStaff.add("Tom");
        arrayOfStaff.add("Jerry");
        arrayOfStaff.add("Tom1");
        arrayOfStaff.add("Tom2");
        arrayOfStaff.add("Tom3");
        mBookAppointmentView.updateStaffList(arrayOfStaff);
    }

    @Override
    public void reqGetServiceList() {
        //Send request to Server
        //Stub
        ArrayList<String> arrayOfService = new ArrayList<String>();
        arrayOfService.add("Hair1");
        arrayOfService.add("Hair2");
        arrayOfService.add("Hair3");
        arrayOfService.add("Hair4");
        arrayOfService.add("Hair5");
        mBookAppointmentView.updateServiceList(arrayOfService);
    }


}
