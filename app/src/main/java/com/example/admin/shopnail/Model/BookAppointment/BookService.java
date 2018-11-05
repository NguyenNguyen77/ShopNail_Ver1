package com.example.admin.shopnail.Model.BookAppointment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class BookService {
    public ArrayList<String> mListStaff;
    public ArrayList<String> mListService;
    public String mServiceTime;
    public String mNote;
    public int mSelectStaff = 0;
    public int mSelectService = 0;

    public BookService(ArrayList<String> listStaff, ArrayList<String> listService, String serviceTime, String note, int staffpos, int servicepos) {
        this.mListStaff = listStaff;
        this.mListService = listService;
        this.mServiceTime = serviceTime;
        this.mNote = note;
        this.mSelectStaff = staffpos;
        this.mSelectService = servicepos;
    }

    public String getNote () {
        return mNote;
    }

    public void setNote (String note) {
        mNote = note;
    }

    public String getServiceTime () {
        return mServiceTime;
    }

    public void setServiceTime (String time) {
        mServiceTime = time;
    }

    public int getSelectStaff () {
        return mSelectStaff;
    }

    public void setSelectStaff (int selectStaff) {
        mSelectStaff = selectStaff;
    }

    public int getSelectService () {
        return mSelectService;
    }

    public void setSelectService (int selectService) {
        mSelectService = selectService;
    }

    public ArrayList<String> getStaffList () {
        return mListStaff;
    }

    public void setStaffList (ArrayList<String> listStaff) {
        mListStaff = listStaff;
    }

    public ArrayList<String> getServiceList () {
        return mListService;
    }

    public void setServiceList (ArrayList<String> listService) {
        mListService = listService;
    }

}
