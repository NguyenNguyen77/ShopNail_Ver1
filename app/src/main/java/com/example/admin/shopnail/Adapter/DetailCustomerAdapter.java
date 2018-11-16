package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.shopnail.CustomViewListExpand.NonScrollListView;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonDetailCustomer;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerActivity;

import java.util.List;

public class DetailCustomerAdapter extends BaseAdapter implements View.OnClickListener {

    List<GsonDetailCustomer.SuccessBean.CustomersBean.OrdersBean> object;
    LayoutInflater layoutInflater;
    Context mContext;

    public DetailCustomerAdapter(Context context, List<GsonDetailCustomer.SuccessBean.CustomersBean.OrdersBean> objects) {
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
            convertView = layoutInflater.inflate(R.layout.item_my_customer_detail, null);
            holder.tvExtra = convertView.findViewById(R.id.txt_extra);
            holder.lv = convertView.findViewById(R.id.list_products);
            holder.tvNote = convertView.findViewById(R.id.txt_note);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GsonDetailCustomer.SuccessBean.CustomersBean.OrdersBean ordersBean = object.get(i);
        holder.tvExtra.setText(String.valueOf(ordersBean.getOrderExtra()) + "$" + " ( Click to update extra for this order)");
        holder.tvExtra.setTag(i);
        holder.tvExtra.setOnClickListener(this);
        holder.tvNote.setText(object.get(i).getOrderComment());
        // TODO: 11/16/2018 here
        List<GsonDetailCustomer.SuccessBean.CustomersBean.OrdersBean.OrderProductsBean> arrProduct = object.get(i).getOrderProducts();
        DetailProductAdapter adapter = new DetailProductAdapter(arrProduct, mContext);
        holder.lv.setAdapter(adapter);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.txt_extra:
                ((MyDetailCustomerActivity) mContext).OpenDialogUpdate(object.get(position).getOrderId());
                break;
        }
    }

    class ViewHolder {
        TextView tvExtra;
        TextView tvNote;
        NonScrollListView lv;
    }
}
