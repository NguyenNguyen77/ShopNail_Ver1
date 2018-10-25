package com.example.admin.shopnail.Manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.example.admin.shopnail.View.BookAppointment.BookAppointmentActivity;
import com.example.admin.shopnail.View.Login.MainActivity;
import com.example.admin.shopnail.View.CustomerServiceHistory.CustomerServiceHistoryActivity;
import com.example.admin.shopnail.View.ResetPassword.ResetPasswordActivity;
import com.example.admin.shopnail.View.ManageStaff.ManageStaffActivity;
import com.example.admin.shopnail.View.MenuFoStaff.MenuForStaffActivity;
import com.example.admin.shopnail.View.MyCustomer.MyCustomerActivity;
import com.example.admin.shopnail.View.MyDetailCustomer.MyDetailCustomerActivity;
import com.example.admin.shopnail.View.LoginCustomer.LoginForCustomerActivity;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.StaffInfo.StaffInformationActivity;
import com.example.admin.shopnail.View.ViewCartActivity.ViewCartActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.admin.shopnail.Manager.KeyManager.ARRAY_PRODUCT;
import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_HISTORY_CHOOSED;
import static com.example.admin.shopnail.Manager.KeyManager.DATE;

public class ViewManager {
    private Activity currentActivity = null;
    private VIEW_KEY mViewKey = VIEW_KEY.LOGIN_SCREEN;
    private List<Activity> mListActivity = new ArrayList<Activity>();
    private static ViewManager instance;
    public ProgressDialog mProgressDialog;

    public enum VIEW_KEY {
        LOGIN_SCREEN,
        MENU_FOR_STAFF,
        BOOK_APPOINTMENT,
        STAFF_INFO,
        SELECT_SERVICE,
        LOGIN_FOR_CUSTOMER,
        CUSTOMER_SERVICE_HISTORY,
        VIEW_CART,
        RESET_PASSWORD,
        MANAGE_STAFF,
        MY_CUSTOMER,
        MY_DETAIL_CUSTOMER
    }

    public void setView(VIEW_KEY key) {
        switch (key) {
            case LOGIN_SCREEN:
                viewLoginActivity();
                break;
            case MENU_FOR_STAFF:
                viewMenuForStaffActivity();
                break;
            case STAFF_INFO:
                viewStaffInformationActivity();
                break;
            case MANAGE_STAFF:
                viewManageStaff();
                break;
            case SELECT_SERVICE:
                viewSelectServiceActivity();
                break;
            case LOGIN_FOR_CUSTOMER:
                viewLoginForCustomerActivity();
                break;
            case CUSTOMER_SERVICE_HISTORY:
                viewCustomerServiceHistoryActivity();
                break;
            case VIEW_CART:
                viewViewCartActivity();
                break;
            case RESET_PASSWORD:
                viewResetPasswordActivity();
                break;
            case BOOK_APPOINTMENT:
                viewBookAppointmentActivity();
                break;
            case MY_CUSTOMER:
                viewMyCustomerActivity();
                break;
            case MY_DETAIL_CUSTOMER:
                viewMyDetailCustomerActivity();
                break;
            default:
                break;
        }

    }


    public void setView(VIEW_KEY key, JSONArray array) {
        switch (key) {
            case VIEW_CART:
                viewViewCartActivity(array);
                break;
        }

    }

    public void setView(VIEW_KEY key, String json, String date) {
        switch (key) {
            case MY_DETAIL_CUSTOMER:
                viewMyDetailCustomerActivity(json, date);
                break;
        }

    }





    public static synchronized ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public void setActivity(Activity a) {
        currentActivity = a;
        if (mListActivity.contains(a) == false) {
            mListActivity.add(a);
        }
    }

    public Class<?> getActivity() {
        Log.d(TAG, "getView()=" + String.valueOf(getView()));
        return convKEYtoClass(getView());
    }

    public void setViewKey(VIEW_KEY viewKey) {
        mViewKey = viewKey;
    }

    public VIEW_KEY getView() {
        return mViewKey;
    }

    public Class<? extends Activity> convKEYtoClass(VIEW_KEY key) {
        switch (key) {
            case LOGIN_SCREEN:
                return MainActivity.class;

            case STAFF_INFO:
                return StaffInformationActivity.class;
            case MANAGE_STAFF:
                return ManageStaffActivity.class;
            case MY_CUSTOMER:
                return MyCustomerActivity.class;
            case MY_DETAIL_CUSTOMER:
                return MyDetailCustomerActivity.class;
            case MENU_FOR_STAFF:
                return MenuForStaffActivity.class;

            case LOGIN_FOR_CUSTOMER:
                return LoginForCustomerActivity.class;

            case SELECT_SERVICE:
                return SelectServiceActivity.class;

            case CUSTOMER_SERVICE_HISTORY:
                return CustomerServiceHistoryActivity.class;
            case VIEW_CART:
                return ViewCartActivity.class;
            case RESET_PASSWORD:
                return ResetPasswordActivity.class;
            default:
                break;
        }
        return null;
    }

    private void viewLoginActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.LOGIN_SCREEN);
    }

    private void viewMenuForStaffActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), MenuForStaffActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MENU_FOR_STAFF);
    }

    private void viewManageStaff() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), ManageStaffActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MANAGE_STAFF);
    }

    private void viewStaffInformationActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), StaffInformationActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.STAFF_INFO);
    }

    private void viewLoginForCustomerActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), LoginForCustomerActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.LOGIN_FOR_CUSTOMER);
    }

    private void viewSelectServiceActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), SelectServiceActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.SELECT_SERVICE);
    }


    private void viewCustomerServiceHistoryActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), CustomerServiceHistoryActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
    }

    private void viewViewCartActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), ViewCartActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.VIEW_CART);
    }

    private void viewViewCartActivity(JSONArray array) {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), ViewCartActivity.class);
        intent.putExtra(ARRAY_PRODUCT, array.toString());
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.VIEW_CART);
    }


    private void viewResetPasswordActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), ResetPasswordActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.RESET_PASSWORD);
    }


    private void viewBookAppointmentActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity.getApplicationContext(), BookAppointmentActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.BOOK_APPOINTMENT);
    }

    private void viewMyCustomerActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity.getApplicationContext(), MyCustomerActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MY_CUSTOMER);
    }

    private void viewMyDetailCustomerActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity.getApplicationContext(), MyDetailCustomerActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MY_DETAIL_CUSTOMER);
    }

    private void viewMyDetailCustomerActivity(String json, String date) {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }

        Intent intent = new Intent(activity.getApplicationContext(), MyDetailCustomerActivity.class);
        intent.putExtra(CLIENT_HISTORY_CHOOSED, json);
        intent.putExtra(DATE, date);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MY_DETAIL_CUSTOMER);
    }

    public void handleBackScreen() {
        switch (mViewKey) {
            case SELECT_SERVICE:
                setView(VIEW_KEY.LOGIN_FOR_CUSTOMER);
                break;
            case STAFF_INFO:
            case CUSTOMER_SERVICE_HISTORY:
            case LOGIN_FOR_CUSTOMER:
            case MANAGE_STAFF:
            case MY_CUSTOMER:
                setView(VIEW_KEY.MENU_FOR_STAFF);
                break;
            case RESET_PASSWORD:
            case MENU_FOR_STAFF:
                setView(VIEW_KEY.LOGIN_SCREEN);
                break;
            case BOOK_APPOINTMENT:
                setView(VIEW_KEY.LOGIN_SCREEN);
                break;
            case VIEW_CART:
                setView(VIEW_KEY.SELECT_SERVICE);
                break;
            default:
                break;
        }
    }

    public void finishListActivity() {
        for (Activity stack : mListActivity) {
            if (stack != null) {
                stack.finish();
            }
        }
        mListActivity.clear();
    }

    public void finishActivity(Activity activity) {
        for (Activity stack : mListActivity) {
            if (stack == activity) {
                stack.finish();
                mListActivity.remove(stack);
            }
        }
    }

    public void showInprogressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
        mProgressDialog = new ProgressDialog(currentActivity);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
    }

    public void dismissInprogressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }
}
