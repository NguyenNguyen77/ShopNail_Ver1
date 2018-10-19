package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends BaseAdapter {


    List<GsonGetClient.SuccessBean.ClientsBean> object;
    LayoutInflater layoutInflater;

    public CustomerAdapter(Context context, List<GsonGetClient.SuccessBean.ClientsBean> object) {
        layoutInflater = LayoutInflater.from(context);
        this.object = object;
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_customer_service_history, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvHome = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.lvItem = (LinearLayout) convertView.findViewById(R.id.item_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GsonGetClient.SuccessBean.ClientsBean user = object.get(position);
        holder.tvName.setText(user.getClientName());
        holder.tvHome.setText(user.getClientPhone());
        holder.lvItem.setBackgroundColor(isColorStrong(position) ?
                ContextCompat.getColor(convertView.getContext(), R.color.list_1)
        : ContextCompat.getColor(convertView.getContext(), R.color.list_2));

        return convertView;
    }

    private  boolean isColorStrong(int position){
        return position % 2 == 1 ? true : false;
    }


    class ViewHolder {
        TextView tvName;
        TextView tvHome;
        LinearLayout lvItem;
    }


}
