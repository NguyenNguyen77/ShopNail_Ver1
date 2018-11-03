package com.example.admin.shopnail.Adapter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Calendar;

public class BookServiceAdapter extends ArrayAdapter<BookService> implements View.OnClickListener {
    ArrayAdapter<String> adapterCategoryStaff = null;
    ArrayAdapter<String> adapterCategoryService = null;
    ArrayList<BookService> mListusers = null;
    TextView mServiceTime;
    private Context mContext = null;
    private int mPosition = 0;
    public int mHour, mMinute;
    private View mView;
    protected ViewManager mViewManager = ViewManager.getInstance();

    public BookServiceAdapter(Context context, ArrayList<BookService> users) {
        super(context, 0, users);
        this.mContext = context;
        mListusers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.mPosition = position;
        BookService user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book_service, parent, false);
        }
        Spinner spinnerStaff = (Spinner) convertView.findViewById(R.id.spinnerStaff);
        Spinner spinnerService = (Spinner) convertView.findViewById(R.id.spinnerService);
        mServiceTime = (TextView) convertView.findViewById(R.id.tv_time);
        EditText serviceNote = (EditText) convertView.findViewById(R.id.et_note);
        ImageView removeBookService = (ImageView) convertView.findViewById(R.id.img_delete_service);
        LinearLayout lvItem = (LinearLayout) convertView.findViewById(R.id.item_list_book);

        removeBookService.setOnClickListener(this);
        mServiceTime.setOnClickListener(this);
        serviceNote.setOnClickListener(this);

        adapterCategoryStaff = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListStaff);
        adapterCategoryStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStaff.setAdapter(adapterCategoryStaff);

        adapterCategoryService = new ArrayAdapter<String>(convertView.getContext(),
                android.R.layout.simple_spinner_item, user.mListService);
        adapterCategoryService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerService.setAdapter(adapterCategoryService);
        showUnderLineText(user.mServiceTime.toString(), mServiceTime);
        serviceNote.setText(user.mNote);

        if (position % 2 == 1) {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_1);
            lvItem.setBackgroundColor(backgroundColor);
        } else {
            int backgroundColor = ContextCompat.getColor(convertView.getContext(), R.color.list_2);
            lvItem.setBackgroundColor(backgroundColor);
        }

        mView = convertView;

        removeBookService.setVisibility(View.INVISIBLE);
        if (mListusers.size() > 1) {
            removeBookService.setVisibility(View.VISIBLE);
        }
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
                showConfirmDialog("Confirm", "Do you want to delete?");
                break;
            default:
                break;
        }
    }

    private void showTimePickerDialog() {
        String strArrtmp[] = mServiceTime.getText().toString().split(":");
        mHour = Integer.parseInt(strArrtmp[0]);
        mMinute = Integer.parseInt(strArrtmp[1]);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(mView.getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        showUnderLineText(hourOfDay + ":" + minute, mServiceTime);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void showUnderLineText(String text, TextView id) {
        SpannableString contentSpanned = new SpannableString(text);
        contentSpanned.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        id.setText(contentSpanned);
    }

    public void showConfirmDialog(String title, String content) {
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
                    mListusers.remove(mPosition);
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
