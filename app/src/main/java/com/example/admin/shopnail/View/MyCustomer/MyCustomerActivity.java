package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.MyCustomerAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class MyCustomerActivity extends Activity implements View.OnClickListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtnBack;
    private Button mBtnUpdateService;
    private Button mBtnCancelService;
    private ListView mLvMyCustomerList;

    List<ServicesOfShop> listService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);

        mBtnBack = (Button) findViewById(R.id.btn_go_back);
        mBtnUpdateService = (Button) findViewById(R.id.btn_update_service);
        mBtnCancelService = (Button) findViewById(R.id.btn_cancel_service);
        mLvMyCustomerList = (ListView)findViewById(R.id.list_my_customer);


        listService = getListDataAcrylic();
        MyCustomerAdapter myCustomerAdapter = new MyCustomerAdapter(getApplicationContext(),listService);
        mLvMyCustomerList.setAdapter(myCustomerAdapter);

        mBtnBack.setOnClickListener(this);
        mBtnUpdateService.setOnClickListener(this);
        mBtnCancelService.setOnClickListener(this);
        mViewManager.setActivity(this);

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
