package com.example.admin.shopnail.View.ViewCartActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.ViewProductPresenter.ViewProductPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends Activity implements View.OnClickListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    EditText edtExtra;
    ListView listCart = null;
    List<ServicesOfShop> mList = null;
    ViewProductAdapter viewProductAdapter;
    ViewProductPresenter mViewProductPresenter = null;
    private Button mBtnConfirm;
    private Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_view_product);
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.VIEW_CART);

        edtExtra = findViewById(R.id.edt_Extra);
        listCart = findViewById(R.id.listView);
        mBtnConfirm = (Button) findViewById(R.id.btn_view_cart);
        mBtnBack = (Button) findViewById(R.id.btn_go_back);

        mBtnConfirm.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);

        mViewProductPresenter = new ViewProductPresenter();
        List<ServicesOfShop> listService = mViewProductPresenter.getListProduct();
        viewProductAdapter = new ViewProductAdapter(this,listService);
        listCart.setAdapter(viewProductAdapter);


        edtExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtExtra.setCursorVisible(true);
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
        menu.findItem(R.id.action_my_customer).setVisible(true);
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
            case R.id.action_my_customer:
                mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_view_cart:
                break;
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                break;
            default:
                break;
        }
    }
}
