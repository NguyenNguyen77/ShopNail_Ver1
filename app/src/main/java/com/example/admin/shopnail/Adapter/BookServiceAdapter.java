package com.example.admin.shopnail.Adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.admin.shopnail.Model.BookAppointment.BookService;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.BookAppointment.BookAppointmentActivity;
import com.example.admin.shopnail.View.ViewManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookServiceAdapter extends ArrayAdapter<BookService> implements View.OnClickListener {
    ArrayAdapter<String> adapterCategoryStaff = null;
    ArrayAdapter<String> adapterCategoryService = null;
    ArrayList<BookService> mListusers = null;
    public int mHour, mMinute;
    private View mView;
    protected ViewManager mViewManager = ViewManager.getInstance();

    public BookServiceAdapter(Context context, ArrayList<BookService> users) {
        super(context, 0, users);
        mListusers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookService user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book_service, parent, false);
        }
        Spinner spinnerStaff = (Spinner) convertView.findViewById(R.id.spinnerStaff);
        Spinner spinnerService = (Spinner) convertView.findViewById(R.id.spinnerService);
        TextView serviceTime = (TextView) convertView.findViewById(R.id.tv_time);
        EditText serviceNote = (EditText) convertView.findViewById(R.id.et_note);
        ImageView removeBookService = (ImageView) convertView.findViewById(R.id.img_delete_service);

        removeBookService.setOnClickListener(this);
        serviceTime.setOnClickListener(this);
        serviceNote.setOnClickListener(this);

        adapterCategoryStaff = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListStaff);
        adapterCategoryStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStaff.setAdapter(adapterCategoryStaff);

        adapterCategoryService = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListService);
        adapterCategoryService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerService.setAdapter(adapterCategoryService);
        serviceTime.setText(user.mServiceTime);
        serviceNote.setText(user.mNote);

        mView = convertView;
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                showTimePickerDialog();
                break;
            case R.id.et_note:

                break;
            case R.id.img_delete_service:
                int pos = 1; //Stub
                if(pos < mListusers.size()) {
                    mListusers.remove(1);
                    BookServiceAdapter.this.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    private void showTimePickerDialog() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(mView.getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        //txtTime.setText(hourOfDay + ":" + minute);
                        int a = hourOfDay;
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }
}
