package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;
import com.example.admin.shopnail.R;

import java.util.ArrayList;
import java.util.List;

public class CancelAppointmentAdapter extends BaseAdapter {

    List<GsonOppointment.SuccessBean.ServiceTypeBean> objects;
    LayoutInflater layoutInflater;
    Context context;

    public CancelAppointmentAdapter(Context context, List<GsonOppointment.SuccessBean.ServiceTypeBean> listService) {
        this.objects = listService;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_cancel_appointment, null);
            holder = new ViewHolder();
            holder.tvDate = convertView.findViewById(R.id.date);
            holder.lvService = convertView.findViewById(R.id.list_service);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDate.setText(objects.get(i).getDateOrder());
        List<GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean> arrService = objects.get(i).getOrders();
        CancelServiceInCanAppointmentAdapter adapter  = new CancelServiceInCanAppointmentAdapter(context, arrService);
        holder.lvService.setAdapter(adapter);
        return convertView;
    }

    class ViewHolder {
        TextView tvDate;
        ListView lvService;
    }
}
