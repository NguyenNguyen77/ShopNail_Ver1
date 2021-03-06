package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Model.MyCustomer.GsonClientTime;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.MyCustomer.TimeSelect;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.MyCustomerPresenter.MyCustommerLogic;

import com.example.admin.shopnail.Adapter.CustomerAdapter;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyCustomerActivity extends Activity implements MyCustomerView, View.OnClickListener, NetworkReceiver.ConnectivityReceiverListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    private MyCustommerLogic myCustommerLogic = new MyCustommerLogic(this, this);
    List<ServicesOfShop> listService;
    ListView listCustomer;
    Button btn_back;
    TextView txt_date, mTvEmpty;
    private Date mDateSelected;
    private Calendar mCalender;
    private ProgressBar mProgressBar;
    private LinearLayout lnCustomer;
    BaseMethod method = new BaseMethod();


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);
        mViewManager.setActivity(this);
        mViewManager.showInprogressDialog();
        initView();
//        myCustommerLogic.requestCustomerOrder(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        myCustommerLogic.requestCustomerOrder(txt_date.getText().toString());
//        listService = getListDataAcrylic();
//        MyCustomerAdapter myCustomerAdapter = new MyCustomerAdapter(getApplicationContext(), listService);
//        mLvMyCustomerList.setAdapter(myCustomerAdapter);
        context = this;


        listCustomer.setOnScrollListener(new AbsListView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!view.canScrollList(View.SCROLL_AXIS_VERTICAL) && scrollState == SCROLL_STATE_IDLE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    myCustommerLogic.startScroll();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(KeyManager.VinhCNLog, firstVisibleItem + " " + visibleItemCount + totalItemCount);
            }
        });


    }

    private void initView() {
//        new NailActionBarGenerator().generate(this,
//                NailActionBarGenerator.BarType.MY_CUSTOMER);
//        mBtnBack = findViewById(R.id.btn_go_back);
//
//        mLvMyCustomerList = findViewById(R.id.list_my_customer);
//        mBtnBack.setOnClickListener(this);
        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);

        listCustomer = findViewById(R.id.list_my_customer);
        btn_back = findViewById(R.id.btn_go_back);
        txt_date = findViewById(R.id.tv_date);
        lnCustomer = findViewById(R.id.layout_list_customer);
        mTvEmpty = (TextView) findViewById(R.id.txt_not_found_history);
        mProgressBar = findViewById(R.id.progress);


        getDefaultInfo();
        mTvEmpty.setVisibility(View.VISIBLE);
        listCustomer.setVisibility(View.GONE);

        listCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mViewManager.showInprogressDialog();
                myCustommerLogic.tranfertoDetailCustomer(i);
            }
        });

        btn_back.setOnClickListener(this);

        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_menu_for_staff).setVisible(true);
        menu.findItem(R.id.action_select_service).setVisible(true);
        menu.findItem(R.id.action_customer_service_history).setVisible(true);
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
            case R.id.action_customer_service_history:
                mViewManager.setView(ViewManager.VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
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
        dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mDateSelected = mCalender.getTime();
        String strDate = dft.format(mCalender.getTime());
        SpannableString strSpanned = new SpannableString(strDate);
        strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, 10, 0);
        strSpanned.setSpan(new UnderlineSpan(), 0, 10, 0);
        if(mViewManager.dateTemp.equals("")){
            txt_date.setText(strSpanned);
        }else {
            txt_date.setText(mViewManager.dateTemp);
            mViewManager.dateTemp="";
        }
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                String dayOfMonthText = "" + dayOfMonth;
                monthOfYear = monthOfYear + 1;
                String MonthText = "" + monthOfYear;
                if (dayOfMonth < 10) {
                    dayOfMonthText = "0" + dayOfMonth;
                }
                if (monthOfYear < 10) {
                    MonthText = "0" + MonthText;
                }
                String strDate = year + "-" + MonthText + "-" + dayOfMonthText;
                SpannableString strSpanned = new SpannableString(strDate);
                strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, strSpanned.length(), 0);
                strSpanned.setSpan(new UnderlineSpan(), 0, strSpanned.length(), 0);
                txt_date.setText(strSpanned);
                mDateSelected = mCalender.getTime();
                mViewManager.showInprogressDialog();
                myCustommerLogic.requestCustomerOrder(txt_date.getText().toString());
            }
        };
        String s = txt_date.getText() + "";
        String strArrtmp[] = s.split("-");
        int date = Integer.parseInt(strArrtmp[2]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[0]);
        DatePickerDialog pic = new DatePickerDialog(MyCustomerActivity.this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }

    CustomerAdapter mCustomerAdapter;

    @Override
    public void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient) {
        mCustomerAdapter = new CustomerAdapter(this, arrClient);
        if (mCustomerAdapter.getCount() == 0) {
            mTvEmpty.setVisibility(View.VISIBLE);
            listCustomer.setVisibility(View.GONE);
        } else {
            mTvEmpty.setVisibility(View.GONE);
            listCustomer.setVisibility(View.VISIBLE);
        }
        listCustomer.setAdapter(mCustomerAdapter);
        mViewManager.dismissInprogressDialog();
    }

    @Override
    public void showDialogChoosedTime(GsonGetClient.SuccessBean.ClientsBean clientsBean) {
//        showDialogSelectTimer(clientsBean);
    }


    List<TimeSelect> arrTimeSelect;

    @Override
    public void showTimeDialog(List<GsonClientTime.SuccessBean.TimeBean> listTime) {
        arrTimeSelect = new ArrayList<>();
        for (int i = 0; i < listTime.size(); i++) {
            String orderId = listTime.get(i).getOrderId();
            List<String> arrTime = listTime.get(i).getTime();
            for (int j = 0; j < arrTime.size(); j++) {
                arrTimeSelect.add(new TimeSelect(orderId, arrTime.get(j).toString()));
            }
        }
        showDialogSelectTimer(arrTimeSelect);
    }

    @Override
    public String getDateChoosed() {
        return txt_date.getText().toString();
    }

    @Override
    public void notifyChange() {
        mProgressBar.setVisibility(View.GONE);
        mCustomerAdapter.notifyDataSetChanged();
    }

    public void showDialogSelectTimer(List<TimeSelect> arr) {
        mViewManager.dismissInprogressDialog();
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select time booked");
        // add a list
//        String[] animals = {"12:30", "15:30", "18:30"};
        String[] animals = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            animals[i] = method.cover24To12(arr.get(i).getTimeName());

        }
//        String[] animals = new String[]{};
//        for (int i = 0; i < arrTimeSet.size(); i++){
//            animals[i] = arrTimeSet.get(i);
//        }
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // stub timer add
//                which = 0;
                myCustommerLogic.openDetailCustomer(which, arrTimeSelect);
                // stub timer end
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }
}
