package com.example.admin.shopnail.View.ManageStaff;

import com.example.admin.shopnail.Model.ManageStaff.CheckBoxObject;
import com.example.admin.shopnail.Model.ManageStaff.GsonServiceType;

import java.util.List;

public interface ManagerStaffView {
    void setListCheckBox(List<CheckBoxObject> arrCheckBox, List<GsonServiceType.SuccessBean.ServiceTypeBean> arrServiceType);

    void closeProgress();
}
