package com.example.admin.shopnail.View.BookAppointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;
import com.example.admin.shopnail.Model.BookAppointment.BookService;
import com.example.admin.shopnail.Model.Employee;
import com.example.admin.shopnail.Presenter.BookAppointment.BookAppointmentPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.CustomerServiceHistory.CustomerServiceHistoryActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends Activity implements View.OnClickListener, IBookAppointmentView {
    protected ViewManager mViewManager = ViewManager.getInstance();
    private Calendar mCalender;
    private Date mDateSelected;
    private Button mBtnBack;
    private Button mBtnSubmit;
    private EditText mEtCustomerName;
    private EditText mEtCustomerPhone;
    private TextView mTvDate;
    private ListView mLvSelectServiceItem;
    private TextView mTvAddMoreervice;

    private BookServiceAdapter mBookServiceAdapter;
    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;
    ArrayAdapter<String> mAdapterStaff = null;
    ArrayAdapter<String> mAdapterService = null;
    private BookAppointmentPresenter mBookAppointmentPresenter = new BookAppointmentPresenter(this);

    private ArrayList<BookService> mListBookService = new ArrayList<BookService>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.BOOK_APPOINTMENT);
        mViewManager.setActivity(this);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mEtCustomerName = (EditText) findViewById(R.id.et_customer_name);
        mEtCustomerPhone = (EditText) findViewById(R.id.et_customer_phone);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mLvSelectServiceItem = (ListView) findViewById(R.id.lv_select_services);
        mTvAddMoreervice = (TextView) findViewById(R.id.tv_add_more_services);

        reqStaffList();
        reqServiceList();
        addMoreService();//Add 1 service first


        mBtnSubmit.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mTvDate.setOnClickListener(this);
        mTvAddMoreervice.setOnClickListener(this);
        mBtnSubmit.setEnabled(false);
        mBtnSubmit.setClickable(false);

        getDefaultInfo(); //display current date

        mEtCustomerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableSubmitButton();
            }

            @Override
            public void afterTextChanged(Editable text) {
                mTextSizeAfter = text.length();
                if (mTextSizeAfter > mTextSizeBefore) {
                    if ((text.length() == 3) || (text.length() == 7)) {
                        text.append('-');
                    }
                }
                mTextSizeBefore = mTextSizeAfter;
            }
        });

        mEtCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableSubmitButton();
            }

            @Override
            public void afterTextChanged(Editable text) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                showDatePickerDialog();
                break;
            case R.id.tv_add_more_services:
                addMoreService();
                break;
            case R.id.btn_submit:
                reqBookAppointment();
                break;
            case R.id.btn_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    private void reqBookAppointment() {
        String customerName = mEtCustomerName.getText().toString().trim();
        String customerPhone = mEtCustomerPhone.getText().toString().trim();

    }

    private void checkEnableSubmitButton() {
        int phoneSize = mEtCustomerPhone.getText().toString().trim().length();
        int nameSize = mEtCustomerName.getText().toString().trim().length();

        if (phoneSize == 12 & nameSize > 0) {
            mBtnSubmit.setEnabled(true);
            mBtnSubmit.setClickable(true);
        } else {
            mBtnSubmit.setEnabled(false);
            mBtnSubmit.setClickable(false);
        }
    }

    private void reqStaffList() {
        mBookAppointmentPresenter.reqGetStaffList();
    }

    private void reqServiceList() {
        mBookAppointmentPresenter.reqGetServiceList();
    }

    @Override
    public void updateStaffList(ArrayList<String> staffList) {
        mAdapterStaff = new ArrayAdapter<String>(BookAppointmentActivity.this,
                android.R.layout.simple_spinner_item, staffList);
        mAdapterStaff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void updateServiceList(ArrayList<String> serviceList) {
        mAdapterService = new ArrayAdapter<String>(BookAppointmentActivity.this,
                android.R.layout.simple_spinner_item, serviceList);
        mAdapterService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onReqCallback(boolean result) {

    }

    public void getDefaultInfo() {
        mCalender = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        mDateSelected = mCalender.getTime();
        String strDate = dft.format(mCalender.getTime());
        mTvDate.setText(strDate);
    }

    public String getCurrentTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String currentTime = String.valueOf(hour) + ":" + String.valueOf(minute);
        return currentTime;
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                mTvDate.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
                mDateSelected = mCalender.getTime();
            }
        };
        String s = mTvDate.getText() + "";
        String strArrtmp[] = s.split("/");
        int date = Integer.parseInt(strArrtmp[0]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(BookAppointmentActivity.this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }

    public void addMoreService() {
        mListBookService.add(getDefaultBookServiceItem());
        mBookServiceAdapter = new BookServiceAdapter(this, mListBookService);
        mLvSelectServiceItem.setAdapter(mBookServiceAdapter);
    }

    public BookService getDefaultBookServiceItem() {
        BookService defaultBookService;
        ArrayList<String> listStaff = new ArrayList<String>();
        ArrayList<String> listService = new ArrayList<String>();

        if ((mAdapterStaff == null) || (mAdapterService == null)) {
            return null;
        }
        for (int i = 0; i < mAdapterStaff.getCount(); i++) {
            listStaff.add(mAdapterStaff.getItem(i));
        }

        for (int i = 0; i < mAdapterService.getCount(); i++) {
            listService.add(mAdapterService.getItem(i));
        }

        defaultBookService = new BookService(listStaff, listService, getCurrentTime(), "N/A");
        return defaultBookService;
    }
}
