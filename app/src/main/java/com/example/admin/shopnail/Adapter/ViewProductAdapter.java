package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonProductChoosed;
import com.example.admin.shopnail.Presenter.ViewProductPresenter.ViewProductPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.ViewCartActivity.ViewCartActivity;

import java.util.List;

public class ViewProductAdapter extends BaseAdapter {

    List<GsonProductChoosed> listService;
    LayoutInflater layoutInflater;
    private ViewProductPresenter mViewProductPresenter;

    public ViewProductAdapter(Context context,  List<GsonProductChoosed> listService, ViewProductPresenter persenter) {
        this.listService = listService;
        layoutInflater = LayoutInflater.from(context);
        mViewProductPresenter = persenter;
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
    public View getView(final int position, View view, ViewGroup viewGroup) {

        ViewProduct holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.product_item_layout, null);
        } else {
            holder = (ViewProduct) view.getTag();
        }
        holder = new ViewProduct();
        holder.nameService = (TextView) view.findViewById(R.id.txtProductName);
        holder.priceService = (TextView) view.findViewById(R.id.txtProductPrice);
        holder.imgTrash = (ImageView) view.findViewById(R.id.img_trash);
        LinearLayout lvItem = (LinearLayout) view.findViewById(R.id.item_list_product);
        view.setTag(holder);
        GsonProductChoosed itemService = this.listService.get(position);
        holder.nameService.setText(itemService.getName());
        holder.priceService.setText(itemService.getPrice() + "$");
        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewProductPresenter.minusTotalPrice(Integer.valueOf(listService.get(position).getPrice()));
                listService.remove(position);
                Log.d("NguyenNK2", "remove item: " + position);
                ViewProductAdapter.this.notifyDataSetChanged();
            }
        });

        if (position % 2 == 1) {
            int backgroundColor = ContextCompat.getColor(view.getContext(), R.color.list_1);
            lvItem.setBackgroundColor(backgroundColor);
        } else {
            int backgroundColor = ContextCompat.getColor(view.getContext(), R.color.list_2);
            lvItem.setBackgroundColor(backgroundColor);
        }


        return view;

    }

    class ViewProduct {
        TextView nameService;
        TextView priceService;
        ImageView imgTrash;
    }
}
