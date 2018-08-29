package com.example.admin.shopnail;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.shopnail.Presenter.LoginPresenter;
import com.example.admin.shopnail.View.ILoginView;


public class MainActivity extends Activity implements View.OnClickListener, ILoginView {
    private Button btnExit;
    private Button btnLogin;
    private Button btnMakeAppointment;
    private LoginPresenter mLoginPersenter;
    private String mUserName = "";
    private String mPassword = "";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExit = (Button) findViewById(R.id.btn_exit);
        btnLogin = (Button) findViewById(R.id.btn_login_for_staff);
        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_for_staff:
                showLoginDialog();
                break;
            case R.id.btn_make_appointment_online:
                break;
            case R.id.btn_exit:
                finish();
                break;
            default:
                break;
        }
    }

    private void showLoginDialog() {
        final Dialog login = new Dialog(this);
        login.setContentView(R.layout.login_dialog);
        login.setTitle(R.string.login_for_staff);

        Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) login.findViewById(R.id.txtPassword);
        mLoginPersenter = new LoginPresenter(this);

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
            Toast.makeText(MainActivity.this, R.string.login_sucessfull, Toast.LENGTH_LONG).show();
            // Change to next screen
        } else {
            Toast.makeText(MainActivity.this, R.string.login_failed, Toast.LENGTH_LONG).show();
        }
    }
}
