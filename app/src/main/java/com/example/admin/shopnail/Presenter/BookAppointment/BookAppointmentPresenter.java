package com.example.admin.shopnail.Presenter.BookAppointment;

import android.content.Context;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.BookAppointment.GsonAllStaff;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangePass;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.View.BookAppointment.IBookAppointmentView;
import com.example.admin.shopnail.View.ERROR_CODE;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class BookAppointmentPresenter extends BaseMethod implements IBookAppointmentPresenter, AsyncTaskCompleteListener<ResuiltObject> {
    private IBookAppointmentView mBookAppointmentView;
    Context mContext;

    public BookAppointmentPresenter(Context context, IBookAppointmentView bookAppointmentView) {
        this.mContext = context;
        this.mBookAppointmentView = bookAppointmentView;
    }

    @Override
    public void reqGetStaffList() {
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_ALL_STAFF_ID, UrlManager.GET_ALL_STAFF_URL, getParamBuilder()));    //Send request to server: get Staff List for select at Book Appointment screen
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }

    @Override
    public void reqGetServiceList() {
        //Send request to Server
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_ALL_SERVICE_ID, UrlManager.GET_ALL_SERVICE_URL, getParamBuilder()));
        //Stub
        ArrayList<String> arrayOfService = new ArrayList<String>();
        arrayOfService.add("Hair1");
        arrayOfService.add("Hair2");
        arrayOfService.add("Hair3");
        arrayOfService.add("Hair4");
        arrayOfService.add("Hair5");
        mBookAppointmentView.updateServiceList(arrayOfService);
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.GET_ALL_STAFF_ID:
                try {
                    GsonAllStaff mGsonAllStaff = getGson().fromJson(s, GsonAllStaff.class);
                    ArrayList<String> staffList = new ArrayList<String>();
                    staffList.add(mGsonAllStaff.getSuccess().getStaffBean().get(0).getName());
                    mBookAppointmentView.updateStaffList(staffList);
                } catch (Exception e) {
//                    mIStaffInforView.showError();
                }
                break;
            case KeyManager.GET_ALL_SERVICE_ID:
                try {
                    GsonAllStaff mGsonAllStaff = getGson().fromJson(s, GsonAllStaff.class);
                    ArrayList<String> serviceList = new ArrayList<String>();
                    serviceList.add(mGsonAllStaff.getSuccess().getStaffBean().get(0).getName());
                    mBookAppointmentView.updateServiceList(serviceList);
                } catch (Exception e) {
                    // Show error
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
