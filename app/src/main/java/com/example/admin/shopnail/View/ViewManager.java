package com.example.admin.shopnail.View;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.admin.shopnail.MainActivity;
import com.example.admin.shopnail.View.CustomerServiceHistory.CustomerServiceHistoryActivity;
import com.example.admin.shopnail.View.MenuFoStaff.MenuForStaffActivity;
import com.example.admin.shopnail.View.SelectService.LoginForCustomerActivity;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.StaffInfo.StaffInformationActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ViewManager {
    private Activity currentActivity = null;
    private VIEW_KEY mViewKey = VIEW_KEY.LOGIN_SCREEN;
    private List<Activity> mListActivity = new ArrayList<Activity>();
    private static ViewManager instance;

    public enum VIEW_KEY {
        LOGIN_SCREEN,
        MENU_FOR_STAFF,
        BOOK_APPOINTMENT,
        STAFF_INFO,
        SELECT_SERVICE,
        LOGIN_FOR_CUSTOMER,
        CUSTOMER_SERVICE_HISTORY,
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
            case SELECT_SERVICE:
                viewSelectServiceActivity();
                break;
            case LOGIN_FOR_CUSTOMER:
                viewLoginForCustomerActivity();
                break;
            case CUSTOMER_SERVICE_HISTORY:
                viewCustomerServiceHistoryActivity();
                break;
            default:
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
        if( mListActivity.contains(a) == false ){
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

            case MENU_FOR_STAFF:
                return MenuForStaffActivity.class;

            case LOGIN_FOR_CUSTOMER:
                return LoginForCustomerActivity.class;

            case SELECT_SERVICE:
                return SelectServiceActivity.class;

            case CUSTOMER_SERVICE_HISTORY:
                return CustomerServiceHistoryActivity.class;

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

    public void handleBackScreen() {
        switch (mViewKey) {
            case SELECT_SERVICE:
                setView(VIEW_KEY.LOGIN_FOR_CUSTOMER);
            case STAFF_INFO:
            case CUSTOMER_SERVICE_HISTORY:
                setView(VIEW_KEY.MENU_FOR_STAFF);
                break;
            case MENU_FOR_STAFF:
                setView(VIEW_KEY.LOGIN_SCREEN);
                break;
            default:
                break;
        }
    }

    public void finishListActivity() {
        for (Activity stack : mListActivity) {
            if( stack != null ){
                stack.finish();
            }
        }
        mListActivity.clear();
    }
}
