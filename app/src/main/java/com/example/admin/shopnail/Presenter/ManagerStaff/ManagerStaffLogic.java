package com.example.admin.shopnail.Presenter.ManagerStaff;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.ManageStaff.CheckBoxObject;
import com.example.admin.shopnail.Model.ManageStaff.GsonGenerateCheckbox;
import com.example.admin.shopnail.View.ManageStaff.ManagerStaffView;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.GENERATE_CHECK_BOX;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_SERVICE_ID;

public class ManagerStaffLogic extends BaseMethod implements ManagerStaffImp, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    ManagerStaffView managerStaffView;
    List<CheckBoxObject> arrCheckBox;

    public ManagerStaffLogic(Context mContext, ManagerStaffView managerStaffView) {
        this.mContext = mContext;
        this.managerStaffView = managerStaffView;
    }

    @Override
    public void createCheckbox() {
        new NailTask(this).execute(new CaseManager(mContext, GENERATE_CHECK_BOX, UrlManager.GENERATE_CHECK_BOX_URL, getParamBuilder()));
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case GENERATE_CHECK_BOX:
                arrCheckBox  = new ArrayList<>();
                Log.d(KeyManager.VinhCNLog, s);
                GsonGenerateCheckbox mGsonGenerateCheckbox = getGson().fromJson(s, GsonGenerateCheckbox.class);
                int serviceNumber = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getService_number());
                int Bonus = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getBonus());
                int Wax = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getWax());
                for (int i = 0; i < getMaxLine(serviceNumber, Bonus, Wax); i++) {
                    arrCheckBox.add(new CheckBoxObject(mGsonGenerateCheckbox.getSuccess().getSetting().getId(), i < serviceNumber ? true : false, i < Bonus ? true : false, i < Wax ? true : false));
                }
                managerStaffView.setListCheckBox(arrCheckBox);
                break;
        }
    }

    private int getMaxLine(int service_number, int bonus, int wax) {
        return Math.max(service_number, Math.max(bonus, wax));
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

}
