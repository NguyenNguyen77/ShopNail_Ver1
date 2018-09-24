package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.shopnail.Model.ManageStaff.ManageStaff;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;

import java.util.List;

public class ManageStaffAdapter extends BaseAdapter {

    Context context;
    List<ManageStaff> listManage;
    LayoutInflater layoutInflater;

    public ManageStaffAdapter(Context context, List<ManageStaff> listManage) {
        this.context = context;
        this.listManage = listManage;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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

        return convertView;
    }

    class ViewHolder {
        Spinner txtService;
        CheckBox checkService;
        CheckBox checkWax;
        CheckBox checkBonus;
    }
}
