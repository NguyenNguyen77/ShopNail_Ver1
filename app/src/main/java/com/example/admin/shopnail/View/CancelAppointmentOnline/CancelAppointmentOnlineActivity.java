package com.example.admin.shopnail.View.CancelAppointmentOnline;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.CancelAppointmentAdapter;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;
import com.example.admin.shopnail.Presenter.CancelAppointmentOnline.CancelAppointmentOnlineImp;
import com.example.admin.shopnail.Presenter.CancelAppointmentOnline.CancelAppointmentOnlineLogic;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.Login.MainActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;

public class CancelAppointmentOnlineActivity extends Activity implements View.OnClickListener, CancelAppointmentOnlineView {

    private ViewManager mViewManager = ViewManager.getInstance();
    private TextView mTvDate, mTvEmpty;
    private ListView mListAppointmentByDate;
    private ProgressBar mProgressBar;
    private Button btnBack;
    private Calendar mCalender;
    private Date mDateSelected;
    private LinearLayout mLayoutList;
    ArrayList<String> listService = new ArrayList<>();
    BaseMethod method = new BaseMethod();
    CancelAppointmentOnlineImp cancelAppointmentOnlineImp;


    private CancelAppointmentOnlineImp getPresenter() {
        if (cancelAppointmentOnlineImp == null) {
            cancelAppointmentOnlineImp = new CancelAppointmentOnlineLogic(this, this);
        }
        return cancelAppointmentOnlineImp;
    }

    GsonLoginOutSide mGsonLoginOutSide;

    public GsonLoginOutSide getDataLogin() {
        if (mGsonLoginOutSide == null) {
            mGsonLoginOutSide = method.getGson().fromJson(getIntent().getStringExtra(KeyManager.DATA), GsonLoginOutSide.class);
        }
        return mGsonLoginOutSide;
    }

    @Override
    public void setAdapterList(CancelAppointmentAdapter appointmentAdapter) {
        mListAppointmentByDate.setAdapter(appointmentAdapter);
        mLayoutList.setVisibility(View.VISIBLE);
        mListAppointmentByDate.setVisibility(View.VISIBLE);
        mTvEmpty.setVisibility(View.GONE);
        if (appointmentAdapter.getCount() == 0) {
            mLayoutList.setVisibility(View.GONE);
            mListAppointmentByDate.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        mViewManager.showInprogressDialog();
    }

    @Override
    public void disibleProgressbar() {
        mViewManager.dismissInprogressDialog();
    }


    Dialog dialog;

    @Override
    public void showDialogConfirmCancel(final GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBean) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        dialog = new Dialog(ctw);
        dialog.setContentView(R.layout.cancel_service_appointment_dialog);
        dialog.setTitle(R.string.confirm);
        Button btnYes = (Button) dialog.findViewById(R.id.btnLogin);
        Button btnNo = (Button) dialog.findViewById(R.id.btnCancel);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewManager.showInprogressDialog();
                getPresenter().requestCancelService(ordersBean);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void hideProgress(int isVisible) {
        mProgressBar.setVisibility(isVisible);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cancel_appointment);
        initView();
        getPresenter().getListBookOnline(mTvDate.getText().toString());
        mListAppointmentByDate.setOnScrollListener(new AbsListView.OnScrollListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!view.canScrollList(View.SCROLL_AXIS_VERTICAL) && scrollState == SCROLL_STATE_IDLE) {
                    hideProgress(View.VISIBLE);
                    getPresenter().startScroll();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(KeyManager.VinhCNLog, firstVisibleItem + " " + visibleItemCount + totalItemCount);
            }
        });
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
        menu.findItem(R.id.action_make_book_appointment_online).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_make_book_appointment_online:
                mViewManager.setView(ViewManager.VIEW_KEY.BOOK_APPOINTMENT);
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
                getPresenter().getListBookOnline(mTvDate.getText().toString());
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
