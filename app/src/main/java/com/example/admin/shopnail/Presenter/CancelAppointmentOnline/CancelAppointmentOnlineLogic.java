package com.example.admin.shopnail.Presenter.CancelAppointmentOnline;

import android.content.Context;
import android.util.Log;

import com.example.admin.shopnail.Adapter.CancelAppointmentAdapter;
import com.example.admin.shopnail.Adapter.SelectServiceAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonProductsByCategory;
import com.example.admin.shopnail.View.CancelAppointmentOnline.CancelAppointmentOnlineView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.DATE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ORDER_BOOKING_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_SERVICE_TYPE;
import static com.example.admin.shopnail.Manager.KeyManager.LIMIT;
import static com.example.admin.shopnail.Manager.KeyManager.LIMIT_CANCEL_APPOINTMENT;
import static com.example.admin.shopnail.Manager.KeyManager.PAGE;
import static com.example.admin.shopnail.Manager.KeyManager.STATUS;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID_KEY;
import static com.example.admin.shopnail.Manager.KeyManager.VinhCNLog;

public class CancelAppointmentOnlineLogic extends BaseMethod implements CancelAppointmentOnlineImp, AsyncTaskCompleteListener<ResuiltObject> {
    CancelAppointmentOnlineView cancelAppointmentOnlineView;
    Context context;

    public CancelAppointmentOnlineLogic(CancelAppointmentOnlineView cancelAppointmentOnlineView, Context context) {
        this.cancelAppointmentOnlineView = cancelAppointmentOnlineView;
        this.context = context;
    }


    @Override
    public void getListBookOnline(String date) {
        cancelAppointmentOnlineView.showProgress();
        new NailTask(this).execute(new CaseManager(context, GET_ORDER_BOOKING_ONLINE, UrlManager.GET_ORDER_BOOKING_ONLINE_URL, getJsonRequest(date).toString()));
    }

    private JSONObject getJsonRequest(String date) {
        JSONObject object = new JSONObject();
        try {
            object.put(USER_ID_KEY, cancelAppointmentOnlineView.getDataLogin().getSuccess().getUser_id());
            object.put(DATE, date);
            object.put(STATUS, 0);
            object.put(LIMIT, LIMIT_CANCEL_APPOINTMENT);
            object.put(PAGE, getPositionPage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


    List<GsonOppointment.SuccessBean.ServiceTypeBean> arrServiceType = new ArrayList<>();
    CancelAppointmentAdapter appointmentAdapter;

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case GET_ORDER_BOOKING_ONLINE:
                try{
                    GsonOppointment mGsonOppointment = getGson().fromJson(s, GsonOppointment.class);
                    if (getPositionPage()==1){
                        arrServiceType.clear();
                        arrServiceType.addAll(mGsonOppointment.getSuccess().getServiceType());
                        appointmentAdapter = new CancelAppointmentAdapter(context, arrServiceType);
                        cancelAppointmentOnlineView.setAdapterList(appointmentAdapter);
                    }else {
                        arrServiceType.addAll(mGsonOppointment.getSuccess().getServiceType());
                        appointmentAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    Log.d(KeyManager.VinhCNLog, e.toString());
                    if (getPositionPage()==1){
                        List<GsonOppointment.SuccessBean.ServiceTypeBean> arrservice = new ArrayList<>();
                        cancelAppointmentOnlineView.setAdapterList(new CancelAppointmentAdapter(context, arrservice));
                    }
                }
                cancelAppointmentOnlineView.disibleProgressbar();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {
        cancelAppointmentOnlineView.disibleProgressbar();
    }
}
