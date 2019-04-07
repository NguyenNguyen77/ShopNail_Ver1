package com.example.admin.shopnail.Manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.BookAppointment.BookAppointmentActivity;
import com.example.admin.shopnail.View.CancelAppointmentOnline.CancelAppointmentOnlineActivity;
import com.example.admin.shopnail.View.Login.MainActivity;
import com.example.admin.shopnail.View.CustomerServiceHistory.CustomerServiceHistoryActivity;
import com.example.admin.shopnail.View.OrderManagermentOnline.OrderManagementOnlineActivity;
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
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.TIME_ORDER;

public class ViewManager {
    private Activity currentActivity = null;
    private VIEW_KEY mViewKey = VIEW_KEY.LOGIN_SCREEN;
    private List<Activity> mListActivity = new ArrayList<Activity>();
    private static ViewManager instance;
    public ProgressDialog mProgressDialog;

    public String dateTemp="";

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
        MY_DETAIL_CUSTOMER,
        CANCEL_APPOINTMENT,
        ORDER_MANAGEMENT_ONLINE
    }
    public enum ERROR_CODE {
        LOGIN_FAIL,
        GET_STAFF_FAIL,
        GET_SERVICE_FAIL,
        GET_ORDER_FAIL
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
            case CANCEL_APPOINTMENT:
                viewCancelAppointmentActivity();
                break;
            case ORDER_MANAGEMENT_ONLINE:
                viewOrderManagementActivity();
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

    public void setView(VIEW_KEY key, String data) {
        switch (key) {
            case CANCEL_APPOINTMENT:
                viewCancelAppointmentActivity(data);
                break;
            case ORDER_MANAGEMENT_ONLINE:
                viewOrderManagementActivity();
                break;
        }

    }

    public void setView(VIEW_KEY key, String orderID, String Time, String JsonInfor, String date) {
        switch (key) {
            case MY_DETAIL_CUSTOMER:
                viewMyDetailCustomerActivity(orderID, Time, JsonInfor, date);
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
            case CANCEL_APPOINTMENT:
                return CancelAppointmentOnlineActivity.class;
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

    private void viewCancelAppointmentActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), CancelAppointmentOnlineActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.CANCEL_APPOINTMENT);
    }

    private void viewOrderManagementActivity() {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), OrderManagementOnlineActivity.class);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.ORDER_MANAGEMENT_ONLINE);
    }

    private void viewCancelAppointmentActivity(String data) {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), CancelAppointmentOnlineActivity.class);
        intent.putExtra(KeyManager.DATA, data);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.CANCEL_APPOINTMENT);
    }
    private void viewMyDetailCustomerActivity(String orderID, String Time, String JsonInfor, String date) {
        Activity activity = currentActivity;
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity.getApplicationContext(), MyDetailCustomerActivity.class);
        intent.putExtra(ORDER_ID, orderID);
        intent.putExtra(TIME_ORDER, Time);
        intent.putExtra(CLIENT_HISTORY_CHOOSED, JsonInfor);
        intent.putExtra(DATE, date);
        activity.startActivity(intent);
        setViewKey(VIEW_KEY.MY_DETAIL_CUSTOMER);
    }

    public void handleBackScreen() {
        Log.d("KhoaND14", "handleBackScreen. mViewKey: " + mViewKey);
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
            case CANCEL_APPOINTMENT:
            case BOOK_APPOINTMENT:
                setView(VIEW_KEY.LOGIN_SCREEN);
                break;
            case VIEW_CART:
                setView(VIEW_KEY.SELECT_SERVICE);
                break;
            case MY_DETAIL_CUSTOMER:
                setView(VIEW_KEY.MY_CUSTOMER);
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
        Log.d("KhoaND14", "finishActivity. activity: " + activity);
        Log.d("KhoaND14", "finishActivity. mListActivity.size: " + mListActivity.size());
        for (Activity stack : mListActivity) {
            Log.d("KhoaND14", "finishActivity. stack: " + stack);
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

    public void checkConnection() {
        boolean isConnected = NetworkReceiver.isConnected();
        showSnack(isConnected);
    }

    public void showSnack(boolean isConnected) {
        if(!isApplicationSentToBackground(getInstance().currentActivity)){
            if (isConnected) {
                Toast.makeText(currentActivity.getApplicationContext(), "Internet connection successful ", Toast.LENGTH_LONG).show();
                Log.d("NguyenNK2", "ViewManager === da ket noi internet");
            } else {
                Toast.makeText(currentActivity.getApplicationContext(), "Please check internet connection and try again", Toast.LENGTH_LONG).show();
                Log.d("NguyenNK2", "ViewManager === ko ket noi internet");
            }
        }
    }

    public void showErrorDialog(String title, String content) {
        ContextThemeWrapper ctw = new ContextThemeWrapper(currentActivity, R.style.Theme_AlertDialog);
        final Dialog commonDialog = new Dialog(ctw);
        commonDialog.setContentView(R.layout.error_dialog);
        commonDialog.setTitle(title);

        TextView tvContent = (TextView) commonDialog.findViewById(R.id.txt_dialog_content);
        tvContent.setText(content);

        Button btnOK = (Button) commonDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }

    public static boolean isApplicationSentToBackground(final Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }

        return false;
    }
}