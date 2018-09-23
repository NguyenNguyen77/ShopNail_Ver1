package com.example.admin.shopnail.View.ManageStaff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class ManageStaffActivity extends Activity implements View.OnClickListener {
    protected ViewManager mViewManager = ViewManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff);

        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MANAGE_STAFF);




        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View view) {

    }
}
