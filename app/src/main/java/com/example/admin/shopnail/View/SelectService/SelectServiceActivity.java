package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.os.Bundle;
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

import com.example.admin.shopnail.Adapter.SelectServiceAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.SelectServicePresenter.SelectServicePresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.List;

public class SelectServiceActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    public SelectServicePresenter mSerlectServicePresenter = new SelectServicePresenter();
    Button btnBack;
    Button btnViewcart;
    Spinner spinnerCategory;
    GridView gridSelectService;
    ArrayAdapter<String> adapterCategory = null;
    SelectServiceAdapter selectServiceAdapter = null;
    List<ServicesOfShop> mList = null;
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
        btnViewcart = findViewById(R.id.btn_view_cart);
        loadListDataAcrylic();

        gridSelectService.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);




        adapterCategory = new ArrayAdapter<String>(SelectServiceActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);



        spinnerCategory.setOnItemSelectedListener(this);

        mViewManager.setActivity(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
            }
        });

        btnViewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.setView(ViewManager.VIEW_KEY.VIEW_CART);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String category = adapterView.getItemAtPosition(i).toString();
        if(category == "Acrylic"){
            loadListDataAcrylic();
        }else if (category == "Natural Nails"){
            loadListNaturalNails();
        }else{
            loadListWaxingFacial();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private List<ServicesOfShop> loadListNaturalNails() {
        mList = mSerlectServicePresenter.getListNaturalNails();
        selectServiceAdapter = new SelectServiceAdapter(this,mList);
        selectServiceAdapter.notifyDataSetChanged();
        gridSelectService.setAdapter(selectServiceAdapter);
        return mList;
    }

    private List<ServicesOfShop> loadListWaxingFacial() {
        mList = mSerlectServicePresenter.getListWaxingFacial();
        selectServiceAdapter = new SelectServiceAdapter(this,mList);
        selectServiceAdapter.notifyDataSetChanged();
        gridSelectService.setAdapter(selectServiceAdapter);
        return mList;
    }

    private List<ServicesOfShop> loadListDataAcrylic() {
        mList = mSerlectServicePresenter.getListDataAcrylic();
        selectServiceAdapter = new SelectServiceAdapter(this,mList);
        selectServiceAdapter.notifyDataSetChanged();
        gridSelectService.setAdapter(selectServiceAdapter);
        return mList;
    }
}

