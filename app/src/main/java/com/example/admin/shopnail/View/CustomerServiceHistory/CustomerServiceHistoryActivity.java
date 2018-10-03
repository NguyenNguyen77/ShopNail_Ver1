package com.example.admin.shopnail.View.CustomerServiceHistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Adapter.ServiceHistoryAdapter;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Presenter.CustomerServiceHistory.CustomerServiceHistoryPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomerServiceHistoryActivity extends Activity implements View.OnClickListener, ICustomerServiceHistoryView {
    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtSubmit;
    private Button mBtBack;
    private TextView mTvDate;
    private Date mDateSelected;
    private Calendar mCalender;
    private TextView mTvEmpty;
    private LinearLayout mLayoutList;
    private ListView mListCustomerServiceHistoryByDate;
    private CustomerServiceHistoryPresenter mCustomerServiceHistoryPresenter = new CustomerServiceHistoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_history);

        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.CUSTOMER_SERVICE_HISTORY);
        mViewManager.setActivity(this);

        mBtSubmit = (Button) findViewById(R.id.btn_submit_date);
        mBtBack = (Button) findViewById(R.id.btn_back);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvEmpty = (TextView) findViewById(R.id.txt_not_found_history);
        mLayoutList = (LinearLayout) findViewById(R.id.layout_list);
        mListCustomerServiceHistoryByDate = (ListView) findViewById(R.id.lv_service_history);

        mBtSubmit.setOnClickListener(this);
        mBtBack.setOnClickListener(this);
        mTvDate.setOnClickListener(this);

        getDefaultInfo();

        mListCustomerServiceHistoryByDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer selectedItem = (Customer) parent.getItemAtPosition(position);
                loadCustomerServiceHistoryByID(selectedItem.name, selectedItem.phone);  //Get ID Customer & send to server
            }
        });

        loadCustomerServiceHistoryByDate(mDateSelected);    //Show history current date
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                showDatePickerDialog();
                break;
            case R.id.btn_submit_date:
                loadCustomerServiceHistoryByDate(mDateSelected);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getActionBar().setIcon(R.drawable.ic_menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_menu_for_staff).setVisible(true);
        menu.findItem(R.id.action_select_service).setVisible(true);
        menu.findItem(R.id.action_my_customer).setVisible(true);
        menu.findItem(R.id.action_manage_staff).setVisible(true);
        menu.findItem(R.id.action_staff_info).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_for_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);
                return true;
            case R.id.action_select_service:
                mViewManager.setView(ViewManager.VIEW_KEY.SELECT_SERVICE);
                return true;
            case R.id.action_my_customer:
                mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
                return true;
            case R.id.action_manage_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MANAGE_STAFF);
                return true;
            case R.id.action_staff_info:
                mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getDefaultInfo() {
        mCalender = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        mDateSelected = mCalender.getTime();
        String strDate = dft.format(mCalender.getTime());
        SpannableString strSpanned = new SpannableString(strDate);
        strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, 10, 0);
        strSpanned.setSpan(new UnderlineSpan(), 0, 10, 0);
        mTvDate.setText(strSpanned);
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                String strDate = (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year;
                SpannableString strSpanned = new SpannableString(strDate);
                strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, 10, 0);
                strSpanned.setSpan(new UnderlineSpan(), 0, 10, 0);
                mTvDate.setText(strSpanned);
                mDateSelected = mCalender.getTime();
            }
        };
        String s = mTvDate.getText() + "";
        String strArrtmp[] = s.split("/");
        int date = Integer.parseInt(strArrtmp[0]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(CustomerServiceHistoryActivity.this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }

    private void loadCustomerServiceHistoryByDate(Date selected) {
        mCustomerServiceHistoryPresenter.loadCustomerServiceHistoryByDate(selected);
    }

    @Override
    public void updateListCustomerServiceHistoryByDate(ArrayList<Customer> listCustomerServiceHistory) {
        if (listCustomerServiceHistory.size() > 0) {
            mLayoutList.setVisibility(View.VISIBLE);
            mListCustomerServiceHistoryByDate.setVisibility(View.VISIBLE);
            mTvEmpty.setVisibility(View.GONE);
            CustomerAdapter adapter = new CustomerAdapter(this, listCustomerServiceHistory);
            mListCustomerServiceHistoryByDate.setAdapter(adapter);
        }
    }

    private void loadCustomerServiceHistoryByID(String name, String phone) {
        mCustomerServiceHistoryPresenter.loadCustomerServiceHistoryByID(name, phone);
    }

    @Override
    public void showListCustomerServiceHistoryByID(ArrayList<ServiceHistory> listCustomerServiceHistoryByID) {
        if (listCustomerServiceHistoryByID.size() > 0) {
            showDetailServiceDialog(listCustomerServiceHistoryByID);
        } else {
            //Show error dialog
        }
    }

    private void showDetailServiceDialog(ArrayList<ServiceHistory> listServiceHistory) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog login = new Dialog(ctw);
        login.setContentView(R.layout.service_history_dialog);
        login.setTitle(R.string.customer_info);

        Button btnLogin = (Button) login.findViewById(R.id.btnOK);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        ListView lvServiceHistory = (ListView) login.findViewById(R.id.lv_customer_service_history);
        ServiceHistoryAdapter adapter = new ServiceHistoryAdapter(this, listServiceHistory);
        lvServiceHistory.setVisibility(View.VISIBLE);
        lvServiceHistory.setAdapter(adapter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        login.show();
    }
}
