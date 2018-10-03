package com.example.admin.shopnail.View.Login;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class ResetPasswordActivity extends Activity implements View.OnClickListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtnResetPass;
    private Button mBtnBack;
    private EditText mEtPhone;

    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.LOGIN);
        mViewManager.setActivity(this);

        mEtPhone = (EditText) findViewById(R.id.btn_phone_num);
        mBtnResetPass = (Button) findViewById(R.id.btn_reset_password);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnResetPass.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);

        mEtPhone.addTextChangedListener(new TextWatcher() {
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

    }

    @Override
    public void onBackPressed() {
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password:

                break;
            case R.id.btn_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    private void checkEnableSubmitButton() {
        int phoneSize = mEtPhone.getText().toString().trim().length();

        if (phoneSize == 12) {
            mBtnResetPass.setEnabled(true);
        } else {
            mBtnResetPass.setEnabled(false);
        }
    }

}
