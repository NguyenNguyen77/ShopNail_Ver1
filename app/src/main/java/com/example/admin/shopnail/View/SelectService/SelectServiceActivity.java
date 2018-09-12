package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class SelectServiceActivity extends Activity{

    private ViewManager mViewManager = new ViewManager();
    Spinner spinnerCategory;
    String[] paths = {"Acrylic", "Natural Nails", "Waxing & Facial"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_select_service);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        spinnerCategory = findViewById(R.id.spinnerCategoty);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(SelectServiceActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);


        mViewManager.setActivity(this);
    }
}
