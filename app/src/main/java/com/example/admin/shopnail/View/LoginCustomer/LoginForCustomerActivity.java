package com.example.admin.shopnail.View.LoginCustomer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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

    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_for_customer);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.SELECT_CUSTOMER_SERVICE);

        btnNewCustomer = findViewById(R.id.btn_new_customer);
        btnOldCustomer = findViewById(R.id.btn_old_customer);
        btnBack = findViewById(R.id.btn_go_back);

        btnNewCustomer.setOnClickListener(this);
        btnOldCustomer.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        mViewManager.setActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_customer:
                ShowCreateNewCutomer();
                break;
            case R.id.btn_old_customer:
                ShowLoginForOldCustomer();
                break;
            case R.id.btn_go_back:
                Toast.makeText(this, "click go back", Toast.LENGTH_SHORT).show();
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getActionBar().setIcon(R.drawable.ic_menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_menu_for_staff).setVisible(true);
        menu.findItem(R.id.action_my_customer).setVisible(true);
        menu.findItem(R.id.action_manage_staff).setVisible(true);
        menu.findItem(R.id.action_customer_service_history).setVisible(true);
        menu.findItem(R.id.action_staff_info).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_for_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);
                return true;
            case R.id.action_my_customer:
                mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
                return true;
            case R.id.action_manage_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MANAGE_STAFF);
                return true;
            case R.id.action_customer_service_history:
                mViewManager.setView(ViewManager.VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
                return true;
            case R.id.action_staff_info:
                mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        txtPhoneCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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
