package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.admin.shopnail.Model.ManageStaff.CheckBoxObject;
import com.example.admin.shopnail.R;

import java.util.List;

public class ManageStaffAdapter extends BaseAdapter {

    Context context;
    List<CheckBoxObject> objects;
    LayoutInflater layoutInflater;
    ArrayAdapter<String> adapterCategory = null;
    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};

    public ManageStaffAdapter(Context context, List<CheckBoxObject> listManage) {
//        this.context = context;
        this.objects = listManage;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ManageStaffAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_manage_staff, null);
            holder = new ViewHolder();
            holder.txtService =  convertView.findViewById(R.id.spinner_service);
            holder.checkService =  convertView.findViewById(R.id.item_service);
            holder.checkWax =  convertView.findViewById(R.id.item_wax);
            holder.checkBonus =  convertView.findViewById(R.id.item_bouns);
            holder.lnService = convertView.findViewById(R.id.layout_service);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        adapterCategory = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item,paths);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.txtService.setAdapter(adapterCategory);
        CheckBoxObject item = this.objects.get(position);
        holder.lnService.setVisibility(objects.get(position).isService() ? View.VISIBLE : View.INVISIBLE);
        holder.checkBonus.setVisibility(objects.get(position).isBonus() ? View.VISIBLE : View.INVISIBLE);
        holder.checkWax.setVisibility(objects.get(position).isWax() ? View.VISIBLE : View.INVISIBLE);
        return convertView;
    }

    class ViewHolder {
        Spinner txtService;
        CheckBox checkService;
        CheckBox checkWax;
        CheckBox checkBonus;
        LinearLayout lnService;
    }
}
