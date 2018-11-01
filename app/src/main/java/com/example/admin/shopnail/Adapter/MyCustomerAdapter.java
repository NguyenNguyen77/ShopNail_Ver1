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

import com.example.admin.shopnail.CustomViewListExpand.NonScrollListView;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerActivity;

import java.util.List;

public class MyCustomerAdapter extends BaseAdapter implements View.OnClickListener {

    List<GsonProductCustomer.SuccessBean.ProductsBean> object;
    LayoutInflater layoutInflater;
    Context mContext;

    public MyCustomerAdapter(Context context, List<GsonProductCustomer.SuccessBean.ProductsBean> objects) {
        layoutInflater = LayoutInflater.from(context);
        this.object = objects;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int position) {
        return object.get(position);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_my_customer_detail_service, null);
            holder.tvExtra = convertView.findViewById(R.id.txt_extra);
            holder.lv = convertView.findViewById(R.id.list_products);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GsonProductCustomer.SuccessBean.ProductsBean mProductsBean = object.get(i);
        holder.tvExtra.setText(String.valueOf(mProductsBean.getExtraMoney()) + "$" + " ( Click to update extra for this order)");
        holder.tvExtra.setTag(i);
        holder.tvExtra.setOnClickListener(this);
        List<GsonProductCustomer.SuccessBean.ProductsBean.ProductBean> arrProduct = object.get(i).getProduct();
        ProductsCustomerAdapter adapter = new ProductsCustomerAdapter(arrProduct, mContext, mProductsBean.getOrderId(), mProductsBean.getExtraMoney());
        holder.lv.setAdapter(adapter);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()){
            case R.id.txt_extra:
                ((MyDetailCustomerActivity) mContext).OpenDialogUpdate(object.get(position).getOrderId());
                break;
        }
    }

    class ViewHolder {
        TextView tvExtra;
        NonScrollListView lv;
    }
}
