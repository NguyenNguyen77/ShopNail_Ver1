package com.example.admin.shopnail.View.OrderManagermentOnline;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;
import com.example.admin.shopnail.Presenter.LoginPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.Login.ILoginView;
import com.example.admin.shopnail.View.Login.MainActivity;

public class OrderManagementOnlineActivity extends Activity implements View.OnClickListener,ILoginView, NetworkReceiver.ConnectivityReceiverListener {

    Button btn_cancel_booking, btn_manager_point, btn_back;
    private String mUserName = "";
    private String mPassword = "";
    private ProgressDialog mProgressDialog;
    private LoginPresenter mLoginPersenter = new LoginPresenter(this, this);
    protected ViewManager mViewManager = ViewManager.getInstance();
    BaseMethod method = new BaseMethod();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);

        btn_cancel_booking = findViewById(R.id.btn_cancel_booking);
        btn_manager_point = findViewById(R.id.btn_manager_point);
        btn_back = findViewById(R.id.btn_back);

        btn_cancel_booking.setOnClickListener(this);
        btn_manager_point.setOnClickListener(this);

        //Toast.makeText(getApplicationContext(), "activity_order_manage", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_booking:
                showLoginDialogOutSide();
                break;
            case R.id.btn_manager_point:
//                showLoginDialog();
                break;
            case R.id.btn_back:
                mViewManager.finishListActivity();
                break;
            default:
                break;
        }

    }

    Dialog login;

    private void showLoginDialogOutSide () {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        login = new Dialog(ctw);
        login.setContentView(R.layout.login_out_side);
        login.setTitle(R.string.login);
        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
        final Drawable mBackgroundColor = txtUsername.getBackground();

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtUsername.setBackground(mBackgroundColor);
            }

            @Override
            public void afterTextChanged(Editable text) {
//                mTextSizeAfter = text.length();
//                if (mTextSizeAfter > mTextSizeBefore) {
//                    if ((text.length() == 3) || (text.length() == 7)) {
//                        text.append('-');
//                    }
//                }
//                mTextSizeBefore = mTextSizeAfter;
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = txtUsername.getText().toString().trim();
                // ================ Check vailidate username and password START
                if (mUserName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert user name", Toast.LENGTH_LONG).show();
                    txtUsername.setBackgroundResource(R.drawable.bordertextview);
                    return;
                }
                // ================ Check vailidate username and password END
                if (mUserName != null) { //Need to check more condition for Username&PWD
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.requestLoginOutSide(mUserName);
                } else {
                    Toast.makeText(OrderManagementOnlineActivity.this, R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
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
        if (result) {
            mProgressDialog.cancel();
            login.dismiss();
            Toast.makeText(OrderManagementOnlineActivity.this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
            mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);  // Change to next screen
        } else {
            dismissProgress();
            Toast.makeText(OrderManagementOnlineActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dismissProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void onLoginOutSideSuccess(GsonLoginOutSide mGsonLoginOutSide) {
        mProgressDialog.cancel();
        Toast.makeText(OrderManagementOnlineActivity.this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
        mViewManager.setView(ViewManager.VIEW_KEY.CANCEL_APPOINTMENT, method.getGson().toJson(mGsonLoginOutSide));
//        mViewManager.setView(ViewManager.VIEW_KEY.ORDER_MANAGEMENT_ONLINE);
    }

    @Override
    public void onLoginOutSideFail(String login_fail) {
        dismissProgress();
        Toast.makeText(OrderManagementOnlineActivity.this, R.string.login_outside_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                mViewManager.finishListActivity();
                return false;
            default:
                return false;
        }
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }
}
