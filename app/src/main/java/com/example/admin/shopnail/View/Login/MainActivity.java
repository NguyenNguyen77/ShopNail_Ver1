package com.example.admin.shopnail.View.Login;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.MyApplication;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;
import com.example.admin.shopnail.Presenter.LoginPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;
import static com.example.admin.shopnail.R.drawable.bordertextview;


public class MainActivity extends Activity implements View.OnClickListener, ILoginView, NetworkReceiver.ConnectivityReceiverListener {
    private Button btnExit;
    private Button btnLogin;
    private Button btnMakeAppointment;
    private Button btnCancelAppointment;
    private LoginPresenter mLoginPersenter = new LoginPresenter(this, this);
    private String mUserName = "";
    private String mPassword = "";
    private ProgressDialog mProgressDialog;
    BaseMethod method = new BaseMethod();
    protected ViewManager mViewManager = ViewManager.getInstance();

    private int mTextSizeBefore = 0;
    private int mTextSizeAfter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.LOGIN);

        btnExit = (Button) findViewById(R.id.btn_exit);
        btnLogin = (Button) findViewById(R.id.btn_login_for_staff);
        btnMakeAppointment = (Button) findViewById(R.id.btn_make_appointment_online);
        btnCancelAppointment = (Button) findViewById(R.id.btn_cancel_appointment_online);
        if (isInternetOn()) {
//            Toast.makeText(getApplicationContext(),"da ket noi internet",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please check internet connection and try again", Toast.LENGTH_LONG).show();
        }
        btnMakeAppointment.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnCancelAppointment.setOnClickListener(this);
        mViewManager.setActivity(this);
//        mViewManager.checkConnection();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_for_staff:
                showLoginDialog();
                break;
            case R.id.btn_make_appointment_online:
                mViewManager.setView(ViewManager.VIEW_KEY.BOOK_APPOINTMENT);
                break;
            case R.id.btn_cancel_appointment_online:
                showLoginDialogOutSide();
//                mViewManager.setView(ViewManager.VIEW_KEY.CANCEL_APPOINTMENT);
                break;
            case R.id.btn_exit:
                mViewManager.finishListActivity();
                break;
            default:
                break;
        }
    }

    Dialog login;


    private void showLoginDialogOutSide() {
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
                if (mUserName!=null) { //Need to check more condition for Username&PWD
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mLoginPersenter.requestLoginOutSide(mUserName);
                } else {
                    Toast.makeText(MainActivity.this, R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
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


    private void showLoginDialog() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        login = new Dialog(ctw);
        login.setContentView(R.layout.login_dialog);
        login.setTitle(R.string.login);
        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) login.findViewById(R.id.txtPassword);
        final TextView txtForgetPassword = login.findViewById(R.id.txt_forget_password);

        final Drawable mBackgroundColor = txtUsername.getBackground();
        final Drawable mBackgroundColorpass = txtPassword.getBackground();

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
                mTextSizeAfter = text.length();
                if (mTextSizeAfter > mTextSizeBefore) {
                    if ((text.length() == 3) || (text.length() == 7)) {
                        text.append('-');
                    }
                }
                mTextSizeBefore = mTextSizeAfter;
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtPassword.setBackground(mBackgroundColorpass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = txtUsername.getText().toString().trim();
                mPassword = txtPassword.getText().toString().trim();
                // ================ Check vailidate username and password START
                if (mUserName.isEmpty() && mPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert user name and password ", Toast.LENGTH_LONG).show();
                    txtUsername.setBackgroundResource(R.drawable.bordertextview);
                    txtPassword.setBackgroundResource(R.drawable.bordertextview);
                    return;
                }
                if (mUserName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert user name ", Toast.LENGTH_LONG).show();
                    txtUsername.setBackgroundResource(R.drawable.bordertextview);
                    return;
                }
                if (mPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert password", Toast.LENGTH_LONG).show();
                    txtPassword.setBackgroundResource(R.drawable.bordertextview);
                    return;
                }
                // ================ Check vailidate username and password END
                if (mLoginPersenter.checkLogin(mUserName, mPassword)) { //Need to check more condition for Username&PWD
//                    login.dismiss();
                    mProgressDialog = new ProgressDialog(login.getContext());   // Show inprogress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    BaseMethod.setDefaults(USER_NAME, mUserName, MainActivity.this);
                    BaseMethod.setDefaults(PASS_WORD, mPassword, MainActivity.this);
                    BaseMethod.setDefaults(USER_ID, String.valueOf(-1), MainActivity.this);
                    mLoginPersenter.sendRequestLogin(mUserName, mPassword);  // Send Username & PWD to persenter: save.
                } else {
                    Toast.makeText(MainActivity.this, R.string.enter_username_pwd, Toast.LENGTH_LONG).show();
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tranferToLResetPassword();
            }
        });
        login.show();
    }

    private void tranferToLResetPassword() {
        mViewManager.setView(ViewManager.VIEW_KEY.RESET_PASSWORD);
    }

    @Override
    public void onLoginResult(boolean result) {
        if (result) {
            mProgressDialog.cancel();
            login.dismiss();
            Toast.makeText(MainActivity.this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
            mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);  // Change to next screen
        } else {
            dismissProgress();
            Toast.makeText(MainActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dismissProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void onLoginOutSideSuccess(GsonLoginOutSide mGsonLoginOutSide) {
        mProgressDialog.cancel();
        Toast.makeText(MainActivity.this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
        mViewManager.setView(ViewManager.VIEW_KEY.CANCEL_APPOINTMENT, method.getGson().toJson(mGsonLoginOutSide));
    }

    @Override
    public void onLoginOutSideFail(String login_fail) {
        dismissProgress();
        Toast.makeText(MainActivity.this, R.string.login_outside_failed, Toast.LENGTH_SHORT).show();
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

    public boolean isInternetOn() {

        boolean checkConnect = false;
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            checkConnect = true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            checkConnect = false;
        }
        return checkConnect;
    }
}

