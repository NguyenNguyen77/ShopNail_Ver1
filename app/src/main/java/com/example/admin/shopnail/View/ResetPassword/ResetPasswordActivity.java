package com.example.admin.shopnail.View.ResetPassword;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Presenter.ResetPassword.ResetPasswordPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

public class ResetPasswordActivity extends Activity implements View.OnClickListener, ResetPasswordView, NetworkReceiver.ConnectivityReceiverListener {

    protected ViewManager mViewManager = ViewManager.getInstance();
    private Button mBtnResetPass;
    private Button mBtnBack;
    private EditText mEtPhone;
    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;
    ResetPasswordPresenter resetPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initView();
    }

    private void initView() {
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
            case R.id.btn_reset_password:
                getPresenter().ResetPassWord(mEtPhone.getText().toString());
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


    private ResetPasswordPresenter getPresenter() {
        if (resetPasswordPresenter == null) {
            resetPasswordPresenter = new ResetPasswordPresenter(this, this);

        }
        return resetPasswordPresenter;
    }

    @Override
    public void showToastResuilt(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }
}
