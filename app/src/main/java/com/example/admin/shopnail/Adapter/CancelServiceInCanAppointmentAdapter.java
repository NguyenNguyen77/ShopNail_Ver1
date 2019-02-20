package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.CancelAppointmentOnline.CancelAppointmentOnlineActivity;

import java.util.List;

public class CancelServiceInCanAppointmentAdapter extends BaseAdapter {

    List<GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean> objects;
    LayoutInflater layoutInflater;
    Context context;
    BaseMethod method = new BaseMethod();

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
            holder.imgTrash = convertView.findViewById(R.id.img_trash);
            holder.tvPrice = convertView.findViewById(R.id.price);
            holder.tvTime = convertView.findViewById(R.id.date);
            holder.lnItem = convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(objects.get(i).getName());
//        holder.tvPrice.setText("Price: " + objects.get(i).getPrice() + " $");
        holder.tvPrice.setText(Html.fromHtml("Price: " +"<font color=#227b12>"+ objects.get(i).getPrice() + " $"+ "</font>"));
        holder.tvTime.setText(Html.fromHtml("Order Time: " +"<font color=#227b12>"+ method.cover24To12(objects.get(i).getTimeOrder())+ "</font>"));
        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CancelAppointmentOnlineActivity) context ).showDialogConfirmCancel(objects.get(i));
            }
        });

        holder.lnItem.setBackgroundColor(isColorStrong(i) ?
                ContextCompat.getColor(convertView.getContext(), R.color.list_1)
                : ContextCompat.getColor(convertView.getContext(), R.color.list_2));

        return convertView;
    }

    private  boolean isColorStrong(int position){
        return position % 2 == 1 ? true : false;
    }


    class ViewHolder {
        TextView tvName;
        TextView tvPrice;
        TextView tvTime;
        ImageView imgTrash;
        LinearLayout lnItem;

    }
}