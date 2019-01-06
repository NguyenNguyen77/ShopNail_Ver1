package com.example.admin.shopnail.View.StaffInfo;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.NetworkReceiver;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.Presenter.StaffInformation.StaffInformationPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.ERROR_CODE;
import com.example.admin.shopnail.Manager.ViewManager;
import com.squareup.picasso.Picasso;

import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;

public class StaffInformationActivity extends Activity implements View.OnClickListener, IStaffInformation, NetworkReceiver.ConnectivityReceiverListener {
    private ImageView imgAvatar;
    private Button btnChangeAvatar;
    private Button btnChangePassword;
    private Button btnBack;
    private TextView mID;
    private TextView mName;
    private TextView mPhone;

    protected ViewManager mViewManager = ViewManager.getInstance();
    private String mOldPass;
    private String mNewPass;
    private String mConfirmNewPass;
    private Dialog mChangePassDialog;
    private static int RESULT_LOAD_IMAGE = 1;

    StaffInformationPresenter mStaffInformationPresenter = new StaffInformationPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_information);
        mViewManager.setActivity(this);
        new com.example.admin.shopnail.View.NailActionBarGenerator().generate(this,
                com.example.admin.shopnail.View.NailActionBarGenerator.BarType.STAFF_INFO);

        btnChangeAvatar = (Button) findViewById(R.id.btn_change_avatar);
        btnChangePassword = (Button) findViewById(R.id.btn_change_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);
        mID = (TextView) findViewById(R.id.tv_staff_id);
        mName = (TextView) findViewById(R.id.tv_staff_name);
        mPhone = (TextView) findViewById(R.id.tv_staff_phone_number);

        btnChangeAvatar.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);

        mViewManager.showInprogressDialog();
        mStaffInformationPresenter.requestInfor(BaseMethod.getDefaults(KeyManager.USER_ID, this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_avatar:
            case R.id.img_avatar:
                if(requestPermissions()){
                    changeAvatar();
                }
                break;
            case R.id.btn_change_password:
                showChangePasswordDialog();
                break;
            case R.id.btn_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_select_service).setVisible(true);
        menu.findItem(R.id.action_my_customer).setVisible(true);
        menu.findItem(R.id.action_manage_staff).setVisible(true);
        menu.findItem(R.id.action_customer_service_history).setVisible(true);
        menu.findItem(R.id.action_menu_for_staff).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_for_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);
                return true;
            case R.id.action_select_service:
                mViewManager.setView(ViewManager.VIEW_KEY.SELECT_SERVICE);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

        final Drawable mBackgroundColorOldPass = txtOldPass.getBackground();
        final Drawable mBackgroundColorNewpass = txtOldPass.getBackground();
        final Drawable mBackgroundColorConfirmPass = txtOldPass.getBackground();
        // Check validate input value START
        txtOldPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtOldPass.setBackground(mBackgroundColorOldPass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtNewPass.setBackground(mBackgroundColorNewpass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtConfirmNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtConfirmNewPass.setBackground(mBackgroundColorConfirmPass);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // Check validate input value END

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInvalid = false;
                mOldPass = txtOldPass.getText().toString().trim();
                mNewPass = txtNewPass.getText().toString().trim();
                mConfirmNewPass = txtConfirmNewPass.getText().toString().trim();

                if(txtNewPass.length()<6 || txtConfirmNewPass.length()<6){
                    Toast.makeText(getApplicationContext(), "Passwords must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mOldPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please insert old password ", Toast.LENGTH_LONG).show();
                    txtOldPass.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }
                if(mNewPass.isEmpty()){
                    if (!isInvalid) {
                        Toast.makeText(getApplicationContext(), "Please insert new password", Toast.LENGTH_LONG).show();
                    }
                    txtNewPass.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }
                if(mConfirmNewPass.isEmpty()){
                    if (!isInvalid) {
                        Toast.makeText(getApplicationContext(), "Please insert confirm new password", Toast.LENGTH_LONG).show();
                    }
                    txtConfirmNewPass.setBackgroundResource(R.drawable.bordertextview);
                    isInvalid = true;
                }

                if (!isInvalid && checkValidPassword(mOldPass, mNewPass, mConfirmNewPass)) {
                    mViewManager.showInprogressDialog();
                    mStaffInformationPresenter.requestChangePassword(BaseMethod.getDefaults(PASS_WORD, StaffInformationActivity.this), mNewPass, mConfirmNewPass);
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
            BaseMethod.setDefaults(KeyManager.PASS_WORD, mNewPass, this);
        } else {
            Toast.makeText(StaffInformationActivity.this, R.string.change_pass_fail, Toast.LENGTH_LONG).show();
        }
       mViewManager.dismissInprogressDialog();
    }

    @Override
    public void onChangeAvatarResult(boolean result) {
        if (result) {
            mStaffInformationPresenter.requestInfor(BaseMethod.getDefaults(KeyManager.USER_ID, this));
            Toast.makeText(this, R.string.change_avatar_success, Toast.LENGTH_LONG).show();
        } else {
            mViewManager.dismissInprogressDialog();
            Toast.makeText(this, R.string.change_avatar_failed, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError() {
        mViewManager.dismissInprogressDialog();
        Toast.makeText(this, "Loading Staff Fail...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setStaffInfor(GsonStaffInfor.SuccessBean success) {
        mViewManager.dismissInprogressDialog();
        mID.setText(String.valueOf(success.getId()));
        mName.setText(success.getFullname());
        mPhone.setText(BaseMethod.getDefaults(USER_NAME, this));
        Picasso.get().load(success.getAvatar()).into(imgAvatar);
    }

    @Override
    public void updatePermission() {
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );
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
        } else if (!mOldPass.equals(BaseMethod.getDefaults(PASS_WORD, StaffInformationActivity.this))) {
            Toast.makeText(this, R.string.old_pass_wrong, Toast.LENGTH_LONG).show();
            result = false;
        }
        return result;
    }

    private void changeAvatar() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            mViewManager.showInprogressDialog();
            mStaffInformationPresenter.requestChangeAvatar(picturePath);
        }
    }

    // Check Internet
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        mViewManager.showSnack(isConnected);
    }

    public boolean requestPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    changeAvatar();
                } else {
                    Toast.makeText(getApplicationContext(), "You must grant permission to use your image", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
}
