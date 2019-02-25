package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Model.ManageStaff.CheckBoxObject;
import com.example.admin.shopnail.Model.ManageStaff.GsonServiceType;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.ManageStaff.ManageStaffActivity;

import java.util.ArrayList;
import java.util.List;

public class ManageStaffAdapter extends BaseAdapter {

    Context context;
    List<CheckBoxObject> objects;
    LayoutInflater layoutInflater;
    ArrayAdapter<String> adapterCategory = null;
    List<GsonServiceType.SuccessBean.ServiceTypeBean> arrServiceType;
    //    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};
//    boolean enableSelectSpinner = false;

    public ManageStaffAdapter(Context context, List<CheckBoxObject> listManage, List<GsonServiceType.SuccessBean.ServiceTypeBean> arrservicetype) {
//        this.context = context;
        this.objects = listManage;
        this.arrServiceType = arrservicetype;
        this.context = context;
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_manage_staff, null);
            holder = new ViewHolder();
            holder.spnService = convertView.findViewById(R.id.spinner_service);
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
        holder.spnService.setAdapter(adapterCategory);
//        holder.spnService.setSelected(false);  // must
//        holder.spnService.setSelection(0,true);
        CheckBoxObject item = this.objects.get(position);
        holder.lnService.setVisibility(objects.get(position).isService() ? View.VISIBLE : View.INVISIBLE);
        holder.checkBonus.setVisibility(objects.get(position).isBonus() ? View.VISIBLE : View.INVISIBLE);
        holder.checkWax.setVisibility(objects.get(position).isWax() ? View.VISIBLE : View.INVISIBLE);
        holder.checkBonus.setChecked(objects.get(position).getValueBonus() == 1 ? true : false);
        holder.checkWax.setChecked(objects.get(position).getValueWax() == 1 ? true : false);
        int valueService = objects.get(position).getValueService();
        Log.d(KeyManager.VinhCNLog, String.valueOf(valueService));
        holder.checkService.setChecked(valueService != 0 ? true : false);
        if (valueService != 0 && valueService == 2) {
            holder.spnService.setSelection(valueService - 1);
        } else {
            holder.spnService.setSelection(0);
        }
        holder.checkService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkService.isChecked()) {
                    int positionSpn = holder.spnService.getSelectedItemPosition();
                    int idSerivce = arrServiceType.get(positionSpn).getId();
                    objects.get(position).setValueService(idSerivce);
                } else {
                    objects.get(position).setValueService(0);
                }
                ((ManageStaffActivity) context).AddOrRemoveItemsArray(holder.checkService.isChecked(), position, objects.get(position).getTypeService());
            }
        });

        holder.spnService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (holder.checkService.isChecked()) {
                    int IdServiceType = arrServiceType.get(i).getId();
                    objects.get(position).setValueService(IdServiceType);
                } else {
                    objects.get(position).setValueService(0);
                }
//                if (enableSelectSpinner) {
                    ((ManageStaffActivity) context).ChangeServiceType(holder.checkService.isChecked(), position, objects.get(position).getTypeService());
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.checkBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.get(position).setValueBonus(holder.checkBonus.isChecked() ? 1 : 0);
                ((ManageStaffActivity) context).AddOrRemoveItemsArray(holder.checkBonus.isChecked(), position, objects.get(position).getTypeBonus());
            }
        });

        holder.checkWax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.get(position).setValueWax(holder.checkWax.isChecked() ? 1 : 0);
                ((ManageStaffActivity) context).AddOrRemoveItemsArray(holder.checkBonus.isChecked(), position, objects.get(position).getTypeWax());
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

//    public void setEnableSelectSpinner(boolean isEnable) {
//        this.enableSelectSpinner = isEnable;
//    }
//
//    public boolean isEnableSelectSpinner(){
//        return enableSelectSpinner;
//    }

    class ViewHolder {
        Spinner spnService;
        CheckBox checkService;
        CheckBox checkWax;
        CheckBox checkBonus;
        LinearLayout lnService;
    }
}
