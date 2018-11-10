package com.example.admin.shopnail.Presenter.BookAppointment;

import android.content.Context;
import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.BookServiceAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Manager.ViewManager;
import com.example.admin.shopnail.Model.BookAppointment.GsonAllService;
import com.example.admin.shopnail.Model.BookAppointment.GsonAllStaff;
import com.example.admin.shopnail.Model.BookAppointment.GsonResBookAppointment;
import com.example.admin.shopnail.Model.BookAppointment.GsonResCheckBookingTime;
import com.example.admin.shopnail.Model.StaffInfor.GsonChangePass;
import com.example.admin.shopnail.Model.StaffInfor.GsonStaffInfor;
import com.example.admin.shopnail.Model.ViewProductPresenter.GsonProductChoosed;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.BookAppointment.IBookAppointmentView;
import com.example.admin.shopnail.View.ERROR_CODE;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.admin.shopnail.Manager.KeyManager.CHECK_TIME_BOOK_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.DATE;
import static com.example.admin.shopnail.Manager.KeyManager.DATE_ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.EMAIL;
import static com.example.admin.shopnail.Manager.KeyManager.EXTRA;
import static com.example.admin.shopnail.Manager.KeyManager.FULL_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.NOTE;
import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.PHONE;
import static com.example.admin.shopnail.Manager.KeyManager.PRICE;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUCT;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUC_ID;
import static com.example.admin.shopnail.Manager.KeyManager.STAFF_ID;
import static com.example.admin.shopnail.Manager.KeyManager.TIMEWORK;
import static com.example.admin.shopnail.Manager.KeyManager.TIME_ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID_KEY;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.VALUES;

public class BookAppointmentPresenter extends BaseMethod implements IBookAppointmentPresenter, AsyncTaskCompleteListener<ResuiltObject> {
    private IBookAppointmentView mBookAppointmentView;
    Context mContext;
    private GsonAllStaff mGsonAllStaff;
    private GsonAllService mGsonAllService;
    int mProductID = 0;
    String mProductPrice = "";

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
    }

    @Override
    public void reqBookOnline(String fullName, String email, String phone, String date, BookServiceAdapter serviceAdapter) {
        if (!checkReqTimeValid(serviceAdapter)) {
            Toast.makeText(mContext, R.string.error_check_time_booking, Toast.LENGTH_LONG).show();
            return;
        }
        String json = addJsonRequest(fullName, email, phone, date, serviceAdapter).toString();
        Log.d("KhoaND14", "KhoaNguyen: Json: " + json);
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.BOOK_ONLINE, UrlManager.ADD_BOOKING_ONLINE, json));
    }

    @Override
    public void checkTimeBookOnline(String staffName, int selectStaff, String date, String timeorder) {
        String json = addJsonRequestCheckTimeBooking(staffName, selectStaff, date, timeorder).toString();
        Log.d("KhoaND14", "checkTimeBookOnline: Json: " + json);
        new NailTask(this).execute(new CaseManager(mContext, KeyManager.CHECK_TIME_BOOK_ONLINE, UrlManager.BOOKING_TIME_CHECKING_URL, json));
    }


    JSONObject addJsonRequest(String fullName, String email, String phone, String date, BookServiceAdapter serviceAdapter) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(FULL_NAME, fullName);
            mJsonObject.put(PHONE, phone);
            mJsonObject.put(EMAIL, email);
            mJsonObject.put(VALUES, getArrayValues(date, serviceAdapter));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    JSONArray getArrayValues(String date, BookServiceAdapter serviceAdapter) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < serviceAdapter.getCount(); i++) {
            try {
                JSONObject object = new JSONObject();
                String staffName = serviceAdapter.getItem(i).getStaffList().get(serviceAdapter.getItem(i).getSelectStaff());
                int selectedStaffPos = serviceAdapter.getItem(i).getSelectStaff();

                object.put(DATE_ORDER, date);
                object.put(NOTE, serviceAdapter.getItem(i).getNote());
                object.put(STAFF_ID, getStaffID(staffName, selectedStaffPos));
                object.put(PRODUCT, getProduct(serviceAdapter, i));
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }


    JSONObject getProduct(BookServiceAdapter serviceAdapter, int pos) {
        JSONObject json = new JSONObject();
        try {
            String productName = serviceAdapter.getItem(pos).getServiceList().get(serviceAdapter.getItem(pos).getSelectService());
            Boolean result = getProductInfo(productName);
            json.put(PRODUC_ID, mProductID);
            json.put(PRICE, mProductPrice);
            json.put(TIME_ORDER, serviceAdapter.getItem(pos).getServiceTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    JSONObject addJsonRequestCheckTimeBooking(String staffName, int selectStaff, String date, String timeorder) {
        JSONObject mJsonObject = new JSONObject();
        try {
            int staffid = getStaffID(staffName, selectStaff);
            mJsonObject.put(STAFF_ID, staffid);
            mJsonObject.put(DATE, date);
            mJsonObject.put(TIMEWORK, timeorder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        Log.d("KhoaND14", "result: Json: " + s);
        switch (CaseRequest) {
            case KeyManager.GET_ALL_STAFF_ID:
                try {
                    mGsonAllStaff = getGson().fromJson(s, GsonAllStaff.class);
                    ArrayList<String> staffList = new ArrayList<String>();
                    int staffNum = mGsonAllStaff.getSuccess().getStaffBean().size();
                    for (int i = 0; i < staffNum; i++) {
                        staffList.add(mGsonAllStaff.getSuccess().getStaffBean().get(i).getName());
                    }
                    mBookAppointmentView.updateStaffList(staffList);
                } catch (Exception e) {
                    mBookAppointmentView.showErrorDialog(ViewManager.ERROR_CODE.GET_STAFF_FAIL);
                }
                break;
            case KeyManager.GET_ALL_SERVICE_ID:
                try {
                    mGsonAllService = getGson().fromJson(s, GsonAllService.class);
                    ArrayList<String> serviceList = new ArrayList<String>();
                    int productNum = mGsonAllService.getSuccess().getProduct().size();
                    for (int i = 0; i < productNum; i++) {
                        int cateChildNum = mGsonAllService.getSuccess().getProduct().get(i).getCateChild().size();
                        for (int j = 0; j < cateChildNum; j++) {
                            serviceList.add(mGsonAllService.getSuccess().getProduct().get(i).getCateChild().get(j).getProductName());
                        }
                    }
                    mBookAppointmentView.updateServiceList(serviceList);
                } catch (Exception e) {
                    mBookAppointmentView.showErrorDialog(ViewManager.ERROR_CODE.GET_SERVICE_FAIL);
                }
                break;
            case KeyManager.BOOK_ONLINE:
                try {
                    GsonResBookAppointment result = getGson().fromJson(s, GsonResBookAppointment.class);
                    int resultCode = result.getSuccess().getCode();
                    if (resultCode == 200) {
                        String message = getGson().fromJson(s, GsonResBookAppointment.class).getSuccess().getMessage();
                        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, R.string.error_book_appointment, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, R.string.error_book_appointment, Toast.LENGTH_LONG).show();
                }
                break;

            case CHECK_TIME_BOOK_ONLINE:

                try {
                    GsonResCheckBookingTime result = getGson().fromJson(s, GsonResCheckBookingTime.class);
                    int resultCode = result.getSuccess().getCode();
                    if (resultCode == 200) {
                        String message = getGson().fromJson(s, GsonResCheckBookingTime.class).getSuccess().getMessage();
                        mBookAppointmentView.updateOrderTime();
                        //Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();  //Not show
                    } else {
                        Toast.makeText(mContext, R.string.error_check_time_booking, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, R.string.error_check_time_booking, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

    private int getStaffID(String staffname, int posstaffname) {
        int staffID = 0;

        int staffNum = mGsonAllStaff.getSuccess().getStaffBean().size();
        for (int i = 0; i < staffNum; i++) {

            if ((staffname.equals(mGsonAllStaff.getSuccess().getStaffBean().get(i).getName()))
                    && (posstaffname == i)) {
                staffID = mGsonAllStaff.getSuccess().getStaffBean().get(i).getId();
            }
        }
        return staffID;
    }

    private boolean getProductInfo(String productname) {
        mProductID = 0;
        mProductPrice = "";

        int serviceNum = mGsonAllService.getSuccess().getProduct().size();
        for (int i = 0; i < serviceNum; i++) {
            int cateChildNum = mGsonAllService.getSuccess().getProduct().get(i).getCateChild().size();
            for (int j = 0; j < cateChildNum; j++) {
                if (productname.equals(mGsonAllService.getSuccess().getProduct().get(i).getCateChild().get(j).getProductName())) {
                    mProductID = mGsonAllService.getSuccess().getProduct().get(i).getCateChild().get(j).getProductId();
                    mProductPrice = mGsonAllService.getSuccess().getProduct().get(i).getCateChild().get(j).getProductPrice();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkReqTimeValid(BookServiceAdapter bookServiceAdapter) {
        int reqNum = bookServiceAdapter.getCount();
        for (int i = 0; i < reqNum; i++) {
            if (bookServiceAdapter.getItem(i).getServiceTime().equals("--:--")) {
                return false;
            }
        }
        return true;
    }
}
