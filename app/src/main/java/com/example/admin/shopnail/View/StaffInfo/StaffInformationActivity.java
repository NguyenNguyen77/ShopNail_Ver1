package com.example.admin.shopnail.View.StaffInfo;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DrawableUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.shopnail.MainActivity;
import com.example.admin.shopnail.Presenter.LoginPresenter;
import com.example.admin.shopnail.Presenter.StaffInformation.StaffInformationPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.ERROR_CODE;

public class StaffInformationActivity extends Activity implements View.OnClickListener, IStaffInformation {
    private ImageView imgAvatar;
    private Button btnChangeAvatar;
    private Button btnChangePassword;
    private Button btnBack;
    private StaffInformationPresenter mStaffInformationPresenter;
    private String mOldPass;
    private String mNewPass;
    private String mConfirmNewPass;
    private ProgressDialog mProgressDialog;
    private Dialog mChangePassDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_information);

        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                com.example.admin.shopnail.View.NailActionBarGenerator.BarType.STAFF_INFO);

        btnChangeAvatar = (Button) findViewById(R.id.btn_change_avatar);
        btnChangePassword = (Button) findViewById(R.id.btn_change_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);


        btnChangeAvatar.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnBack.setOnClickListener(this);

//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
//                getResources(),R.drawable.default_avatar),size.x,size.y,true);
//        imgAvatar.setImageBitmap(bmp);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_avatar:

                break;
            case R.id.btn_change_password:
                showChangePasswordDialog();
                break;
            case R.id.btn_back:

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.search:
//                Toast.makeText(this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onBackPressed() {
//        Toast.makeText(this, R.string.login_sucessfull, Toast.LENGTH_SHORT).show();
//    }


    private void showChangePasswordDialog() {
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_AlertDialog);
        mChangePassDialog = new Dialog(ctw);
        mChangePassDialog.setContentView(R.layout.change_pass_dialog);
        mChangePassDialog.setTitle(R.string.change_password);

        Button btnLogin = (Button) mChangePassDialog.findViewById(R.id.btnOK);
        Button btnCancel = (Button) mChangePassDialog.findViewById(R.id.btnCancel);
        final EditText txtOldPass = (EditText) mChangePassDialog.findViewById(R.id.txtOldPassword);
        final EditText txtNewPass = (EditText) mChangePassDialog.findViewById(R.id.txtNewPassword);
        final EditText txtConfirmNewPass = (EditText) mChangePassDialog.findViewById(R.id.txtConfirmNewPassword);
        mStaffInformationPresenter = new StaffInformationPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOldPass = txtOldPass.getText().toString().trim();
                mNewPass = txtNewPass.getText().toString().trim();
                mConfirmNewPass = txtConfirmNewPass.getText().toString().trim();
                if (checkValidPassword(mOldPass, mNewPass, mConfirmNewPass)) {
                    mProgressDialog = new ProgressDialog(mChangePassDialog.getContext());   // Show in-progress dialog: please wait
                    mProgressDialog.setMessage(getString(R.string.please_wait));
                    mProgressDialog.show();
                    mStaffInformationPresenter.requestChangePassword(mOldPass, mNewPass, mConfirmNewPass);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangePassDialog.dismiss();
            }
        });
        mChangePassDialog.show();
    }

    @Override
    public void onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE resultCode) {
        mChangePassDialog.dismiss();
        if (resultCode == ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK) {
            Toast.makeText(StaffInformationActivity.this, R.string.change_pass_succeccful, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(StaffInformationActivity.this, R.string.change_pass_fail, Toast.LENGTH_LONG).show();
        }
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }

    private boolean checkValidPassword(String mOldPass, String newPass, String mConfirmNewPass) {
        boolean result = true;
        if (mOldPass.equals("")) {
            Toast.makeText(StaffInformationActivity.this, R.string.old_pass_empty, Toast.LENGTH_LONG).show();
            result = false;
        } else if (newPass.equals("")) {
            Toast.makeText(StaffInformationActivity.this, R.string.new_pass_empty, Toast.LENGTH_LONG).show();
            result = false;
        } else if (mConfirmNewPass.equals("")) {
            Toast.makeText(StaffInformationActivity.this, R.string.confirm_new_pass_empty, Toast.LENGTH_LONG).show();
            result = false;
        } else if (!newPass.equals(mConfirmNewPass)) {
            Toast.makeText(StaffInformationActivity.this, R.string.new_pass_notmatch, Toast.LENGTH_LONG).show();
            result = false;
        }
        return result;
    }
}
