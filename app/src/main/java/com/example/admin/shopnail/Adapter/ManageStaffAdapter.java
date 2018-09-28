package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ManageStaff.ManageStaff;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;

import java.util.List;

public class ManageStaffAdapter extends BaseAdapter {

    Context context;
    List<ManageStaff> listManage;
    LayoutInflater layoutInflater;
    ArrayAdapter<String> adapterCategory = null;
    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};

    public ManageStaffAdapter(Context context, List<ManageStaff> listManage) {
//        this.context = context;
        this.listManage = listManage;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listManage.size();
    }

    @Override
    public Object getItem(int i) {
        return listManage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ManageStaffAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_manage_staff, null);
            holder = new ViewHolder();
            holder.txtService =  convertView.findViewById(R.id.spinner_service);
            holder.checkService =  convertView.findViewById(R.id.item_service);
            holder.checkWax =  convertView.findViewById(R.id.item_wax);
            holder.checkBonus =  convertView.findViewById(R.id.item_bouns);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        adapterCategory = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item,paths);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.txtService.setAdapter(adapterCategory);

        ManageStaff item = this.listManage.get(i);

        return convertView;
    }

    class ViewHolder {
        Spinner txtService;
        CheckBox checkService;
        CheckBox checkWax;
        CheckBox checkBonus;
    }
}
