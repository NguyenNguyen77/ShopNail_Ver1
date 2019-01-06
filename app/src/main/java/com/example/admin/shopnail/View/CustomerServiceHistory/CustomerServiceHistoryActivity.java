package com.example.admin.shopnail.View.CustomerServiceHistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Adapter.HistoriesDetailsAdapter;
import com.example.admin.shopnail.Adapter.MyCustomerAdapter;
import com.example.admin.shopnail.Adapter.ServiceHistoryAdapter;
import com.example.admin.shopnail.CustomViewListExpand.SingleToast;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Presenter.CustomerServiceHistory.CustomerServiceHistoryPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.MyCustomer.MyCustomerActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerServiceHistoryActivity extends Activity implements View.OnClickListener, ICustomerServiceHistoryView, NetworkReceiver.ConnectivityReceiverListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    private ImageView mImgSubmit;
    private Button mBtBack;
    private TextView mTvDate;
    private Date mDateSelected;
    private Calendar mCalender;
    private TextView mTvEmpty;
    private LinearLayout mLayoutList;
    private ListView mListCustomerServiceHistoryByDate;
    private CustomerServiceHistoryPresenter mCustomerServiceHistoryPresenter;

    private CustomerServiceHistoryPresenter getPresenter(){
        if (mCustomerServiceHistoryPresenter == null){
            mCustomerServiceHistoryPresenter = new CustomerServiceHistoryPresenter(this, this);
        }
        return mCustomerServiceHistoryPresenter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_history);
        initView();
        mViewManager.showInprogressDialog();
        getPresenter().requestCustomerOrder(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    private void initView() {
        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.CUSTOMER_SERVICE_HISTORY);
        mViewManager.setActivity(this);

        mImgSubmit = (ImageView) findViewById(R.id.img_submit);
        mBtBack = (Button) findViewById(R.id.btn_back);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvEmpty = (TextView) findViewById(R.id.txt_not_found_history);
        mLayoutList = (LinearLayout) findViewById(R.id.layout_list);
        mListCustomerServiceHistoryByDate = (ListView) findViewById(R.id.lv_service_history);
        mImgSubmit.setOnClickListener(this);
        mBtBack.setOnClickListener(this);
        mTvDate.setOnClickListener(this);

        getDefaultInfo();
        getPresenter();
        mListCustomerServiceHistoryByDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Customer selectedItem = (Customer) parent.getItemAtPosition(position);
//                loadCustomerServiceHistoryByID(selectedItem.name, selectedItem.phone);  //Get ID Customer & send to server

                mViewManager.showInprogressDialog();
                getPresenter().OpenHistorisDetail(position);
            }
        });

//        loadCustomerServiceHistoryByDate(mDateSelected);
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
        dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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

                String strDate = year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", (dayOfMonth));
                SpannableString strSpanned = new SpannableString(strDate);
                strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, strSpanned.length(), 0);
                strSpanned.setSpan(new UnderlineSpan(), 0, strSpanned.length(), 0);
                mTvDate.setText(strSpanned);
                mDateSelected = mCalender.getTime();
                mViewManager.showInprogressDialog();
                getPresenter().requestCustomerOrder(mTvDate.getText().toString());

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

    private void loadCustomerServiceHistoryByDate(Date selected) {
        mViewManager.showInprogressDialog();
//        mCustomerServiceHistoryPresenter.loadCustomerServiceHistoryByDate(selected);
    }

    @Override
    public void updateListCustomerServiceHistoryByDate(ArrayList<Customer> listCustomerServiceHistory) {
        mViewManager.dismissInprogressDialog();
        if (listCustomerServiceHistory.size() == 0) {
            mLayoutList.setVisibility(View.GONE);
            mListCustomerServiceHistoryByDate.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
//            CustomerAdapter adapter = new CustomerAdapter(this, listCustomerServiceHistory);
//            mListCustomerServiceHistoryByDate.setAdapter(adapter);
        }else {
            mLayoutList.setVisibility(View.VISIBLE);
            mListCustomerServiceHistoryByDate.setVisibility(View.VISIBLE);
            mTvEmpty.setVisibility(View.GONE);
        }
    }

//    private void loadCustomerServiceHistoryByID(String name, String phone) {
//        mCustomerServiceHistoryPresenter.loadCustomerServiceHistoryByID(name, phone);
//    }

//    @Override
//    public void showListCustomerServiceHistoryByID(ArrayList<ServiceHistory> listCustomerServiceHistoryByID) {
////        if (listCustomerServiceHistoryByID.size() > 0) {
////            showDetailServiceDialog(listCustomerServiceHistoryByID);
////        } else {
////            //Show error dialog
////        }
//    }

    @Override
    public void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient) {
        CustomerAdapter mCustomerAdapter = new CustomerAdapter(this, arrClient);
        mListCustomerServiceHistoryByDate.setAdapter(mCustomerAdapter);
        if (arrClient.size() == 0) {
            mLayoutList.setVisibility(View.GONE);
            mListCustomerServiceHistoryByDate.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
        }else {
            mLayoutList.setVisibility(View.VISIBLE);
            mListCustomerServiceHistoryByDate.setVisibility(View.VISIBLE);
            mTvEmpty.setVisibility(View.GONE);
        }
        mViewManager.dismissInprogressDialog();
    }

    @Override
    public void OpenDialogHistories(List<GsonProductCustomer.SuccessBean.ProductsBean> listProduct) {
        if (listProduct.size() > 0) {
            showDetailServiceDialog(listProduct);
        } else {
            SingleToast.show(this, "Empty", 3000);
        }
    }

    @Override
    public void closeProgress() {
        mViewManager.dismissInprogressDialog();
    }

    private void showDetailServiceDialog(List<GsonProductCustomer.SuccessBean.ProductsBean> listServiceHistory) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog detailService = new Dialog(ctw);
        detailService.requestWindowFeature(Window.FEATURE_NO_TITLE);
        detailService.setContentView(R.layout.service_history_dialog);

//        detailService.setTitle(R.string.customer_info);

        Button btnClose = (Button) detailService.findViewById(R.id.btnClose);
        TextView tvDateDetail = (TextView) detailService.findViewById(R.id.tv_date_select);
        ListView lvServiceHistory = (ListView) detailService.findViewById(R.id.lv_customer_service_history);
        HistoriesDetailsAdapter myDetailCustomerAdapter = new HistoriesDetailsAdapter(this, listServiceHistory);
        lvServiceHistory.setAdapter(myDetailCustomerAdapter);
        lvServiceHistory.setVisibility(View.VISIBLE);
        tvDateDetail.setText(mTvDate.getText());
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailService.dismiss();
            }
        });
        detailService.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        detailService.getWindow().setLayout((width / 6) * 5, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }
}
