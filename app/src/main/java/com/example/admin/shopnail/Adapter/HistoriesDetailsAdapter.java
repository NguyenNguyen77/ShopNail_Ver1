package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.shopnail.CustomViewListExpand.NonScrollListView;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerActivity;

import org.w3c.dom.Text;

import java.util.List;

public class HistoriesDetailsAdapter extends BaseAdapter implements View.OnClickListener {

    List<GsonProductCustomer.SuccessBean.ProductsBean> object;
    LayoutInflater layoutInflater;
    Context mContext;

    public HistoriesDetailsAdapter(Context context, List<GsonProductCustomer.SuccessBean.ProductsBean> objects) {
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
            convertView = layoutInflater.inflate(R.layout.item_service_details_history, null);
            holder.tvExtra = convertView.findViewById(R.id.txt_extra);
            holder.lv = convertView.findViewById(R.id.list_products);
            holder.tvTotalPrice = convertView.findViewById(R.id.tv_total_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GsonProductCustomer.SuccessBean.ProductsBean mProductsBean = object.get(i);
        holder.tvExtra.setText(String.valueOf(mProductsBean.getExtraMoney()) + " $");
        holder.tvExtra.setTag(i);
        holder.tvExtra.setOnClickListener(this);
        List<GsonProductCustomer.SuccessBean.ProductsBean.ProductBean> arrProduct = object.get(i).getProduct();
        ProductsCustomerAdapter adapter = new ProductsCustomerAdapter(arrProduct, mContext, mProductsBean.getOrderId(), mProductsBean.getExtraMoney(), true);
        holder.lv.setAdapter(adapter);
        holder.tvTotalPrice.setText(String.valueOf(getTotal(object.get(i).getProduct())) + " $");
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.txt_extra:
//                ((MyDetailCustomerActivity) mContext).OpenDialogUpdate(object.get(position).getOrderId());
                break;
        }
    }

    public int getTotal(List<GsonProductCustomer.SuccessBean.ProductsBean.ProductBean> product) {
        int total = 0;
        for (GsonProductCustomer.SuccessBean.ProductsBean.ProductBean bean : product) {
            total = total + Integer.parseInt(bean.getProductPrice());
        }
        return total;
    }

    class ViewHolder {
        TextView tvExtra;
        NonScrollListView lv;
        TextView tvTotalPrice;
    }
}
