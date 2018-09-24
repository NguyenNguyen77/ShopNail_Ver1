package com.example.admin.shopnail.View.SelectService;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.shopnail.Presenter.AccountCustomerPresenter.AccountCustomerPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

public class LoginForCustomerActivity extends Activity implements View.OnClickListener, ILoginForCustomerView {

    private ViewManager mViewManager = ViewManager.getInstance();
    private Button btnNewCustomer;
    private Button btnOldCustomer;
    private Button btnBack;
    AccountCustomerPresenter mLoginPersenter = new AccountCustomerPresenter(this, this);
    private String mNameCustomer = "";
    private String mPhoneCustomer = "";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_for_customer);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        btnNewCustomer = findViewById(R.id.btn_new_customer);
        btnOldCustomer = findViewById(R.id.btn_old_customer);
        btnBack = findViewById(R.id.btn_go_back);

        btnNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "fuck click create new customer", Toast.LENGTH_SHORT).show();
                ShowCreateNewCutomer();
            }
        });

        btnOldCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "fuck click old customer", Toast.LENGTH_SHORT).show();
                ShowLoginForOldCustomer();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewManager.handleBackScreen();
            }
        });
        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_customer:
//                Toast.makeText(this, "click create new customer", Toast.LENGTH_SHORT).show();
                ShowCreateNewCutomer();
                break;
            case R.id.btn_old_customer:
//                Toast.makeText(this, "click old customer", Toast.LENGTH_SHORT).show();
                ShowLoginForOldCustomer();
                break;
            case R.id.btn_go_back:
                Toast.makeText(this, "click go back", Toast.LENGTH_SHORT).show();
                mViewManager.handleBackScreen();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
    }

    private void ShowCreateNewCutomer() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog login = new Dialog(ctw);
        login.setContentView(R.layout.create_account_for_customer_dialog);
        login.setTitle(R.string.new_customer);

        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);

        final EditText txtNameCustomer = (EditText) login.findViewById(R.id.txtNameCustomer);
        final EditText txtPhoneCustomer = (EditText) login.findViewById(R.id.txtPhoneCustomer);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(txtNameCustomer.getText().toString() != null){
                mNameCustomer = txtNameCustomer.getText().toString().trim();
                mPhoneCustomer = txtPhoneCustomer.getText().toString();
                if (mLoginPersenter.checkLoginForCustomer(mPhoneCustomer) && mNameCustomer != null) { //Need to check more condition for Username&PWD
                    login.dismiss();
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.createAccountForCustomer(mNameCustomer, mPhoneCustomer);  // Send Username & PWD to persenter: save.
                } else {
                    Toast.makeText(getApplicationContext(), R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
                }
//                }else {
//                    return;
//                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        login.show();
    }

    private void ShowLoginForOldCustomer() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog login = new Dialog(ctw);
        login.setContentView(R.layout.login_account_for_customer_dialog);
        login.setTitle(R.string.old_customer);

        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);

        final EditText txtPhoneCustomer = (EditText) login.findViewById(R.id.txtPhoneCustomer);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneCustomer = txtPhoneCustomer.getText().toString();
                if (mLoginPersenter.checkLoginForCustomer(mPhoneCustomer)) { //Need to check more condition for Username&PWD
                    login.dismiss();
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.sendRequestLoginForCustomer(mPhoneCustomer);  // Send Username & PWD to persenter: save.
                } else {
                    Toast.makeText(getApplicationContext(), R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        login.show();
    }

    @Override
    public void onLoginResult(boolean result) {
        mProgressDialog.cancel();
        if (result) {
            mViewManager.setView(ViewManager.VIEW_KEY.SELECT_SERVICE);  // Change to next screen
        } else {
            Toast.makeText(LoginForCustomerActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
        }
    }
}
