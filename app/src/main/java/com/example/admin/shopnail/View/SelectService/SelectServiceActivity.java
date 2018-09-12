package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class SelectServiceActivity extends Activity{

    private ViewManager mViewManager = ViewManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_select_service);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        mViewManager.setActivity(this);
    }
}
