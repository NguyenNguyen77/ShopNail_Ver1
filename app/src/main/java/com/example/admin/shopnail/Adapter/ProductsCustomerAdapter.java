package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.R;

import java.util.List;

public class ProductsCustomerAdapter extends BaseAdapter {
    List<GsonProductCustomer.SuccessBean.ProductsBean.ProductBean> object;
    LayoutInflater layoutInflater;
    String OrderID;
    String Price;

    public ProductsCustomerAdapter(List<GsonProductCustomer.SuccessBean.ProductsBean.ProductBean> object, Context context, String orderid, String price) {
        this.object = object;
        layoutInflater = LayoutInflater.from(context);
        this.OrderID = orderid;
        this.Price = price;
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
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_product_customer, null);
            holder.nameService = convertView.findViewById(R.id.txt_name_service);
            holder.priceService = convertView.findViewById(R.id.txt_price_service);
            holder.lnItem =  convertView.findViewById(R.id.item_list_my_customer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GsonProductCustomer.SuccessBean.ProductsBean.ProductBean productBean = object.get(position);
        holder.lnItem.setBackgroundColor(colorItem(position, convertView));
        holder.nameService.setText(productBean.getProductName());
        holder.priceService.setText(productBean.getProductPrice() + "$");
        return convertView;
    }

    private int colorItem(int position, View convertView) {
        return position % 2 == 1 ? ContextCompat.getColor(convertView.getContext(), R.color.list_1) :
                ContextCompat.getColor(convertView.getContext(), R.color.list_2);
    }

    class ViewHolder {
        ImageView srcIconService;
        TextView nameService;
        TextView priceService;
        CheckBox cbItems;
        LinearLayout lnItem;

    }
}
