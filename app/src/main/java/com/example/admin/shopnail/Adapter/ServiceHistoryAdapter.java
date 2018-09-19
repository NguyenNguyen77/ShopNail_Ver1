package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.R;

import java.util.ArrayList;

public class ServiceHistoryAdapter extends ArrayAdapter<ServiceHistory> {

        public ServiceHistoryAdapter(Context context, ArrayList<ServiceHistory> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ServiceHistory user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_service_history, parent, false);
            }
            TextView tvServiceName = (TextView) convertView.findViewById(R.id.tv_service_name);
            TextView tvServicePrice = (TextView) convertView.findViewById(R.id.tv_service_price);
            tvServiceName.setText(user.mServiceName);
            tvServicePrice.setText( Float.toString(user.mServicePrice));
            return convertView;
        }

}
