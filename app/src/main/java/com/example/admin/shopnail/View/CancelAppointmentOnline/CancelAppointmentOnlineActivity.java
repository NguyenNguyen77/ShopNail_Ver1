package com.example.admin.shopnail.View.CancelAppointmentOnline;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.shopnail.Adapter.CancelAppointmentAdapter;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CancelAppointmentOnlineActivity extends Activity implements View.OnClickListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    private TextView mTvDate,mTvEmpty;
    private ListView mListAppointmentByDate;
    private ProgressBar mProgressBar;
    private Button btnBack;
    private Calendar mCalender;
    private Date mDateSelected;
    private LinearLayout mLayoutList;
    private CancelAppointmentAdapter cancelAppointmentAdapter;
    ArrayList<String> listService = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cancel_appointment);

        initView();

        listService.add("Nhuom Toc");
        listService.add("Goi Dau");
        listService.add("Duoi Toc");
        cancelAppointmentAdapter = new CancelAppointmentAdapter(this,listService);
        mListAppointmentByDate.setAdapter(cancelAppointmentAdapter);

        mLayoutList.setVisibility(View.VISIBLE);
        mListAppointmentByDate.setVisibility(View.VISIBLE);
        mTvEmpty.setVisibility(View.GONE);

//        mViewManager.showInprogressDialog();
    }

    private void initView() {
        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.CANCEL_APPOINTMENT);
        mViewManager.setActivity(this);

        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvEmpty = (TextView) findViewById(R.id.txt_not_found_appointment);
        mListAppointmentByDate = (ListView) findViewById(R.id.lv_appointment);
        mProgressBar = findViewById(R.id.progress);
        mLayoutList = (LinearLayout) findViewById(R.id.layout_list);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(this);
        mTvDate.setOnClickListener(this);

        getDefaultInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                showDatePickerDialog();
                break;
            case R.id.img_submit:
//                loadCustomerServiceHistoryByDate(mDateSelected);
                break;
            case R.id.btn_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }
    public void getDefaultInfo() {
        mCalender = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mDateSelected = mCalender.getTime();
        String strDate = dft.format(mCalender.getTime());
        SpannableString strSpanned = new SpannableString(strDate);
        strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, 10, 0);
        strSpanned.setSpan(new UnderlineSpan(), 0, 10, 0);
        mTvDate.setText(strSpanned);
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

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {

                String strDate = year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", (dayOfMonth));
                SpannableString strSpanned = new SpannableString(strDate);
                strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, strSpanned.length(), 0);
                strSpanned.setSpan(new UnderlineSpan(), 0, strSpanned.length(), 0);
                mTvDate.setText(strSpanned);
                mDateSelected = mCalender.getTime();
                mViewManager.showInprogressDialog();
//                getPresenter().requestCustomerOrder(mTvDate.getText().toString());

            }
        };
        String s = mTvDate.getText() + "";
        String strArrtmp[] = s.split("-");
        int date = Integer.parseInt(strArrtmp[2]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[0]);
        DatePickerDialog pic = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }

}
