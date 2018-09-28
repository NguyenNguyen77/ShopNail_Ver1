package com.example.admin.shopnail.View.MyCustomer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.Button;
import android.widget.Toast;
>>>>>>> a9585c2591b4e7aa6f159ac193c2494f7957aa71

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class MyCustomerActivity extends Activity implements View.OnClickListener {

<<<<<<< HEAD
    private ViewManager mViewManager = ViewManager.getInstance();
=======
    protected ViewManager mViewManager = ViewManager.getInstance();
    Button btn_back;
>>>>>>> a9585c2591b4e7aa6f159ac193c2494f7957aa71

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my_customer);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.MY_CUSTOMER);

<<<<<<< HEAD
=======
        btn_back = findViewById(R.id.btn_go_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
            }
        });
>>>>>>> a9585c2591b4e7aa6f159ac193c2494f7957aa71

        mViewManager.setActivity(this);
    }

<<<<<<< HEAD
    @Override
    public void onClick(View view) {

=======

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_back:
                Toast.makeText(this, "click select service", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
>>>>>>> a9585c2591b4e7aa6f159ac193c2494f7957aa71
    }
}
