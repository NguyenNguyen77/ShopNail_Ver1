package com.example.admin.shopnail.View.Login;

import android.app.Activity;
import android.os.Bundle;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class ResetPasswordActivity extends Activity {

    protected ViewManager mViewManager = ViewManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.LOGIN);

        mViewManager.setActivity(this);

    }


}
