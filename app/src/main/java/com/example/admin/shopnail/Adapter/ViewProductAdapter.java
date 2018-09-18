package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class ViewProductAdapter extends BaseAdapter {

    List<ServicesOfShop> listService;
    LayoutInflater layoutInflater;

    public ViewProductAdapter(Context context, List<ServicesOfShop> listService) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewProduct holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.product_item_layout, null);
            holder = new ViewProduct();
            holder.nameService = (TextView) view.findViewById(R.id.txtProductName);
            holder.priceService = (TextView) view.findViewById(R.id.txtProductPrice);
            holder.btnRemove = (Button) view.findViewById(R.id.btn_remove) ;
            view.setTag(holder);
        } else {
            holder = (ViewProduct) view.getTag();
        }

        ServicesOfShop itemService = this.listService.get(i);
        holder.nameService.setText(itemService.nameService);
        holder.priceService.setText(itemService.priceService+" $");

        return view;

    }

    class ViewProduct {
        TextView nameService;
        TextView priceService;
        Button btnRemove;
    }
}
