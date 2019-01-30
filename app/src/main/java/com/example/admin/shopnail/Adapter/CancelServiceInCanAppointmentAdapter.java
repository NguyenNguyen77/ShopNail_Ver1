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
import com.example.admin.shopnail.View.CancelAppointmentOnline.CancelAppointmentOnlineActivity;

import java.util.List;

public class CancelServiceInCanAppointmentAdapter extends BaseAdapter {

    List<GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean> objects;
    LayoutInflater layoutInflater;
    Context context;

    public CancelServiceInCanAppointmentAdapter(Context context, List<GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean> listService) {
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_cancel_service, null);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.service_name);
            holder.btnCancel = convertView.findViewById(R.id.btn_cancel);
            holder.tvPrice = convertView.findViewById(R.id.price);
            holder.tvTime = convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(objects.get(i).getName());
        holder.tvPrice.setText("Price: " + objects.get(i).getPrice() + " $");
        holder.tvTime.setText("Time: " + objects.get(i).getTimeOrder());
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CancelAppointmentOnlineActivity) context ).showDialogConfirmCancel(objects.get(i));
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvName;
        TextView tvPrice;
        TextView tvTime;
        Button btnCancel;

    }
}