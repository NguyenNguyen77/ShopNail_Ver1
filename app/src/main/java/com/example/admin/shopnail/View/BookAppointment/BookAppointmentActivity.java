package com.example.admin.shopnail.View.BookAppointment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.BOOK_APPOINTMENT);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mEtCustomerName = (EditText) findViewById(R.id.et_customer_name);
        mEtCustomerPhone = (EditText) findViewById(R.id.et_customer_phone);

        mBtnSubmit.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mBtnSubmit.setEnabled(false);
        mBtnSubmit.setClickable(false);


        mEtCustomerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableSubmitButton();
            }

            @Override
            public void afterTextChanged(Editable text) {
                mTextSizeAfter = text.length();
                if (mTextSizeAfter > mTextSizeBefore) {
                    if ((text.length() == 3) || (text.length() == 7)) {
                        text.append('-');
                    }
                }
                mTextSizeBefore = mTextSizeAfter;
            }
        });

        mEtCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableSubmitButton();
            }

            @Override
            public void afterTextChanged(Editable text) {
            }
        });

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

    private void reqBookAppointment() {
        String customerName = mEtCustomerName.getText().toString().trim();
        String customerPhone = mEtCustomerPhone.getText().toString().trim();

    }

    private void checkEnableSubmitButton() {
        int phoneSize = mEtCustomerPhone.getText().toString().trim().length();
        int nameSize = mEtCustomerName.getText().toString().trim().length();

        if (phoneSize == 12 & nameSize > 0) {
            mBtnSubmit.setEnabled(true);
            mBtnSubmit.setClickable(true);
        } else {
            mBtnSubmit.setEnabled(false);
            mBtnSubmit.setClickable(false);
        }
    }
}
