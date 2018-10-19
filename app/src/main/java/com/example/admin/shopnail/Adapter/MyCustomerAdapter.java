package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class MyCustomerAdapter extends BaseAdapter {

    List<ServicesOfShop> listService;
    LayoutInflater layoutInflater;
    TextView txt_name_service;
    TextView txt_price_service;
    TextView txt_date;

    public MyCustomerAdapter(Context context,List<ServicesOfShop> objects) {
        layoutInflater = LayoutInflater.from(context);
        listService= objects;
    }

    //    public MyCustomerAdapter(Context context, List<ServicesOfShop> listService) {
////        this.context = context;
//        this.listService = listService;
//        layoutInflater = LayoutInflater.from(context);
//    }
    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public Object getItem(int position) {
        return listService.get(position);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_my_customer_detail_service, null);
        } else {
            holder = (MyCustomerAdapter.ViewHolder) convertView.getTag();
        }
            holder = new MyCustomerAdapter.ViewHolder();
            holder.nameService = convertView.findViewById(R.id.txt_name_service);
            holder.priceService = convertView.findViewById(R.id.txt_price_service);
            LinearLayout lvItem = (LinearLayout) convertView.findViewById(R.id.item_list_my_customer);
//            txt_date = convertView.findViewById(R.id.txt_time_service);

            convertView.setTag(holder);


        holder.nameService.setText(listService.get(i).nameService);
//        holder.priceService.setText((int) listService.get(i).priceService);

        if (i % 2 == 1) {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_1);
            lvItem.setBackgroundColor(backgroundColor);
        } else {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_2);
            lvItem.setBackgroundColor(backgroundColor);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView srcIconService;
        TextView nameService;
        TextView priceService;
        CheckBox cbItems;
    }
}
