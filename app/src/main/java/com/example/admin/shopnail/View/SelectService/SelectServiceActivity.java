package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.CategoryAdapter;
import com.example.admin.shopnail.Adapter.SelectServiceAdapter;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonProductsByCategory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.SelectServicePresenter.ISelectServicePresenter;
import com.example.admin.shopnail.Presenter.SelectServicePresenter.SelectServicePresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SelectServiceActivity extends Activity implements ISelectServiceView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    SelectServicePresenter mSerlectServicePresenter = new SelectServicePresenter(this, this);
    Button btnBack;
    Button btnViewcart;
    Spinner spinnerCategory;
    GridView gridSelectService;
    private ProgressDialog mProgressDialog;
    //    ArrayAdapter<String> adapterCategory;
    SelectServiceAdapter selectServiceAdapter = null;
    List<GsonProductsByCategory.SuccessBean.DataBean> mList = null;
    JSONArray jsonArray = new JSONArray();
//    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_select_service);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        gridSelectService = findViewById(R.id.gridSelectService);
        btnBack = findViewById(R.id.btn_go_back);
        btnViewcart = findViewById(R.id.btn_view_cart);
        btnBack.setOnClickListener(this);
        btnViewcart.setOnClickListener(this);
        // request server category add start vinhcn 25/09/2018
        mSerlectServicePresenter.RequestCategory();
        // request server category add end vinhcn 25/09/2018
//        loadListDataAcrylic();
        gridSelectService.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);


        // comment out here 25/09/2018
//        adapterCategory = new ArrayAdapter<String>(SelectServiceActivity.this,
//                android.R.layout.simple_spinner_item, paths);
//        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCategory.setAdapter(adapterCategory);
        // comment out here 25/09/2018


        spinnerCategory.setOnItemSelectedListener(this);

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
        mViewManager.finishActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_view_cart:
                mViewManager.setView(ViewManager.VIEW_KEY.VIEW_CART, jsonArray);
                break;
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String category = adapterView.getItemAtPosition(i).toString();
//        if (category == "Acrylic") {
//            loadListDataAcrylic();
//        } else if (category == "Natural Nails") {
//            loadListNaturalNails();
//        } else {
//            loadListWaxingFacial();
//        }
        showInprogressDialog();
        mSerlectServicePresenter.requestProduct(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    private List<ServicesOfShop> loadListNaturalNails() {
//        mList = mSerlectServicePresenter.getListNaturalNails();
//        selectServiceAdapter = new SelectServiceAdapter(this, mList);
//        selectServiceAdapter.notifyDataSetChanged();
//        gridSelectService.setAdapter(selectServiceAdapter);
//        return mList;
//    }

//    private List<ServicesOfShop> loadListWaxingFacial() {
//        mList = mSerlectServicePresenter.getListWaxingFacial();
//        selectServiceAdapter = new SelectServiceAdapter(this, mList);
//        selectServiceAdapter.notifyDataSetChanged();
//        gridSelectService.setAdapter(selectServiceAdapter);
//        return mList;
//    }
//
//    private List<ServicesOfShop> loadListDataAcrylic() {
//        mList = mSerlectServicePresenter.getListDataAcrylic();
//        selectServiceAdapter = new SelectServiceAdapter(this, mList);
//        selectServiceAdapter.notifyDataSetChanged();
//        gridSelectService.setAdapter(selectServiceAdapter);
//        return mList;
//    }

    @Override
    public void setCategoryAdapter(CategoryAdapter adapterCategory) {
//        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
    }

    @Override
    public void setProductsByCategoryAdapter(SelectServiceAdapter selectServiceAdapter) {
        gridSelectService.setAdapter(selectServiceAdapter);
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }


    @Override
    public void addJsonArrayService(boolean isChecked, String name, int id, String price, String format) {
        try {
            if (isChecked) {
                JSONObject mJsonObject = new JSONObject();
                mJsonObject.put(KeyManager.PRODUC_ID, id);
                mJsonObject.put(KeyManager.NAME, name);
                mJsonObject.put(KeyManager.PRICE, price);
                mJsonObject.put(KeyManager.TIME_ORDER, format);
                jsonArray.put(mJsonObject);
            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject mJsonObject = jsonArray.getJSONObject(i);
                    if (id == mJsonObject.getInt(KeyManager.PRODUC_ID)) {
                        jsonArray.remove(i);
                        break;
                    }

                }
            }
            Log.d(KeyManager.VinhCNLog, jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public JSONArray getArrayChecked() {
        return jsonArray;
    }

    private void showInprogressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
        mProgressDialog = new ProgressDialog(this);   // Show inprogress dialog: please wait
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.show();
    }
}

