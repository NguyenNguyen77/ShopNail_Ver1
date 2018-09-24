package com.example.admin.shopnail.View.ManageStaff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.admin.shopnail.Adapter.ManageStaffAdapter;
import com.example.admin.shopnail.Model.ManageStaff.ManageStaff;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class ManageStaffActivity extends Activity implements View.OnClickListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    ListView listManageStaff;
    ManageStaffAdapter manageStaffAdapter = null;
    List<ManageStaff> mList = new ArrayList<ManageStaff>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff);

        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MANAGE_STAFF);

        listManageStaff = findViewById(R.id.listStatusService);
        ManageStaff staffA = new ManageStaff(true,"cat toc",false,false);
        ManageStaff staffB = new ManageStaff(true,"cat toc",false,false);
        ManageStaff staffC = new ManageStaff(true,"cat toc",false,false);
        ManageStaff staffD = new ManageStaff(true,"cat toc",false,false);

        mList.add(staffA);
        mList.add(staffB);
        mList.add(staffC);
        mList.add(staffD);

        manageStaffAdapter = new ManageStaffAdapter(ManageStaffActivity.this,mList);

        listManageStaff.setAdapter(manageStaffAdapter);

        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View view) {

    }
}
