package com.example.admin.shopnail.Presenter.CancelAppointmentOnline;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.example.admin.shopnail.Model.ResetPassword.GsonResuilt;
import com.example.admin.shopnail.Model.SelectCustomerService.GsonProductsByCategory;
import com.example.admin.shopnail.View.CancelAppointmentOnline.CancelAppointmentOnlineView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.DATE;
import static com.example.admin.shopnail.Manager.KeyManager.DELETE_ORDER_APPOINTMENT_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ORDER_BOOKING_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_SERVICE_TYPE;
import static com.example.admin.shopnail.Manager.KeyManager.LIMIT;
import static com.example.admin.shopnail.Manager.KeyManager.LIMIT_CANCEL_APPOINTMENT;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.PAGE;
import static com.example.admin.shopnail.Manager.KeyManager.PRODUC_ID;
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

    String date;
    @Override
    public void getListBookOnline(String date) {
        this.date = date;
        cancelAppointmentOnlineView.showProgress();
        new NailTask(this).execute(new CaseManager(context, GET_ORDER_BOOKING_ONLINE, UrlManager.GET_ORDER_BOOKING_ONLINE_URL, getJsonRequest(date).toString()));
    }

    @Override
    public void requestCancelService(GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBean) {
        cancelAppointmentOnlineView.showProgress();
        new NailTask(this).execute(new CaseManager(context, DELETE_ORDER_APPOINTMENT_ONLINE, UrlManager.DELETE_ORDER_APPOINTMENT_ONLINE_URL, getJsonDeleteService(ordersBean).toString()));
    }

    @Override
    public void startScroll() {
        setPositionPage(getPositionPage() + 1);
        new NailTask(this).execute(new CaseManager(context, GET_ORDER_BOOKING_ONLINE, UrlManager.GET_ORDER_BOOKING_ONLINE_URL, getJsonRequest(date).toString()));
    }


    GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBeDelete;
    private JSONObject getJsonDeleteService(GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBean) {
        this.ordersBeDelete = ordersBean;
        JSONObject object = new JSONObject();
        try {
            object.put(ORDER_ID, ordersBean.getOrderId());
            object.put(PRODUC_ID, ordersBean.getProductId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;

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
                try {
                    GsonOppointment mGsonOppointment = getGson().fromJson(s, GsonOppointment.class);
                    if (getPositionPage() == 1) {
                        arrServiceType.clear();
                        arrServiceType.addAll(mGsonOppointment.getSuccess().getServiceType());
                        appointmentAdapter = new CancelAppointmentAdapter(context, arrServiceType);
                        cancelAppointmentOnlineView.setAdapterList(appointmentAdapter);
                    } else {
                        arrServiceType.addAll(mGsonOppointment.getSuccess().getServiceType());
                        appointmentAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.d(KeyManager.VinhCNLog, e.toString());
                    if (getPositionPage() == 1) {
                        List<GsonOppointment.SuccessBean.ServiceTypeBean> arrservice = new ArrayList<>();
                        cancelAppointmentOnlineView.setAdapterList(new CancelAppointmentAdapter(context, arrservice));
                    }
                }
                break;
            case DELETE_ORDER_APPOINTMENT_ONLINE:
                try{
                    GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
                    if (mGsonResuilt.isStatus()){
                        setPositionPage(1);
                        getListBookOnline(date);
                        Toast.makeText(context, mGsonResuilt.getSuccess().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, mGsonResuilt.getSuccess().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Can not cancel this order", Toast.LENGTH_SHORT).show();
                }
                Log.d(KeyManager.VinhCNLog, s);
                break;
            default:
                break;
        }
        cancelAppointmentOnlineView.hideProgress(View.GONE);
        cancelAppointmentOnlineView.disibleProgressbar();
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {
        cancelAppointmentOnlineView.disibleProgressbar();
    }
}
