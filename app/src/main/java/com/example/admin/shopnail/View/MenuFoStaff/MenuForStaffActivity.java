package com.example.admin.shopnail.View.MenuFoStaff;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.MyApplication;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class MenuForStaffActivity extends Activity implements View.OnClickListener, NetworkReceiver.ConnectivityReceiverListener {

    private Button btnSelectService;
    private Button btnMyCustomer;
    private Button btnCustomerServiceHistory;
    private Button btnStaffInfo;
    private Button btnLogout;
    private Button btnManageStaff;

    protected ViewManager mViewManager = ViewManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_staff);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MENU_FOR_STAFF);


        btnSelectService = (Button) findViewById(R.id.btn_select_service);
        btnMyCustomer = (Button) findViewById(R.id.btn_my_customer);
        btnManageStaff = (Button) findViewById(R.id.btn_manage_staff);
        btnCustomerServiceHistory = (Button) findViewById(R.id.btn_customer_service_history);
        btnStaffInfo = (Button) findViewById(R.id.btn_staff_info);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        btnSelectService.setOnClickListener(this);
        btnMyCustomer.setOnClickListener(this);
        btnManageStaff.setOnClickListener(this);
        btnCustomerServiceHistory.setOnClickListener(this);
        btnStaffInfo.setOnClickListener(this);
        btnLogout.setOnClickListener(this);


        mViewManager.checkConnection();

        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_service:
                tranferToLoginForCustomer();
                break;
            case R.id.btn_my_customer:
                tranferToMyCustomer();
                break;
            case R.id.btn_manage_staff:
                tranferToManageStaff();
                break;
            case R.id.btn_customer_service_history:
                transferToCustomerServiceHistory();
                break;
            case R.id.btn_staff_info:
                transferToStaffInformation();
                break;
            case R.id.btn_logout:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.finishListActivity();
    }

    private void tranferToLoginForCustomer() {
        mViewManager.setView(ViewManager.VIEW_KEY.LOGIN_FOR_CUSTOMER);
    }

    private void tranferToManageStaff() {
        mViewManager.setView(ViewManager.VIEW_KEY.MANAGE_STAFF);
    }

    private void transferToStaffInformation() {
        mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
    }

    private void transferToCustomerServiceHistory() {
        mViewManager.setView(ViewManager.VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
    }

    private void tranferToMyCustomer() {
        mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

}
