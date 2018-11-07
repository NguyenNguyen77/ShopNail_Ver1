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
import com.example.admin.shopnail.Model.ManageStaff.GsonServiceType;
import com.example.admin.shopnail.R;

import java.util.ArrayList;
import java.util.List;

public class ManageStaffAdapter extends BaseAdapter {

    Context context;
    List<CheckBoxObject> objects;
    LayoutInflater layoutInflater;
    ArrayAdapter<String> adapterCategory = null;
    List<GsonServiceType.SuccessBean.ServiceTypeBean> arrServiceType;
//    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};

    public ManageStaffAdapter(Context context, List<CheckBoxObject> listManage, List<GsonServiceType.SuccessBean.ServiceTypeBean> arrservicetype) {
//        this.context = context;
        this.objects = listManage;
        this.arrServiceType = arrservicetype;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ManageStaffAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_manage_staff, null);
            holder = new ViewHolder();
            holder.txtService = convertView.findViewById(R.id.spinner_service);
            holder.checkService = convertView.findViewById(R.id.item_service);
            holder.checkWax = convertView.findViewById(R.id.item_wax);
            holder.checkBonus = convertView.findViewById(R.id.item_bouns);
            holder.lnService = convertView.findViewById(R.id.layout_service);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        adapterCategory = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, getArrStringService(arrServiceType));
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.txtService.setAdapter(adapterCategory);
        CheckBoxObject item = this.objects.get(position);
        holder.lnService.setVisibility(objects.get(position).isService() ? View.VISIBLE : View.INVISIBLE);
        holder.checkBonus.setVisibility(objects.get(position).isBonus() ? View.VISIBLE : View.INVISIBLE);
        holder.checkWax.setVisibility(objects.get(position).isWax() ? View.VISIBLE : View.INVISIBLE);
        
        holder.checkService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.get(position).setValueService(holder.checkService.isChecked() ? 1 : 0);
            }
        });

        holder.checkBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.get(position).setValueBonus(holder.checkBonus.isChecked() ? 1 : 0);
            }
        });

        holder.checkWax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.get(position).setValueWax(holder.checkWax.isChecked() ? 1 : 0);
            }
        });
        return convertView;
    }


    private List<String> getArrStringService(List<GsonServiceType.SuccessBean.ServiceTypeBean> arr) {
        List<String> arrService = new ArrayList<>();
        for (GsonServiceType.SuccessBean.ServiceTypeBean s : arr) {
            arrService.add(s.getName());
        }
        return arrService;
    }

    class ViewHolder {
        Spinner txtService;
        CheckBox checkService;
        CheckBox checkWax;
        CheckBox checkBonus;
        LinearLayout lnService;
    }
}
