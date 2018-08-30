package com.example.admin.shopnail.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.shopnail.R;

public class MenuForStaffActivity extends Activity implements View.OnClickListener {

    private Button btnSelectService;
    private Button btnMyCustomer;
    private Button btnCustomerServiceHistory;
    private Button btnStaffInfo;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_staff);


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_service:

                break;
            case R.id.btn_my_customer:

                break;
            case R.id.btn_customer_service_history:

                break;
            case R.id.btn_staff_info:

                break;
            case R.id.btn_logout:

                break;
            default:
                break;
        }
    }
}
