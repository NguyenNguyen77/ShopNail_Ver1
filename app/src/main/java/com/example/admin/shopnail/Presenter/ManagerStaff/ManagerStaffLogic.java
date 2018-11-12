package com.example.admin.shopnail.Presenter.ManagerStaff;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.shopnail.Adapter.ManageStaffAdapter;
import com.example.admin.shopnail.AsynTaskManager.AsyncTaskCompleteListener;
import com.example.admin.shopnail.AsynTaskManager.CaseManager;
import com.example.admin.shopnail.AsynTaskManager.NailTask;
import com.example.admin.shopnail.AsynTaskManager.ResuiltObject;
import com.example.admin.shopnail.CustomViewListExpand.SingleToast;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;
import com.example.admin.shopnail.Manager.UrlManager;
import com.example.admin.shopnail.Model.ManageStaff.CheckBoxObject;
import com.example.admin.shopnail.Model.ManageStaff.GsonAllNavigateStaff;
import com.example.admin.shopnail.Model.ManageStaff.GsonGenerateCheckbox;
import com.example.admin.shopnail.Model.ManageStaff.GsonServiceType;
import com.example.admin.shopnail.Model.MyDetailCustomer.GsonResuiltUpdate;
import com.example.admin.shopnail.View.ManageStaff.ManageStaffActivity;
import com.example.admin.shopnail.View.ManageStaff.ManagerStaffView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.ADD_OR_UPDATE_SERVICE_CHECKING;
import static com.example.admin.shopnail.Manager.KeyManager.GENERATE_CHECK_BOX;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_NAVIGATE_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_SERVICE_ID;
import static com.example.admin.shopnail.Manager.KeyManager.GET_SERVICE_TYPE;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER;
import static com.example.admin.shopnail.Manager.KeyManager.SERVICES;
import static com.example.admin.shopnail.Manager.KeyManager.STAFF_ID;
import static com.example.admin.shopnail.Manager.KeyManager.TYPE;
import static com.example.admin.shopnail.Manager.KeyManager.VALUE;
import static com.example.admin.shopnail.Manager.KeyManager.VALUES;
import static com.example.admin.shopnail.Manager.KeyManager.VinhCNLog;

public class ManagerStaffLogic extends BaseMethod implements ManagerStaffImp, AsyncTaskCompleteListener<ResuiltObject> {
    Context mContext;
    ManagerStaffView managerStaffView;
    List<CheckBoxObject> arrCheckBox;
    List<GsonServiceType.SuccessBean.ServiceTypeBean> arrServiceType;
    ManageStaffAdapter manageStaffAdapter;

    public ManagerStaffLogic(Context mContext, ManagerStaffView managerStaffView) {
        this.mContext = mContext;
        this.managerStaffView = managerStaffView;
    }

    @Override
    public void createCheckbox() {
        new NailTask(this).execute(new CaseManager(mContext, GENERATE_CHECK_BOX, UrlManager.GENERATE_CHECK_BOX_URL, getParamBuilder()));
    }

    @Override
    public void updateService() {
        new NailTask(this).execute(new CaseManager(mContext, ADD_OR_UPDATE_SERVICE_CHECKING, UrlManager.ADD_OR_UPDATE_SERVICE_CHECKING_URL, getJsonParamUpdateService().toString()));
    }

    @Override
    public void getServiceType() {
        new NailTask(this).execute(new CaseManager(mContext, GET_SERVICE_TYPE, UrlManager.GET_SERVICE_TYPE_URL, getParamBuilder()));
    }

    @Override
    public void getAllNavigateStaff() {
        new NailTask(this).execute(new CaseManager(mContext, GET_ALL_NAVIGATE_STAFF, UrlManager.GET_ALL_NAVIGATE_STAFF_URL, getParamBuilder()));
    }

    private JSONObject getJsonParamUpdateService() {
        JSONObject object = new JSONObject();
        try {
            object.put(STAFF_ID, getStaffId(mContext));
            JSONArray services = new JSONArray();
            for (int i = 0; i < arrCheckBox.size(); i++) {
                JSONObject objects = new JSONObject();
                if (arrCheckBox.get(i).getValueService() != 0) {
                    objects.put(TYPE, arrCheckBox.get(i).getTypeService());
                    objects.put(ORDER, arrCheckBox.get(i).getOrder());
                    objects.put(VALUE, arrCheckBox.get(i).getValueService());
                    services.put(objects);
                }
                if (arrCheckBox.get(i).getValueWax() == 1) {
                    objects = new JSONObject();
                    objects.put(TYPE, arrCheckBox.get(i).getTypeWax());
                    objects.put(ORDER, arrCheckBox.get(i).getOrder());
                    objects.put(VALUE, arrCheckBox.get(i).getValueWax());
                    services.put(objects);
                }
                if (arrCheckBox.get(i).getValueBonus() == 1) {
                    objects = new JSONObject();
                    objects.put(TYPE, arrCheckBox.get(i).getTypeBonus());
                    objects.put(ORDER, arrCheckBox.get(i).getOrder());
                    objects.put(VALUE, arrCheckBox.get(i).getValueBonus());
                    services.put(objects);
                }
            }
            object.put(SERVICES, services);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private Uri.Builder getParamBuilder() {
        return new Uri.Builder();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case GENERATE_CHECK_BOX:
                try {
                    arrCheckBox = new ArrayList<>();
                    Log.d(KeyManager.VinhCNLog, s);
                    GsonGenerateCheckbox mGsonGenerateCheckbox = getGson().fromJson(s, GsonGenerateCheckbox.class);
                    int serviceNumber = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getService_number());
                    int Bonus = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getBonus());
                    int Wax = Integer.parseInt(mGsonGenerateCheckbox.getSuccess().getSetting().getWax());
                    for (int i = 0; i < getMaxLine(serviceNumber, Bonus, Wax); i++) {
                        arrCheckBox.add(new CheckBoxObject(mGsonGenerateCheckbox.getSuccess().getSetting().getId(), i < serviceNumber ? true : false, i < Bonus ? true : false, i < Wax ? true : false, i + 1, 1, 3, 2, 0, 0, 0));
                    }
                    manageStaffAdapter = new ManageStaffAdapter(mContext, arrCheckBox, arrServiceType);
                    managerStaffView.setListCheckBox(arrCheckBox, arrServiceType, manageStaffAdapter);
                    getAllNavigateStaff();
                } catch (Exception e) {
                    SingleToast.show(mContext, "Server error", 3000);
                }
                break;
            case ADD_OR_UPDATE_SERVICE_CHECKING:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonResuiltUpdate update = getGson().fromJson(s, GsonResuiltUpdate.class);
                    SingleToast.show(mContext, update.isStatus() ? "Update Service success" : "Update Service fail", 3000);

                } catch (Exception e) {
                    SingleToast.show(mContext, "Server error", 3000);
                }
                break;
            case GET_SERVICE_TYPE:
                Log.d(KeyManager.VinhCNLog, s);
                try {
                    GsonServiceType type = getGson().fromJson(s, GsonServiceType.class);
                    arrServiceType = type.getSuccess().getServiceType();
                    createCheckbox();
                } catch (Exception e) {
                    SingleToast.show(mContext, "Server error", 3000);
                }
                break;
            case GET_ALL_NAVIGATE_STAFF:
                Log.d(VinhCNLog, s);
                try {
                    GsonAllNavigateStaff navigateStaff = getGson().fromJson(s, GsonAllNavigateStaff.class);
                    List<GsonAllNavigateStaff.SuccessBean.NavigatesBean> arrNavigate = navigateStaff.getSuccess().getNavigates();
                    for (int i = 0; i < arrNavigate.size(); i++) {
                        for (int j = 0; j < arrCheckBox.size(); j++) {
                            int order_number = Integer.parseInt(arrNavigate.get(i).getOrder_number());
                            int order = arrCheckBox.get(j).getOrder();
                            if (order_number == order) {
                                int ServiceID = Integer.parseInt(arrNavigate.get(i).getService_id());
                                arrCheckBox.get(j).setValueService(ServiceID);
                                arrCheckBox.get(j).setValueWax(Integer.parseInt(arrNavigate.get(i).getWax()));
                                arrCheckBox.get(j).setValueBonus(Integer.parseInt(arrNavigate.get(i).getBonus()));
                            }
                        }
                    }
                    manageStaffAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    SingleToast.show(mContext, "Server error", 3000);
                }
                break;
            default:
                break;

        }
        managerStaffView.closeProgress();
    }

    private int getMaxLine(int service_number, int bonus, int wax) {
        return Math.max(service_number, Math.max(bonus, wax));
    }

    @Override
    public void onTaskError(String s, String CaseRequest) {

    }

}
