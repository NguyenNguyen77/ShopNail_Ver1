package com.example.admin.shopnail.View;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.admin.shopnail.MainActivity;
import com.example.admin.shopnail.View.MenuFoStaff.MenuForStaffActivity;
import com.example.admin.shopnail.View.SelectService.LoginForCustomerActivity;
import com.example.admin.shopnail.View.SelectService.SelectServiceActivity;
import com.example.admin.shopnail.View.StaffInfo.StaffInformationActivity;

import static android.content.ContentValues.TAG;

public class ViewManager {
    private Activity currentActivity = null;
    private VIEW_KEY mViewKey = VIEW_KEY.LOGIN_SCREEN;

    public enum VIEW_KEY {
        LOGIN_SCREEN,
        MENU_FOR_STAFF,
        BOOK_APPOINTMENT,
        STAFF_INFO,
        SELECT_SERVICE,
        LOGIN_FOR_CUSTOMER
    }

    public void setView(VIEW_KEY key) {
        switch (key) {
            case LOGIN_SCREEN:
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
            default:
                break;
        }

    }

    public void setActivity(Activity a) {
        currentActivity = a;
    }

    public Class<?> getActivity() {
        Log.d(TAG, "getView()=" + String.valueOf(getView()));
        return convKEYtoClass(getView());
    }
    public VIEW_KEY getView() {
        return mViewKey;
    }

    public Class<? extends Activity> convKEYtoClass(VIEW_KEY key) {
        switch (key) {
            case LOGIN_SCREEN:
                return MainActivity.class;

            case MENU_FOR_STAFF:
                return MenuForStaffActivity.class;

            case LOGIN_FOR_CUSTOMER:
                return LoginForCustomerActivity.class;
            case SELECT_SERVICE:
                return SelectServiceActivity.class;

            default:
                break;
        }
        return null;
    }

    private void viewMenuForStaffActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), MenuForStaffActivity.class);
        activity.startActivity(intent);
    }

    private void viewStaffInformationActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), StaffInformationActivity.class);
        activity.startActivity(intent);
    }

    private void viewLoginForCustomerActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), LoginForCustomerActivity.class);
        activity.startActivity(intent);
    }

    private void viewSelectServiceActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), SelectServiceActivity.class);
        activity.startActivity(intent);
    }
}
