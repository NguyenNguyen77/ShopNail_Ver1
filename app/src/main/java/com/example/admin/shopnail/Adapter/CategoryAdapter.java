package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.shopnail.Model.SelectCustomerService.GsonCategory;
import com.example.admin.shopnail.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    List<GsonCategory.SuccessBean.DataBean> object;
    Context context;

    public CategoryAdapter(List<GsonCategory.SuccessBean.DataBean> object, Context context) {
        this.object = object;
        this.context = context;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_category_list, null);
        TextView tvCategoryName =  convertView.findViewById(R.id.category_name);
        tvCategoryName.setText((object.get(position).getName()));
        return convertView;
    }
}
