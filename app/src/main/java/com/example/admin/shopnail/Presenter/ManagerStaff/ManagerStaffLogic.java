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

import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_SERVICE_ID;

public class ManagerStaffLogic implements ManagerStaffImp, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    ManagerStaffView managerStaffView;

    public ManagerStaffLogic(Context mContext, ManagerStaffView managerStaffView) {
        this.mContext = mContext;
        this.managerStaffView = managerStaffView;
    }

    @Override
    public void requestListService() {
        new NailTask(this).execute(new CaseManager(mContext, GET_ALL_SERVICE_ID, UrlManager.GET_ALL_SERVICE_URL, getParamBuilder()));
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case GET_ALL_SERVICE_ID:
                Log.d(KeyManager.VinhCNLog, s);

                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
