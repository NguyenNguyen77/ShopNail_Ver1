package com.example.admin.shopnail.Presenter.CancelAppointmentOnline;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;

public interface CancelAppointmentOnlineImp {
    void getListBookOnline(String date);

    void requestCancelService(GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBean);

    void startScroll();
}
