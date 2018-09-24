package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class MyCustomerActivity extends Activity implements View.OnClickListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);

        btn_back = findViewById(R.id.btn_go_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
            }
        });

        mViewManager.setActivity(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_back:
                Toast.makeText(this, "click select service", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
