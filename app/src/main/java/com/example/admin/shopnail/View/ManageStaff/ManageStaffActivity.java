package com.example.admin.shopnail.View.ManageStaff;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.ManageStaffAdapter;
import com.example.admin.shopnail.Model.ManageStaff.ManageStaff;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class ManageStaffActivity extends Activity implements View.OnClickListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    Button btn_back;
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
        btn_back = findViewById(R.id.btn_go_back);
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


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "click go back", Toast.LENGTH_SHORT).show();
                mViewManager.handleBackScreen();
            }
        });
        mViewManager.setActivity(this);
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
        menu.findItem(R.id.action_my_customer).setVisible(true);
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
            case R.id.action_my_customer:
                mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
    }
}
