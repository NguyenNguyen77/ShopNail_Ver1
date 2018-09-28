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

    public BookService(ArrayList<String> listStaff, ArrayList<String> listService, String serviceTime, String note) {
        this.mListStaff = listStaff;
        this.mListService = listService;
        this.mServiceTime = serviceTime;
        this.mNote = note;
    }

}
