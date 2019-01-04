package com.example.admin.shopnail.View.LoginCustomer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
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

import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Presenter.AccountCustomerPresenter.AccountCustomerPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

public class LoginForCustomerActivity extends Activity implements View.OnClickListener, ILoginForCustomerView, NetworkReceiver.ConnectivityReceiverListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    private Button btnNewCustomer;
    private Button btnOldCustomer;
    private Button btnBack;
    AccountCustomerPresenter mLoginPersenter = new AccountCustomerPresenter(this, this);
    private String mNameCustomer = "";
    private String mPhoneCustomer = "";
    private String mEmailCustomer = "";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
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
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        mViewManager.setActivity(this);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
//        mViewManager.handleBackScreen();
        mViewManager.setViewKey(ViewManager.VIEW_KEY.MENU_FOR_STAFF);
        mViewManager.finishActivity(this);
        super.onBackPressed();
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
        login = new Dialog(ctw);
        login.setContentView(R.layout.create_account_for_customer_dialog);
        login.setTitle(R.string.new_customer);

        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);

        final EditText txtNameCustomer = (EditText) login.findViewById(R.id.txtNameCustomer);
        final EditText txtPhoneCustomer = (EditText) login.findViewById(R.id.txtPhoneCustomer);
        final EditText txtEmailCustomer = (EditText) login.findViewById(R.id.txtEmailCustomer);

        final Drawable mBackgroundColor = txtNameCustomer.getBackground();
        final Drawable mBackgroundColorpass = txtPhoneCustomer.getBackground();
        final Drawable mBackgroundColorEmail = txtEmailCustomer.getBackground();
        txtPhoneCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtPhoneCustomer.setBackground(mBackgroundColor);
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
                if (mTextSizeBefore == 12) {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_ok));
                } else {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                }
            }
        });

        txtNameCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtNameCustomer.setBackground(mBackgroundColorpass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtEmailCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtEmailCustomer.setBackground(mBackgroundColorEmail);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Check validate email START
                mEmailCustomer = txtEmailCustomer.getEditableText().toString().trim();
                if (mEmailCustomer.matches(emailPattern) && mEmailCustomer.length() > 0) {
                    txtEmailCustomer.setTextColor(getResources().getColor(R.color.email_ok));
                    txtEmailCustomer.setBackground(mBackgroundColorEmail);
                } else {
                    txtEmailCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                    txtEmailCustomer.setBackgroundResource(R.drawable.bordertextview);
                }
                // Check validate email END
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInvalid = false;
//                if(txtNameCustomer.getText().toString() != null){
                mNameCustomer = txtNameCustomer.getText().toString().trim();
                mPhoneCustomer = txtPhoneCustomer.getText().toString();
                mEmailCustomer = txtEmailCustomer.getText().toString();

                // ================ Check vailidate username and password START
//                if (mNameCustomer.isEmpty() && mPhoneCustomer.isEmpty() && mEmailCustomer.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please insert name and phone customer ", Toast.LENGTH_LONG).show();
//                    txtNameCustomer.setBackgroundResource(R.drawable.bordertextview);
//                    txtPhoneCustomer.setBackgroundResource(R.drawable.bordertextview);
//                    txtEmailCustomer.setBackgroundResource(R.drawable.bordertextview);
//                    return;
//                }
                if (mNameCustomer.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert user name customer ", Toast.LENGTH_LONG).show();
                    txtNameCustomer.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }

                if (mEmailCustomer.isEmpty()) {
                    if (!isInvalid) {
                        Toast.makeText(getApplicationContext(), "Please insert email customer", Toast.LENGTH_LONG).show();
                    }
                    txtEmailCustomer.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }

                if (mPhoneCustomer.isEmpty()) {
                    if (!isInvalid) {
                        Toast.makeText(getApplicationContext(), "Please insert phone number customer", Toast.LENGTH_LONG).show();
                    }
                    txtPhoneCustomer.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }
                // ================ Check vailidate username and password END
                int phoneSize = txtPhoneCustomer.getText().toString().trim().length();
                if (phoneSize != 12) {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                    isInvalid = true;
                }
                String emailText = txtEmailCustomer.getEditableText().toString().trim();
                if (!(emailText.matches(emailPattern) && emailText.length() > 0)) {
                    txtEmailCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                    isInvalid = true;
                }

                if (!isInvalid) { //Need to check more condition for Username&PWD
//                    login.dismiss();
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.createAccountForCustomer(mNameCustomer, mPhoneCustomer, mEmailCustomer);  // Send Username & PWD to persenter: save.
                }
//                else {
//                    Toast.makeText(getApplicationContext(), R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
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

    Dialog login;

    private void ShowLoginForOldCustomer() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        login = new Dialog(ctw);
        login.setContentView(R.layout.login_account_for_customer_dialog);
        login.setTitle(R.string.old_customer);

        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);

        final EditText txtPhoneCustomer = (EditText) login.findViewById(R.id.txtPhoneCustomer);
        final Drawable mBackgroundColor = txtPhoneCustomer.getBackground();

        txtPhoneCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtPhoneCustomer.setBackground(mBackgroundColor);
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
                if (mTextSizeBefore == 12) {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_ok));
                } else {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneCustomer = txtPhoneCustomer.getText().toString();
                boolean isInvalid = false;
                // ================ Check vailidate username and password START
                if (mPhoneCustomer.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert phone customer", Toast.LENGTH_LONG).show();
                    txtPhoneCustomer.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }
                // ================ Check vailidate username and password END
                int phoneSize = txtPhoneCustomer.getText().toString().trim().length();
                if (phoneSize != 12) {
                    txtPhoneCustomer.setTextColor(getResources().getColor(R.color.email_failed));
                    isInvalid = true;
                }
                // ================ Check vailidate username and password END
                if (!isInvalid) { //Need to check more condition for Username&PWD
                    login.dismiss();
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.sendRequestLoginForCustomer(mPhoneCustomer);  // Send Username & PWD to persenter: save.
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
    public void onLoginResult(boolean result, String msg) {
        mProgressDialog.cancel();
        if (result) {
            login.dismiss();
            mViewManager.setView(ViewManager.VIEW_KEY.SELECT_SERVICE);  // Change to next screen
            if (!msg.equals("")) {
                Toast.makeText(LoginForCustomerActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        } else {
            if (!msg.equals("")) {
                Toast.makeText(LoginForCustomerActivity.this, msg, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(LoginForCustomerActivity.this, getResources().getString(R.string.login_customer_fail), Toast.LENGTH_LONG).show();
            }
        }
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }

}
