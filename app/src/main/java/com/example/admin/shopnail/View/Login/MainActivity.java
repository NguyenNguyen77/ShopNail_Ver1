package com.example.admin.shopnail.View.Login;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.example.admin.shopnail.Presenter.LoginPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.ILoginView;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;


public class MainActivity extends Activity implements View.OnClickListener, ILoginView {
    private Button btnExit;
    private Button btnLogin;
    private Button btnMakeAppointment;
    private LoginPresenter mLoginPersenter = new LoginPresenter(this, this);
    private String mUserName = "";
    private String mPassword = "";
    private ProgressDialog mProgressDialog;
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

        btnMakeAppointment.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        mViewManager.setActivity(this);
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
            case R.id.btn_exit:
                mViewManager.finishListActivity();
                break;
            default:
                break;
        }
    }

    private void showLoginDialog() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        final Dialog login = new Dialog(ctw);
        login.setContentView(R.layout.login_dialog);
        login.setTitle(R.string.login);
        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) login.findViewById(R.id.txtPassword);

        txtUsername.addTextChangedListener(new TextWatcher() {
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
                mUserName = txtUsername.getText().toString().trim();
                mPassword = txtPassword.getText().toString().trim();
                if (mLoginPersenter.checkLogin(mUserName, mPassword)) { //Need to check more condition for Username&PWD
                    login.dismiss();
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
        login.show();
    }

    @Override
    public void onLoginResult(boolean result) {
        if (result) {
            mProgressDialog.cancel();
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
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                mViewManager.finishListActivity();
                return false;
            default:
                return false;
        }
    }
}
