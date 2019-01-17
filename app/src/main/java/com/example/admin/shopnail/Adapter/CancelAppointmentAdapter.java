package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.shopnail.R;

import java.util.ArrayList;

public class CancelAppointmentAdapter extends BaseAdapter {

    public ArrayList<String> mListService;
    LayoutInflater layoutInflater;
   public CancelAppointmentAdapter(Context context, ArrayList<String> listService) {
       this.mListService = listService;
       layoutInflater = LayoutInflater.from(context);
   }

    @Override
    public int getCount() {
        return mListService.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(mListService.get(i));
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_cancel_appointment, null);
            holder = new ViewHolder();
            holder.txtService = (TextView) convertView.findViewById(R.id.txt_service);
            holder.btnCancel = (Button) convertView.findViewById(R.id.btn_cancel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtService.setText(mListService.get(i));
        return convertView;
    }

    class ViewHolder {
        TextView txtService;
        Button btnCancel;
    }
}
