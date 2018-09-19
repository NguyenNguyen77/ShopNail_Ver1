package com.example.admin.shopnail.View.BookAppointment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class BookAppointmentActivity extends Activity implements View.OnClickListener {
    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtnBack;
    private Button mBtnSubmit;
    private EditText mEtCustomerName;
    private EditText mEtCustomerPhone;
    private Spinner mSSelectStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.BOOK_APPOINTMENT);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);


        mBtnSubmit.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                break;
            case R.id.btn_back:
                mViewManager.handleBackScreen();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mViewManager.handleBackScreen();
    }

}
