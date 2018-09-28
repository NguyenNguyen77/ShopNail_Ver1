package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class MyCustomerAdapter extends ArrayAdapter<ServicesOfShop> {

    List<ServicesOfShop> listService;
    LayoutInflater layoutInflater;
    TextView txt_name_service;
    TextView txt_price_service;
    TextView txt_date;

    public MyCustomerAdapter(Context context,List<ServicesOfShop> objects) {
        super(context, objects.size());
        listService= objects;
    }

    //    public MyCustomerAdapter(Context context, List<ServicesOfShop> listService) {
////        this.context = context;
//        this.listService = listService;
//        layoutInflater = LayoutInflater.from(context);
//    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_customer, parent, false);
        }

        txt_name_service = convertView.findViewById(R.id.txt_name_service);
        txt_price_service = convertView.findViewById(R.id.txt_price_service);
        txt_date = convertView.findViewById(R.id.txt_time_service);


        return convertView;
    }
}
