package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class SelectServiceAdapter extends BaseAdapter {

    Context context;
    List<ServicesOfShop> listService;
    LayoutInflater layoutInflater;

    public SelectServiceAdapter(Context context, List<ServicesOfShop> listService) {
//        this.context = context;
        this.listService = listService;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int position) {
        return listService.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.service_item_layout, null);
            holder = new ViewHolder();
//            holder.srcIconService = (ImageView) convertView.findViewById(R.id.img_ServiceIcon);
            holder.nameService = (TextView) convertView.findViewById(R.id.txtServiceName);
            holder.priceService = (TextView) convertView.findViewById(R.id.txtServicePrice);
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
        ImageView srcIconService;
        TextView nameService;
        TextView priceService;
    }
}
