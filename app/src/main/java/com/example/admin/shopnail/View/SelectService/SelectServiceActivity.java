package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.admin.shopnail.Adapter.SelectServiceAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.SelectServicePresenter.SelectServicePresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class SelectServiceActivity extends Activity implements View.OnClickListener{

    private ViewManager mViewManager = ViewManager.getInstance();
    public SelectServicePresenter mSerlectServicePresenter = new SelectServicePresenter();
    Button btnBack;
    Spinner spinnerCategory;
    GridView gridSelectService;
    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_select_service);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        spinnerCategory = findViewById(R.id.spinnerCategoty);
        gridSelectService = findViewById(R.id.gridSelectService);
        btnBack = findViewById(R.id.btn_go_back);



        List<ServicesOfShop> mlistService = mSerlectServicePresenter.getListData();
        SelectServiceAdapter selectServiceAdapter = new SelectServiceAdapter(this,mlistService);
        gridSelectService.setAdapter(selectServiceAdapter);


        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(SelectServiceActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        mViewManager.setActivity(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
            }
        });
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
}

