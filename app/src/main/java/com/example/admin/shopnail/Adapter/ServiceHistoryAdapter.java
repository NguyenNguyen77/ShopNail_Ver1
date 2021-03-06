package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
        TextView tvServiceExtra = (TextView) convertView.findViewById(R.id.tv_service_extra);
        TextView tvServicePriceExtra = (TextView) convertView.findViewById(R.id.tv_service_price_extra);
        LinearLayout lvItem = (LinearLayout) convertView.findViewById(R.id.item_list);

        tvServiceName.setText(user.mServiceName);
        tvServicePrice.setText(Float.toString(user.mServicePrice));
        if(user.isExtra) {
            tvServiceExtra.setVisibility(View.VISIBLE);
            tvServicePriceExtra.setVisibility(View.VISIBLE);
            tvServicePriceExtra.setText(Float.toString(user.mServicePriceExtra));
        }

        if (position % 2 == 1) {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_1);
            lvItem.setBackgroundColor(backgroundColor);
        } else {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_2);
            lvItem.setBackgroundColor(backgroundColor);
        }
        return convertView;
    }

}
