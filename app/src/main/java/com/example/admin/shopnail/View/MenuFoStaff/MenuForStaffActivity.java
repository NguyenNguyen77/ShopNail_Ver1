package com.example.admin.shopnail.View.MenuFoStaff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class MenuForStaffActivity extends Activity implements View.OnClickListener {

    private Button btnSelectService;
    private Button btnMyCustomer;
    private Button btnCustomerServiceHistory;
    private Button btnStaffInfo;
    private Button btnLogout;

    private ViewManager mViewManager = new ViewManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_staff);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MENU_FOR_STAFF);

        btnSelectService = (Button) findViewById(R.id.btn_select_service);
        btnMyCustomer = (Button) findViewById(R.id.btn_my_customer);
        btnCustomerServiceHistory = (Button) findViewById(R.id.btn_customer_service_history);
        btnStaffInfo = (Button) findViewById(R.id.btn_staff_info);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        btnSelectService.setOnClickListener(this);
        btnMyCustomer.setOnClickListener(this);
        btnCustomerServiceHistory.setOnClickListener(this);
        btnStaffInfo.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_service:
                Toast.makeText(this,"click select service",Toast.LENGTH_SHORT).show();
                tranferToSelectService();
                break;
            case R.id.btn_my_customer:

                break;
            case R.id.btn_customer_service_history:

                break;
            case R.id.btn_staff_info:
                transferToStaffInformation();
                break;
            case R.id.btn_logout:

                break;
            default:
                break;
        }
    }

    private void tranferToSelectService() {
        mViewManager.setView(ViewManager.VIEW_KEY.SELECT_SERVICE);
    }

    private void transferToStaffInformation() {
        mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
    }
}
