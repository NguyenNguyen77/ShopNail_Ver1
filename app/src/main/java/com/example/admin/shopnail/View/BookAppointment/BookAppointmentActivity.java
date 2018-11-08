package com.example.admin.shopnail.View.BookAppointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Model.BookAppointment.BookService;
import com.example.admin.shopnail.Presenter.BookAppointment.BookAppointmentPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookAppointmentActivity extends Activity implements View.OnClickListener, IBookAppointmentView, NetworkReceiver.ConnectivityReceiverListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    private Calendar mCalender;
    private Button mBtnBack;
    private Button mBtnSubmit;
    private EditText mEtCustomerName;
    private EditText mEtCustomerPhone;
    private EditText mEtCustomerEmail;
    private TextView mTvDate;
    private ListView mLvSelectServiceItem;
    private TextView mTvAddMoreService;
    private String mEmailText;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Context mContext;

    private String currentTime;
    private String orderTime;
    private int currentPosition;

    private BookServiceAdapter mBookServiceAdapter;
    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;
    ArrayAdapter<String> mAdapterStaff = null;
    ArrayAdapter<String> mAdapterService = null;
    private BookAppointmentPresenter mBookAppointmentPresenter = new BookAppointmentPresenter(this, this);

    private ArrayList<BookService> mListBookService = new ArrayList<BookService>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        initView();
        loadInitData();
        mContext = this;
    }

    private void initView() {

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.BOOK_APPOINTMENT);
        mViewManager.setActivity(this);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mEtCustomerName = (EditText) findViewById(R.id.et_customer_name);
        mEtCustomerEmail = (EditText) findViewById(R.id.et_customer_email);
        mEtCustomerPhone = (EditText) findViewById(R.id.et_customer_phone);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mLvSelectServiceItem = (ListView) findViewById(R.id.lv_select_services);
        mTvAddMoreService = (TextView) findViewById(R.id.tv_add_more_services);

        mBtnSubmit.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mTvDate.setOnClickListener(this);
        mTvAddMoreService.setOnClickListener(this);
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

        //Validate Email
        mEtCustomerEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEnableSubmitButton();
                validateEmail();
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
                if (validateEmail()) {
                    reqBookAppointment();
                } else {
                    Toast.makeText(getApplicationContext(), "The email address is not valid.\nPlease check it and try again", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_back:
                handleBack();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        handleBack();
    }

    private void handleBack() {
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    private void reqBookAppointment() {
        String fullName = mEtCustomerName.getText().toString().trim();
        String phone = mEtCustomerPhone.getText().toString().trim();
        String dateOder = mTvDate.getText().toString();
        String email = mEtCustomerEmail.getText().toString().trim();
        mBookServiceAdapter = (BookServiceAdapter) mLvSelectServiceItem.getAdapter();
        mBookAppointmentPresenter.reqBookOnline(fullName, email, phone, dateOder, mBookServiceAdapter);

    }

    private void checkEnableSubmitButton() {
        int phoneSize = mEtCustomerPhone.getText().toString().trim().length();
        int nameSize = mEtCustomerName.getText().toString().trim().length();
        int emailSize = mEtCustomerEmail.getText().toString().trim().length();

        if ((phoneSize == 12) & (nameSize > 0) && (emailSize > 0)) {
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
        reqServiceList();
    }

    @Override
    public void updateServiceList(ArrayList<String> serviceList) {
        mAdapterService = new ArrayAdapter<String>(BookAppointmentActivity.this,
                android.R.layout.simple_spinner_item, serviceList);
        mViewManager.dismissInprogressDialog();
        addMoreService();//Add 1 service first
    }

    @Override
    public void onReqCallback(boolean result) {

    }

    @Override
    public void showErrorDialog(ViewManager.ERROR_CODE errorCode) {
        mViewManager.dismissInprogressDialog();
        if (errorCode == ViewManager.ERROR_CODE.GET_STAFF_FAIL) {
            showErrorDialog(getString(R.string.error_title), getString(R.string.error_get_staff));
        } else if (errorCode == ViewManager.ERROR_CODE.GET_SERVICE_FAIL) {
            showErrorDialog(getString(R.string.error_title), getString(R.string.error_get_service));
        }
    }

    @Override
    public void checkTimeBookOnline(String staffName, int pos, String timeOrder) {
        currentTime = mBookServiceAdapter.getItem(pos).getServiceTime();
        orderTime = timeOrder;
        currentPosition = pos;
        String date = mTvDate.getText().toString();
        int selectStaff = mBookServiceAdapter.getItem(pos).getSelectStaff();
        mBookAppointmentPresenter.checkTimeBookOnline(staffName, selectStaff, date, timeOrder);
    }

    @Override
    public void updateOrderTime() {
        if (currentPosition <= mBookServiceAdapter.getCount()) {
            BookService test = mBookServiceAdapter.getItem(currentPosition);
            mBookServiceAdapter.getItem(currentPosition).setServiceTime(orderTime);
            mBookServiceAdapter.notifyDataSetChanged();
        }
    }

    public void getDefaultInfo() {
        mCalender = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String strDate = dft.format(mCalender.getTime());
        showUnderLineText(strDate, mTvDate);
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
                if (validateDatTime(year, monthOfYear, dayOfMonth)) {
                    showUnderLineText(year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth), mTvDate);
                } else {
                    Toast.makeText(mContext, R.string.error_select_date, Toast.LENGTH_LONG).show();
                    getDefaultInfo();
                }
            }
        };
        String s = mTvDate.getText() + "";
        String strArrtmp[] = s.split("-");
        int date = Integer.parseInt(strArrtmp[2]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[0]);
        DatePickerDialog pic = new DatePickerDialog(BookAppointmentActivity.this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }

    public void addMoreService() {
        BookService defaultService = getDefaultBookServiceItem();
        if (defaultService != null) {
            if ((mLvSelectServiceItem.getAdapter() != null) && mLvSelectServiceItem.getAdapter().getCount() > 0) {
                mBookServiceAdapter = (BookServiceAdapter) mLvSelectServiceItem.getAdapter();
                mBookServiceAdapter.add(defaultService);
            } else {
                mListBookService.add(defaultService);
                mBookServiceAdapter = new BookServiceAdapter(this, mListBookService, this);
            }

            mLvSelectServiceItem.setAdapter(mBookServiceAdapter);
        }
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

        defaultBookService = new BookService(listStaff, listService, "--:--", "", 0, 0);
        return defaultBookService;
    }

    private void loadInitData() {
        mViewManager.showInprogressDialog();
        reqStaffList();
    }

    private void showUnderLineText(String text, TextView id) {
        SpannableString contentSpanned = new SpannableString(text);
        contentSpanned.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        id.setText(contentSpanned);
    }

    public void showErrorDialog(String title, String content) {
        android.support.v7.view.ContextThemeWrapper ctw = new android.support.v7.view.ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog commonDialog = new Dialog(ctw);
        commonDialog.setContentView(R.layout.error_dialog);
        commonDialog.setTitle(title);

        TextView tvContent = (TextView) commonDialog.findViewById(R.id.txt_dialog_content);
        tvContent.setText(content);

        Button btnOK = (Button) commonDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog.dismiss();
                handleBack();
            }
        });
        commonDialog.show();
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }

    public boolean validateEmail() {
        mEmailText = mEtCustomerEmail.getEditableText().toString().trim();
        if (mEmailText.matches(emailPattern) && mEmailText.length() > 0) {
            mEtCustomerEmail.setTextColor(getResources().getColor(R.color.email_ok));
            return true;
        } else {
            mEtCustomerEmail.setTextColor(getResources().getColor(R.color.email_failed));
            return false;
        }
    }

    private boolean validateDatTime(int year, int month, int day) {
        mCalender = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String strDate = dft.format(mCalender.getTime());

        String strArrtmp[] = strDate.split("-");
        int curentDay = Integer.parseInt(strArrtmp[2]);
        int curentMonth = Integer.parseInt(strArrtmp[1]) - 1;
        int curentYear = Integer.parseInt(strArrtmp[0]);

        if ((year >= curentYear) && (month >= curentMonth) && (day >= curentDay)) {
            return true;
        } else {
            return false;
        }
    }
}
