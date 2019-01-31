package com.example.admin.shopnail.View.CancelAppointmentOnline;

import com.example.admin.shopnail.Adapter.CancelAppointmentAdapter;
import com.example.admin.shopnail.Model.CancelAppointmentOnline.GsonOppointment;
import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;

public interface CancelAppointmentOnlineView {

    GsonLoginOutSide getDataLogin();

    void setAdapterList(CancelAppointmentAdapter appointmentAdapter);

    void showProgress();

    void disibleProgressbar();

    void showDialogConfirmCancel(GsonOppointment.SuccessBean.ServiceTypeBean.OrdersBean ordersBean);

    void hideProgress(int gone);
}
