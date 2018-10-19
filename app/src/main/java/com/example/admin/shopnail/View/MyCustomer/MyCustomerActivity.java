package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

<<<<<<< HEAD
import com.example.admin.shopnail.Adapter.MyCustomerAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.MyCustomerPresenter.MyCustommerLogic;
=======
import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
>>>>>>> 3dfaee1c9529e3037a7c5df6ab65c98bdd7e86ec
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.CustomerServiceHistory.CustomerServiceHistoryActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

<<<<<<< HEAD
public class MyCustomerActivity extends Activity implements MyCustomerView, View.OnClickListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtnBack;
    private Button mBtnUpdateService;
    private Button mBtnCancelService;
    private ListView mLvMyCustomerList;
    private MyCustommerLogic myCustommerLogic = new MyCustommerLogic(this, this);
    List<ServicesOfShop> listService;
=======
public class MyCustomerActivity extends Activity implements View.OnClickListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    ListView listCustomer;
    Button btn_back;
    TextView txt_date;
    private Date mDateSelected;
    private Calendar mCalender;
>>>>>>> 3dfaee1c9529e3037a7c5df6ab65c98bdd7e86ec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);
        initView();
        myCustommerLogic.requestCustomerOrder();
        listService = getListDataAcrylic();
        MyCustomerAdapter myCustomerAdapter = new MyCustomerAdapter(getApplicationContext(),listService);
        mLvMyCustomerList.setAdapter(myCustomerAdapter);

<<<<<<< HEAD

    }

    private void initView() {
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);
        mBtnBack = (Button) findViewById(R.id.btn_go_back);
        mBtnUpdateService = (Button) findViewById(R.id.btn_update_service);
        mBtnCancelService = (Button) findViewById(R.id.btn_cancel_service);
        mLvMyCustomerList = (ListView)findViewById(R.id.list_my_customer);
        mBtnBack.setOnClickListener(this);
        mBtnUpdateService.setOnClickListener(this);
        mBtnCancelService.setOnClickListener(this);
=======
        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);

        listCustomer = findViewById(R.id.list_my_customer);
        btn_back = (Button) findViewById(R.id.btn_go_back);
        txt_date = findViewById(R.id.tv_date);

        //Stub data
        ArrayList<Customer> users = new ArrayList<>();
        Customer a = new Customer("Nguyen Van B", "0123456789");
        Customer b = new Customer("Nguyen B", "0123456789");
        Customer c = new Customer("Nguyen Thi C", "0123456789");
        Customer d = new Customer("Nguyen Cong D", "0123456789");

        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
        //Stub data

        getDefaultInfo();

        CustomerAdapter mCustomerAdapter = new CustomerAdapter(getApplicationContext(),users);
        listCustomer.setAdapter(mCustomerAdapter);

        listCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tranfertoDetailCustomer();
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
                showDatePickerDialog();
            }
        });
//        btn_back.setOnClickListener(this);
>>>>>>> 3dfaee1c9529e3037a7c5df6ab65c98bdd7e86ec
        mViewManager.setActivity(this);
    }

    private void tranfertoDetailCustomer() {
        mViewManager.setView(ViewManager.VIEW_KEY.MY_DETAIL_CUSTOMER);
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
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
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
                String strDate = (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year;
                SpannableString strSpanned = new SpannableString(strDate);
                strSpanned.setSpan(new StyleSpan(Typeface.ITALIC), 0, strSpanned.length(), 0);
                strSpanned.setSpan(new UnderlineSpan(), 0, strSpanned.length(), 0);
                txt_date.setText(strSpanned);
                mDateSelected = mCalender.getTime();
            }
        };
        String s = txt_date.getText() + "";
        String strArrtmp[] = s.split("/");
        int date = Integer.parseInt(strArrtmp[0]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(MyCustomerActivity.this, AlertDialog.THEME_HOLO_LIGHT, callback, year, month, date);
        pic.setTitle(R.string.select_date_to_view_history);
        pic.show();
    }
}
