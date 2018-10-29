package com.example.admin.shopnail.View.MyDetailCustomer;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Adapter.MyCustomerAdapter;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonProductCustomer;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.MyDetailCustom.MyDetailCustomerLogic;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_HISTORY_CHOOSED;
import static com.example.admin.shopnail.Manager.KeyManager.DATE;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.TIME_ORDER;

public class MyDetailCustomerActivity extends Activity implements View.OnClickListener, MyDetailCustomerView {

    protected ViewManager mViewManager = ViewManager.getInstance();
    MyDetailCustomerLogic mMyDetailCustomerLogic = new MyDetailCustomerLogic(this, this);

    private Button mBtnBack;
    private Button mBtnUpdateService;
    private Button mBtnCancelService;
    private ListView mLvMyCustomerList;
    BaseMethod method = new BaseMethod();
    private TextView tvDate;
    private TextView tvName;
    private TextView tvPhone;
    //    private TextView tvExtra;
    private TextView tvTime;
    List<ServicesOfShop> listService;

    @Override
    public String getOrderID() {
        String orderId = getIntent().getStringExtra(ORDER_ID);
        return orderId;
    }

    @Override
    public String getTimeName() {
        String TimeName = getIntent().getStringExtra(TIME_ORDER);
        return TimeName;
    }

    @Override
    public void setListProducts(List<GsonProductCustomer.SuccessBean.ProductsBean> listProduct) {
        MyCustomerAdapter myDetailCustomerAdapter = new MyCustomerAdapter(getApplicationContext(), listProduct);
        mLvMyCustomerList.setAdapter(myDetailCustomerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_detail_customer);
        initView();
        mMyDetailCustomerLogic.requestCustomerProducts(getOrderID());
//        listService = getListDataAcrylic();
//        MyCustomerAdapter myDetailCustomerAdapter = new MyCustomerAdapter(getApplicationContext(), listService);
//        mLvMyCustomerList.setAdapter(myDetailCustomerAdapter);
    }


    private void initView() {
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);
        mBtnBack = (Button) findViewById(R.id.btn_go_back);
        mBtnUpdateService = (Button) findViewById(R.id.btn_update_service);
        mBtnCancelService = (Button) findViewById(R.id.btn_cancel_service);
        mLvMyCustomerList = (ListView) findViewById(R.id.list_my_customer);
        tvTime = findViewById(R.id.tv_time);
        tvName = findViewById(R.id.tv_customer_name);
        tvDate = findViewById(R.id.tv_date);
        tvPhone = findViewById(R.id.tv_customer_phone);
//        tvExtra = findViewById(R.id.txt_extra);
        mBtnBack.setOnClickListener(this);
        mBtnUpdateService.setOnClickListener(this);
        mBtnCancelService.setOnClickListener(this);
        mViewManager.setActivity(this);
        tvDate.setText(getDateChoosed());
        tvName.setText(getClientChoosed().getClientName());
        tvPhone.setText(getClientChoosed().getClientPhone());
        tvTime.setText(getTimeName());
//        tvExtra.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogUpdateExtra();
//            }
//        });
    }

    private List<String> getListOrderID() {
        return getClientChoosed().getClientOrderId();
    }

    private GsonGetClient.SuccessBean.ClientsBean getClientChoosed() {
        return method.getGson().fromJson(getIntent().getStringExtra(CLIENT_HISTORY_CHOOSED), GsonGetClient.SuccessBean.ClientsBean.class);
    }

    private String getDateChoosed() {
        return getIntent().getStringExtra(DATE);
    }


    public List<ServicesOfShop> getListDataAcrylic() {
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("Manicure", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("Gel Manicure", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("Gel Manicure w/ French Tip", 35, "http//...");
        ServicesOfShop Spa_Pedicure = new ServicesOfShop("Spa Pedicure (Sea Salt & Hot Towel)", 22, "http//...");
        ServicesOfShop Spa_Pedicure_Gel_Polish = new ServicesOfShop("Spa Pedicure w/ Gel Polish (Sea Salt & Hot Towel)", 10, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);
        listService.add(Spa_Pedicure);
        listService.add(Spa_Pedicure_Gel_Polish);
        return listService;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            case R.id.txt_extra:
                showDialogUpdateExtra();
                break;
            default:
                break;
        }
    }

    private Dialog mChangePassDialog;

    private void showDialogUpdateExtra() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        mChangePassDialog = new Dialog(ctw);
        mChangePassDialog.setContentView(R.layout.update_extra_dialog);
        mChangePassDialog.setTitle("Update extra");

        Button btnUpdate = (Button) mChangePassDialog.findViewById(R.id.btnUpdate);
        Button btnCancel = (Button) mChangePassDialog.findViewById(R.id.btnCancel);
        final EditText txtExtra = (EditText) mChangePassDialog.findViewById(R.id.txt_extra);

//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvExtra.setText(txtExtra.getText()+"$");
//                mChangePassDialog.dismiss();
//            }
//        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangePassDialog.dismiss();
            }
        });
        mChangePassDialog.show();
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
        menu.findItem(R.id.action_manage_staff).setVisible(true);
        menu.findItem(R.id.action_customer_service_history).setVisible(true);
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
            case R.id.action_manage_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MANAGE_STAFF);
                return true;
            case R.id.action_customer_service_history:
                mViewManager.setView(ViewManager.VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
                return true;
            case R.id.action_staff_info:
                mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
