package com.example.admin.shopnail.View.MyCustomer;

import com.example.admin.shopnail.Model.MyCustomer.GsonClientTime;
import com.example.admin.shopnail.Model.MyCustomer.GsonGetClient;

import java.util.List;

public interface MyCustomerView {

    void setAdapterClients(List<GsonGetClient.SuccessBean.ClientsBean> arrClient);

    void showDialogChoosedTime(GsonGetClient.SuccessBean.ClientsBean clientsBean);

    void showTimeDialog(List<GsonClientTime.SuccessBean.TimeBean> listTime);

    String getDateChoosed();
}
