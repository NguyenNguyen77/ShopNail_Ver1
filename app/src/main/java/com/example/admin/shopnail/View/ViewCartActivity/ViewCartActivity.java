package com.example.admin.shopnail.View.ViewCartActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class ViewCartActivity extends Activity {

    private ViewManager mViewManager = ViewManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_view_cart);
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.VIEW_CART);


        mViewManager.setActivity(this);
    }
}
