package com.example.admin.shopnail.Presenter.StaffInformation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangeAvatar;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangePass;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.View.ERROR_CODE;
import com.example.admin.shopnail.View.StaffInfo.IStaffInformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class StaffInformationPresenter extends BaseMethod implements IStaffInformationPresenter, AsyncTaskCompleteListener<ResuiltObject> {


    private IStaffInformation mIStaffInforView;
    private boolean mResult = false;
    Context mContext;

    public StaffInformationPresenter(IStaffInformation staffInformationView, Context context) {
        this.mIStaffInforView = staffInformationView;
        mContext = context;
    }


    @Override
    public void requestChangePassword(final String oldPass, final String newPass, final String confirmNewPass) {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.CHANGE_PASSWORD, UrlManager.CHANGE_PASSWORD_URL, getParamBuilder(oldPass, newPass, confirmNewPass)));
    }

    // mIStaffInforView.onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK);
    Uri.Builder getParamBuilder(String oldPass, String newPass, String confirmNewPass) {
        Uri.Builder mBuilder = new Uri.Builder();
        mBuilder.appendQueryParameter(KeyManager.ID, getDefaults(KeyManager.USER_ID, mContext));
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD, oldPass);
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD_NEW, newPass);
        mBuilder.appendQueryParameter(KeyManager.PASS_WORD_CONFIRMATION, confirmNewPass);
        Log.d("KhoaND14", "request: Json: " + mBuilder.build().getEncodedQuery());
        return mBuilder;
    }

    @Override
    public void requestInfor(String defaults) {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_USER_BY_ID, UrlManager.GET_USER_BY_ID_URL + defaults, getParamBuilder()));
    }

    @Override
    public void requestChangeAvatar(String path) {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.CHANGE_AVATAR, UrlManager.CHANGE_AVATAR_URL, addJsonRequestChangeAvatar(path).toString()));
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }

    JSONObject addJsonRequestChangeAvatar(String path) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(KeyManager.ID, Integer.parseInt(getDefaults(KeyManager.USER_ID, mContext)));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            options.inPurgeable = true;
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int permission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                mIStaffInforView.updatePermission();
            } else {
                bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
                byte[] byteImage_photo = baos.toByteArray();
                String encodedImage = Base64.encodeToString(byteImage_photo, Base64.DEFAULT);
                mJsonObject.put(KeyManager.BASE_64, encodedImage.trim());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("KhoaND14", "request: Json: " + mJsonObject);
        return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        Log.d("KhoaND14", "result: Json: " + s);
        switch (CaseRequest) {
            case KeyManager.GET_USER_BY_ID:
                try {
                    GsonStaffInfor mGsonStaffInfor = getGson().fromJson(s, GsonStaffInfor.class);
                    mIStaffInforView.setStaffInfor(mGsonStaffInfor.getSuccess());
                } catch (Exception e) {
                    mIStaffInforView.showError();
                }
                break;
            case KeyManager.CHANGE_PASSWORD:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonChangePass mGsonChangePass = getGson().fromJson(s, GsonChangePass.class);
                    mIStaffInforView.onChangePasswordResult(mGsonChangePass.isStatus() ? ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_OK : ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG);
                } catch (Exception e) {
                    mIStaffInforView.onChangePasswordResult(ERROR_CODE.CHANGE_PASS_RESULT_CODE.RESULT_NG);
                }
                break;
            case KeyManager.CHANGE_AVATAR:
                try {
                    GsonChangeAvatar mGsonChangeAvatar = getGson().fromJson(s, GsonChangeAvatar.class);
                    mIStaffInforView.onChangeAvatarResult(mGsonChangeAvatar.isStatus());
                } catch (Exception e) {
                    mIStaffInforView.onChangeAvatarResult(false);
                }
                break;
                default:
                    break;
        }

    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
