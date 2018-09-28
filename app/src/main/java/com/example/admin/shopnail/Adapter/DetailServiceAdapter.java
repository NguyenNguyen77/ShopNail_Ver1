package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class DetailServiceAdapter extends BaseAdapter {

    List<ServicesOfShop> listService;
    LayoutInflater layoutInflater;

    public DetailServiceAdapter(Context context, List<ServicesOfShop> listService) {
//        this.context = context;
        this.listService = listService;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int i) {
        return listService.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_my_customer_detail_service, null);
            holder = new ViewHolder();
//            holder.srcIconService = (ImageView) convertView.findViewById(R.id.img_ServiceIcon);
            holder.nameService = (TextView) convertView.findViewById(R.id.txt_name_service);
            holder.priceService = (TextView) convertView.findViewById(R.id.txt_price_service);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ServicesOfShop itemService = this.listService.get(i);
        holder.nameService.setText(itemService.nameService);
        holder.priceService.setText(itemService.priceService+" $");

        return convertView;
    }

    class ViewHolder {
        TextView nameService;
        TextView priceService;
        CheckBox cbStatus;
    }
}
