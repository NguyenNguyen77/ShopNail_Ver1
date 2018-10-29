package com.example.admin.shopnail.Presenter.CustomerServiceHistory;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.admin.shopnail.Adapter.CustomerAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.CustomerInfo.Customer;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.ServiceHistory;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.View.CustomerServiceHistory.ICustomerServiceHistoryView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceHistoryPresenter extends BaseMethod implements ICustomerServiceHistoryPresenter, AsyncTaskCompleteListener<ResuiltObject> {

    private ArrayList<Customer> mArrayOfUsers;
    String Date;
    Context mContext;
    List<GsonGetClient.SuccessBean.ClientsBean> arrClient;

    private ICustomerServiceHistoryView mCustomerServiceHistoryView;

    public CustomerServiceHistoryPresenter(ICustomerServiceHistoryView customerServiceHistoryView, Context context) {
        this.mCustomerServiceHistoryView = customerServiceHistoryView;
        this.mContext = context;
    }

    @Override
    public void loadCustomerServiceHistoryByDate(Date selected) {
        mArrayOfUsers = new ArrayList<Customer>();
        mArrayOfUsers.add(new Customer("KhoaNguyen", "0973603509"));
        mArrayOfUsers.add(new Customer("Peter", "0123456789"));
        mArrayOfUsers.add(new Customer("Tom", "0123456789"));
        mArrayOfUsers.add(new Customer("Jerry", "0123456789"));
        // For test only: show inprogress dialog
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                mCustomerServiceHistoryView.updateListCustomerServiceHistoryByDate(mArrayOfUsers);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
        //For test only
    }

    @Override
    public void loadCustomerServiceHistoryByID(String name, String phone) {
        ArrayList<ServiceHistory> arrayOfServiceHistories = new ArrayList<ServiceHistory>();
        arrayOfServiceHistories.add(new ServiceHistory("Hair", 50, true, 30));
        arrayOfServiceHistories.add(new ServiceHistory("Nail clipper", 20, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Nail polish", 30, true, 10));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, true, 20));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, true, 20));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        arrayOfServiceHistories.add(new ServiceHistory("Pedicure", 50, false, 0));
        mCustomerServiceHistoryView.showListCustomerServiceHistoryByID(arrayOfServiceHistories);

    }

    @Override
    public void requestCustomerOrder(String date) {
        this.Date = date;
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(mContext, KeyManager.GET_CLIENT_OF_STAFF, UrlManager.GET_CLIENT_OF_STAFF, getJsonRequest().toString()));
    }

    @Override
    public void OpenHistorisDetail(int position) {

    }

    private JSONObject getJsonRequest() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(KeyManager.DATE, Date);
            mJsonObject.put(KeyManager.STAFF_ID, getStaffId(mContext));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.GET_CLIENT_OF_STAFF:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonGetClient mGsonGetClient = getGson().fromJson(s, GsonGetClient.class);
                    arrClient = mGsonGetClient.getSuccess().getClients();
                    mCustomerServiceHistoryView.setAdapterClients(arrClient);
                } catch (Exception e) {
                    Log.d(KeyManager.VinhCNLog, s);
                    arrClient = new ArrayList<>();
                    mCustomerServiceHistoryView.setAdapterClients(arrClient);
                }
                break;
        }
    }



    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
