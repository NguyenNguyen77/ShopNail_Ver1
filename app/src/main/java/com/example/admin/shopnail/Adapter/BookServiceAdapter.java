package com.example.admin.shopnail.Adapter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.shopnail.Model.BookAppointment.BookService;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.View.BookAppointment.BookAppointmentActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class BookServiceAdapter extends ArrayAdapter<BookService> implements View.OnClickListener {
    ArrayAdapter<String> adapterCategoryStaff = null;
    ArrayAdapter<String> adapterCategoryService = null;
    ArrayList<BookService> mListusers = null;
    BookAppointmentActivity mBookAppointmentActivity;
    //TextView mServiceTime;
    private Context mContext = null;
    private LayoutInflater inflater;
    private int mPosition = 0;
    public int mHour, mMinute;
    private View mView;
    protected ViewManager mViewManager = ViewManager.getInstance();

    public BookServiceAdapter(Context context, ArrayList<BookService> users, BookAppointmentActivity view) {
        super(context, 0, users);
        this.mContext = context;
        mListusers = users;
        this.mBookAppointmentActivity = view;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        this.mPosition = position;
        final BookService user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book_service, parent, false);

            holder = new ViewHolder();

            holder.spinnerStaff = (Spinner) convertView.findViewById(R.id.spinnerStaff);
            holder.spinnerService = (Spinner) convertView.findViewById(R.id.spinnerService);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.edNote = (EditText) convertView.findViewById(R.id.et_note);
            holder.imgTrash = (ImageView) convertView.findViewById(R.id.img_delete_service);
            holder.llListItem = (LinearLayout) convertView.findViewById(R.id.item_list_book);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.spinnerService.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);
                        String selectStaff = (String) item;
                        holder.spinnerService.setSelection(pos);
                        mListusers.get(position).setSelectService(pos);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog("Confirm", "Do you want to delete?", position);
            }
        });


        holder.tvTime.setText(user.getServiceTime());
        holder.tvTime.setId(position);
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(holder, v);
            }
        });


        holder.edNote.setText(user.getNote());
        holder.edNote.setId(position);

        holder.edNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    if (position < mListusers.size()) {
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        mListusers.get(position).setNote(Caption.getText().toString());
                    }
                }
            }
        });

        adapterCategoryStaff = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListStaff);
        adapterCategoryStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerStaff.setAdapter(adapterCategoryStaff);
        if (user.getSelectStaff() <= adapterCategoryStaff.getCount()) {
            holder.spinnerStaff.setSelection(user.getSelectStaff());
        }

        adapterCategoryService = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListService);
        adapterCategoryService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerService.setAdapter(adapterCategoryService);

        if (user.getSelectService() <= adapterCategoryService.getCount()) {
            holder.spinnerService.setSelection(user.getSelectService());
        }

        showUnderLineText(user.mServiceTime.toString(), holder.tvTime);

        holder.edNote.setText(user.mNote);

        //Set color for list view
        if (position % 2 == 1) {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_1);
            holder.llListItem.setBackgroundColor(backgroundColor);
        } else {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_2);
            holder.llListItem.setBackgroundColor(backgroundColor);
        }

        mView = convertView;

        holder.imgTrash.setVisibility(View.INVISIBLE);
        if (mListusers.size() > 1) {
            holder.imgTrash.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout llListItem;

        Spinner spinnerStaff;
        Spinner spinnerService;
        TextView tvTime;
        EditText edNote;
        ImageView imgTrash;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    private void showTimePickerDialog(final ViewHolder holder, View v) {
        String strArrtmp[] = holder.tvTime.getText().toString().split(":");
        mHour = Integer.parseInt(strArrtmp[0]);
        mMinute = Integer.parseInt(strArrtmp[1]);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(mView.getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        showUnderLineText(hourOfDay + ":" + minute, holder.tvTime);
                        final TextView Caption = (TextView) holder.tvTime;
                        mListusers.get(mPosition).setServiceTime(Caption.getText().toString());
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void showUnderLineText(String text, TextView id) {
        SpannableString contentSpanned = new SpannableString(text);
        contentSpanned.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        id.setText(contentSpanned);
    }

    public void showConfirmDialog(final String title, String content, final int pos) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(mContext, R.style.Theme_AlertDialog);
        final Dialog commonDialog = new Dialog(ctw);
        commonDialog.setContentView(R.layout.confirm_dialog);
        commonDialog.setTitle(title);

        TextView tvContent = (TextView) commonDialog.findViewById(R.id.tv_dialog_content);
        tvContent.setText(content);

        Button btnOK = (Button) commonDialog.findViewById(R.id.btn_ok);
        btnOK.setVisibility(View.VISIBLE);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog.dismiss();
                if (mPosition < mListusers.size()) {
                    mListusers.remove(pos);
                    BookServiceAdapter.this.notifyDataSetChanged();
                }
            }
        });
        Button btnCancel = (Button) commonDialog.findViewById(R.id.btn_cancel);
        btnCancel.setVisibility(View.VISIBLE);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }
}
