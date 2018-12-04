package com.example.admin.shopnail.Presenter.MyCustomerPresenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.CustomViewListExpand.SingleToast;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.MyCustomer.GsonClientTime;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;
import com.example.admin.shopnail.Model.MyCustomer.TimeSelect;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.MyCustomer.MyCustomerView;
import com.example.admin.shopnail.Manager.ViewManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.GET_TIME_OF_CLIENT_FROM_STAFF;

public class MyCustommerLogic extends BaseMethod implements IMyCustomer, AsyncTaskCompleteListener<ResuiltObject> {

    Context mContext;
    MyCustomerView myCustomerView;
    String Date;
    List<GsonGetClient.SuccessBean.ClientsBean> arrClient;
    ViewManager mViewManager = ViewManager.getInstance();
    List<GsonClientTime.SuccessBean.TimeBean> listTime;

    public MyCustommerLogic(Context mContext, MyCustomerView myCustomerView) {
        this.mContext = mContext;
        this.myCustomerView = myCustomerView;
    }

    @Override
    public void requestCustomerOrder(String date) {
        this.Date = date;
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(mContext, KeyManager.GET_CLIENT_OF_STAFF, UrlManager.GET_CUSTOMER_INFOR, getJsonRequest().toString()));
    }


    GsonGetClient.SuccessBean.ClientsBean clientChoosed;

    @Override
    public void tranfertoDetailCustomer(int i) {
        clientChoosed = arrClient.get(i);
        new NailTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(mContext, GET_TIME_OF_CLIENT_FROM_STAFF, UrlManager.GET_TIME_OF_CLIENT_FROM_STAFF_URL, getArrayClientOrderID(arrClient.get(i)).toString()));
//        myCustomerView.showDialogChoosedTime(arrClient.get(i));
//        mViewManager.setView(ViewManager.VIEW_KEY.MY_DETAIL_CUSTOMER, getGson().toJson(arrClient.get(i)), this.Date);
    }

    @Override
    public void openDetailCustomer(int which, List<TimeSelect> arrTimeSelect) {
        mViewManager.setView(ViewManager.VIEW_KEY.MY_DETAIL_CUSTOMER, arrTimeSelect.get(which).getOrderID(), arrTimeSelect.get(which).getTimeName(),  getGson().toJson(clientChoosed), myCustomerView.getDateChoosed());
    }

    private JSONArray getArrayClientOrderID(GsonGetClient.SuccessBean.ClientsBean clientsBean) {
        JSONArray array = new JSONArray();
        for (String s : clientsBean.getClientOrderId()) {
            array.put(s);
        }
        return array;
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
        Log.d("KhoaND14", "result: Json: " + s);
        switch (CaseRequest) {
            case KeyManager.GET_CLIENT_OF_STAFF:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonGetClient mGsonGetClient = getGson().fromJson(s, GsonGetClient.class);
                    arrClient = mGsonGetClient.getSuccess().getClients();
                    myCustomerView.setAdapterClients(arrClient);
                } catch (Exception e) {
                    Log.d(KeyManager.VinhCNLog, s);
                    arrClient = new ArrayList<>();
                    myCustomerView.setAdapterClients(arrClient);
                }
                break;
            case GET_TIME_OF_CLIENT_FROM_STAFF:
                try {
                    GsonClientTime gsonClientTime = getGson().fromJson(s, GsonClientTime.class);
                    if (gsonClientTime.isStatus()) {
                        listTime = gsonClientTime.getSuccess().getTime();
                        myCustomerView.showTimeDialog(listTime);
                    } else {
                        if (gsonClientTime.getSuccess().getError() != null) {
                            Toast.makeText(mContext, gsonClientTime.getSuccess().getError(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    SingleToast.show(mContext, "Server error", 3000);
                }
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }
}
