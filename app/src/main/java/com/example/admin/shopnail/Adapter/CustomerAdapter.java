package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.R;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    public CustomerAdapter(Context context, ArrayList<Customer> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_customer_service_history, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tv_phone);
        LinearLayout lvItem = (LinearLayout) convertView.findViewById(R.id.item_list);
        tvName.setText(user.name);
        tvHome.setText(user.phone);

        if(position %2 == 1) {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_1);
            lvItem.setBackgroundColor(backgroundColor);
        }
        else
        {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_2);
            lvItem.setBackgroundColor(backgroundColor);
        }

        return convertView;
    }


}
