package com.example.admin.shopnail.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonProductsByCategory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class SelectServiceAdapter extends BaseAdapter implements View.OnClickListener {

    Context mContext;
    List<GsonProductsByCategory.SuccessBean.DataBean> listService;
    LayoutInflater layoutInflater;
    BaseMethod method = new BaseMethod();
    JSONArray mJSONArray;


    public SelectServiceAdapter(Context context, List<GsonProductsByCategory.SuccessBean.DataBean> listService, JSONArray mArray) {
        this.mContext = context;
        this.listService = listService;
        layoutInflater = LayoutInflater.from(context);
        this.mJSONArray = mArray;
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int position) {
        return listService.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        String isNew;
        String isHot;
        final ViewHolder mHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.service_item_layout, null);
            mHolder = new ViewHolder();
            mHolder.nameService = (TextView) convertView.findViewById(R.id.txtServiceName);
            mHolder.priceService = (TextView) convertView.findViewById(R.id.txtServicePrice);
            mHolder.imgNew = (ImageView) convertView.findViewById(R.id.image_new);
            mHolder.imgHot = (ImageView) convertView.findViewById(R.id.image_hot);
            mHolder.cbItems = convertView.findViewById(R.id.itemCheckBox);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        final GsonProductsByCategory.SuccessBean.DataBean itemService = this.listService.get(position);
        mHolder.nameService.setText(itemService.getName());
        mHolder.nameService.setTag(position);
        mHolder.priceService.setText(itemService.getPrice() + " $");
        mHolder.priceService.setTag(position);
        if (itemService.getIs_new()!=null)
        mHolder.imgNew.setVisibility(itemService.getIs_new().equals("1") ? View.VISIBLE : View.GONE);
        if (itemService.getIs_hot()!=null)
        mHolder.imgHot.setVisibility(itemService.getIs_hot().equals("1") ? View.VISIBLE : View.GONE);
        mHolder.cbItems.setTag(position);
        mHolder.cbItems.setChecked(false);
        beforeCheck(mJSONArray, mHolder.cbItems, itemService);
        mHolder.cbItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = mHolder.cbItems.isChecked();
                ((SelectServiceActivity) mContext).addJsonArrayService(isChecked,  itemService.getName() , itemService.getId(), itemService.getPrice(), method.mSimpleDateFormat.format(Calendar.getInstance().getTime()));
            }
        });

//        Picasso.get().load(itemService.getImage()).into(holder.srcIconService);
        return convertView;
    }

    private void beforeCheck(JSONArray mJSONArray, CheckBox cbItems, GsonProductsByCategory.SuccessBean.DataBean itemService) {
        boolean isBeforeChecked = false;
        if (mJSONArray.length() != 0) {
            for (int i = 0; i < mJSONArray.length(); i++) {
                try {
                    JSONObject jsonObject = mJSONArray.getJSONObject(i);
                    int id = jsonObject.getInt(KeyManager.PRODUC_ID);
                    if (itemService.getId() == id) {
                        isBeforeChecked = true;
                        break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (isBeforeChecked) {
            cbItems.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
//        int position = (int) v.getTag();
//        switch (v.getId()) {
//        }
    }

    class ViewHolder {
        ImageView imgNew;
        ImageView imgHot;
        TextView nameService;
        TextView priceService;
        CheckBox cbItems;
    }
}
