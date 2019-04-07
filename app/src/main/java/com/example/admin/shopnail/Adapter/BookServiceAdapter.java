package com.example.admin.shopnail.Adapter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.admin.shopnail.Model.BookAppointment.BookService;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.View.BookAppointment.BookAppointmentActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookServiceAdapter extends ArrayAdapter<BookService> implements View.OnClickListener {
    ArrayAdapter<String> adapterCategoryStaff = null;
    ArrayAdapter<String> adapterCategoryService = null;
    ArrayList<BookService> mListusers = null;
    BookAppointmentActivity mBookAppointmentActivity;
    //TextView mServiceTime;
    private Context mContext = null;
    private LayoutInflater inflater;
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
        final BookService user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book_service, parent, false);
            holder = new ViewHolder();
            holder.position = position;
            holder.spinnerStaff = (Spinner) convertView.findViewById(R.id.spinnerStaff);
            holder.spinnerService = (Spinner) convertView.findViewById(R.id.spinnerService);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.edNote = (EditText) convertView.findViewById(R.id.et_note);
            holder.imgTrash = (ImageView) convertView.findViewById(R.id.img_delete_service);
            holder.llListItem = (LinearLayout) convertView.findViewById(R.id.item_list_book);
            holder.lnNote = convertView.findViewById(R.id.layout_note);
            holder.tvTitleNote = convertView.findViewById(R.id.title_note);
            holder.lnTime = convertView.findViewById(R.id.layout_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.spinnerStaff.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        holder.spinnerStaff.setSelection(pos);
                        mListusers.get(position).setSelectStaff(pos);
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        holder.spinnerService.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        holder.spinnerService.setSelection(pos);
                        mListusers.get(position).setSelectService(pos);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog("Confirm", "Do you want to delete?", holder.position);
            }
        });


        holder.tvTime.setText(user.getServiceTime() + " ");
        if (holder.tvTime.getText().equals("--:-- ")) {
            holder.tvTime.setTextColor(Color.RED);
        } else {
            holder.tvTime.setTextColor(Color.parseColor("#096B09"));
        }
        holder.tvTime.setId(position);
        holder.lnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showTimePickerDialog(holder, v);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                    holder.lnNote.setBackgroundColor(Color.TRANSPARENT);
                    holder.edNote.setTextColor(Color.BLACK);
                    holder.tvTitleNote.setTextColor(Color.BLACK);
                } else {
                    holder.edNote.setTextColor(Color.WHITE);
                    holder.lnNote.setBackgroundColor(Color.parseColor("#096B09"));
                    holder.tvTitleNote.setTextColor(Color.WHITE);

                }
            }
        });

        final ArrayList<String> mListStaff = new ArrayList<>();
        mListStaff.add("Select Staff");
        mListStaff.addAll(user.mListStaff);

        adapterCategoryStaff = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, mListStaff);
        adapterCategoryStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerStaff.setAdapter(adapterCategoryStaff);
//        if(user.mListStaff.get(0).equals("Select Staff")){
//            mListStaff.remove("Select Staff");
//        }
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
        LinearLayout lnNote;
        TextView tvTitleNote;
        LinearLayout lnTime;

        int position;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    private void showTimePickerDialog(final ViewHolder holder, View v) throws ParseException {
        if (holder.tvTime.getText().toString().replace(" ", "").equals("--:--")) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
        } else {
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            Date date = parseFormat.parse(holder.tvTime.getText().toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            mHour = cal.get(Calendar.HOUR_OF_DAY);
            mMinute = cal.get(Calendar.MINUTE);
        }
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(mView.getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        //showUnderLineText(hourOfDay + ":" + minute, holder.tvTime);
                        //final TextView Caption = (TextView) holder.tvTime;
                        //mListusers.get(mPosition).setServiceTime(Caption.getText().toString());
                        String staffName = mListusers.get(holder.position).mListStaff.get(mListusers.get(holder.position).getSelectStaff());
                        //mBookAppointmentActivity.checkTimeBookOnline(staffName, mPosition, Caption.getText().toString());
                        String strTime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);;
                        try {
                            mBookAppointmentActivity.checkTimeBookOnline(staffName, holder.position, strTime, mBookAppointmentActivity.checkInputTime(strTime, holder.position));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void showUnderLineText(String text, TextView id) {
        SpannableString contentSpanned = new SpannableString(text);
        contentSpanned.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        id.setText(contentSpanned + " ");
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
                if (pos < mListusers.size()) {
                    mListusers.remove(pos);
                    BookServiceAdapter.this.notifyDataSetChanged();
                    mBookAppointmentActivity.updateStatusButtonAddMoreServices();
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
