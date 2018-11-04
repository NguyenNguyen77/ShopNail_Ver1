package com.example.admin.shopnail.Presenter.BookAppointment;

import android.content.Context;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.BookAppointment.GsonAllStaff;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangePass;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonProductChoosed;
import com.example.admin.shopnail.View.BookAppointment.IBookAppointmentView;
import com.example.admin.shopnail.View.ERROR_CODE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.admin.shopnail.Manager.KeyManager.DATE_ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.EXTRA;
import static com.example.admin.shopnail.Manager.KeyManager.FULL_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.NOTE;
import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.PHONE;
import static com.example.admin.shopnail.Manager.KeyManager.PRICE;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUC_ID;
import static com.example.admin.shopnail.Manager.KeyManager.STAFF_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID_KEY;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.VALUES;

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
//        new NailTask(this).execute(new CaseManager(mContext, KeyManager.GET_ALL_SERVICE_ID, UrlManager.GET_ALL_SERVICE_URL, getParamBuilder()));
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
    public void reqBookOnline(String fullName, String phone, String date, BookServiceAdapter serviceAdapter) {
        String json = addJsonRequest(fullName, phone,date, serviceAdapter).toString();
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.BOOK_ONLINE, UrlManager.ADD_BOOKING_ONLINE, json));
    }


    JSONObject addJsonRequest(String fullName, String phone, String date, BookServiceAdapter serviceAdapter){
        JSONObject mJsonObject  = new JSONObject();
        try {
            mJsonObject.put(FULL_NAME, fullName);
            mJsonObject.put(PHONE, phone);
            mJsonObject.put(VALUES, getArrayProduct(date, serviceAdapter));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  mJsonObject;
    }

    JSONArray getArrayProduct(String date, BookServiceAdapter serviceAdapter){
        JSONArray array =  new JSONArray();
        for (int i = 0; i < serviceAdapter.getCount(); i++){
            try {
                JSONObject object = new JSONObject();
                object.put(DATE_ORDER, date);
                object.put(NOTE, serviceAdapter.getItem(i).getNote());
                object.put(STAFF_ID, serviceAdapter.getItem(i).getStaffList().get(serviceAdapter.getItem(i).getSelectStaff()));
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
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
                    mBookAppointmentView.showErrorDialog(ViewManager.ERROR_CODE.GET_STAFF_FAIL);
                }
                break;
            case KeyManager.GET_ALL_SERVICE_ID:
                try {
                    GsonAllStaff mGsonAllStaff = getGson().fromJson(s, GsonAllStaff.class);
                    ArrayList<String> serviceList = new ArrayList<String>();
                    serviceList.add(mGsonAllStaff.getSuccess().getStaffBean().get(0).getName());
                    mBookAppointmentView.updateServiceList(serviceList);
                } catch (Exception e) {
                    mBookAppointmentView.showErrorDialog(ViewManager.ERROR_CODE.GET_SERVICE_FAIL);
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
