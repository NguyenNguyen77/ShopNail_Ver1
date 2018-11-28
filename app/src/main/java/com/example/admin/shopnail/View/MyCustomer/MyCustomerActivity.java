package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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
    TextView txt_date;
    private Date mDateSelected;
    private Calendar mCalender;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);
        mViewManager.setActivity(this);
        initView();
        myCustommerLogic.requestCustomerOrder(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
//        listService = getListDataAcrylic();
//        MyCustomerAdapter myCustomerAdapter = new MyCustomerAdapter(getApplicationContext(), listService);
//        mLvMyCustomerList.setAdapter(myCustomerAdapter);
        context = this;

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

        //Stub data
//        ArrayList<Customer> users = new ArrayList<>();
//        Customer a = new Customer("Nguyen Van B", "0123456789");
//        Customer b = new Customer("Nguyen B", "0123456789");
//        Customer c = new Customer("Nguyen Thi C", "0123456789");
//        Customer d = new Customer("Nguyen Cong D", "0123456789");
//
//        users.add(a);
//        users.add(b);
//        users.add(c);
//        users.add(d);
        //Stub data

        getDefaultInfo();


        listCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myCustommerLogic.tranfertoDetailCustomer(i);

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(MyCustomerActivity.this);
            }
        });

        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "A nên chọn ngày 19-10-2018 để có data", Toast.LENGTH_LONG).show();
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
            case R.id.btn_back:
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
        txt_date.setText(strSpanned);
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                String strDate = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth);
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

    @Override
    public void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient) {
        CustomerAdapter mCustomerAdapter = new CustomerAdapter(this, arrClient);
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

    public void showDialogSelectTimer(List<TimeSelect> arr) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select time booked");
        // add a list
//        String[] animals = {"12:30", "15:30", "18:30"};
        String[] animals = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            animals[i] = arr.get(i).getTimeName();
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
