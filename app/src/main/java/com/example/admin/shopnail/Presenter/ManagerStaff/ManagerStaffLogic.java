package com.example.admin.shopnail.Presenter.ManagerStaff;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.View.ManageStaff.ManagerStaffView;

import static com.example.admin.shopnail.Manager.KeyManager.GENERATE_CHECK_BOX;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_SERVICE_ID;

public class ManagerStaffLogic implements ManagerStaffImp, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    ManagerStaffView managerStaffView;

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
                Log.d(KeyManager.VinhCNLog, s);

                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

}
