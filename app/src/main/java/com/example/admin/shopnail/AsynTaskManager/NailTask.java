package com.example.admin.shopnail.AsynTaskManager;

import android.os.AsyncTask;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;

import static com.example.admin.shopnail.Manager.KeyManager.ADD_OR_UPDATE_SERVICE_CHECKING;
import static com.example.admin.shopnail.Manager.KeyManager.BOOK_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.CANCEL_SERVICE;
import static com.example.admin.shopnail.Manager.KeyManager.CHANGE_AVATAR;
import static com.example.admin.shopnail.Manager.KeyManager.CHANGE_PASSWORD;
import static com.example.admin.shopnail.Manager.KeyManager.CHECK_TIME_BOOK_ONLINE;
import static com.example.admin.shopnail.Manager.KeyManager.CREATE_ACCOUNT_CUSTOMER;
import static com.example.admin.shopnail.Manager.KeyManager.FORGOT_PASSWORD;
import static com.example.admin.shopnail.Manager.KeyManager.GENERATE_CHECK_BOX;
import static com.example.admin.shopnail.Manager.KeyManager.GET_ALL_NAVIGATE_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.GET_CLIENT_OF_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY;
import static com.example.admin.shopnail.Manager.KeyManager.GET_SERVICE_TYPE;
import static com.example.admin.shopnail.Manager.KeyManager.GET_TIME_OF_CLIENT_FROM_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.GET_USER_BY_ID;
import static com.example.admin.shopnail.Manager.KeyManager.LOGIN;
import static com.example.admin.shopnail.Manager.KeyManager.ORDER_SERVICE_BY_STAFF;
import static com.example.admin.shopnail.Manager.KeyManager.UPDATE_EXTRA;
import static com.example.admin.shopnail.Manager.KeyManager.UPDATE_STATUS_SERVICE;

public class NailTask extends AsyncTask<CaseManager, Integer, ResuiltObject> {

    AsyncTaskCompleteListener<ResuiltObject> AsynCallBack;

    public NailTask(AsyncTaskCompleteListener<ResuiltObject> asynCallBack) {
        AsynCallBack = asynCallBack;
    }

    @Override
    protected ResuiltObject doInBackground(CaseManager... caseManagers) {
        String Resuilt = "";
        ResuiltObject mResuiltObject = null;
        switch (caseManagers[0].getCase()) {
            case GET_ALL_NAVIGATE_STAFF:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GET_ALL_NAVIGATE_STAFF, Resuilt);
                break;
            case GET_SERVICE_TYPE:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GET_SERVICE_TYPE, Resuilt);
                break;
            case ADD_OR_UPDATE_SERVICE_CHECKING:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(ADD_OR_UPDATE_SERVICE_CHECKING, Resuilt);
                break;
            case GENERATE_CHECK_BOX:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GENERATE_CHECK_BOX, Resuilt);
                break;
            case UPDATE_EXTRA:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(UPDATE_EXTRA, Resuilt);
                break;
            case CANCEL_SERVICE:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(CANCEL_SERVICE, Resuilt);
                break;
            case UPDATE_STATUS_SERVICE:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(UPDATE_STATUS_SERVICE, Resuilt);
                break;
            case GET_TIME_OF_CLIENT_FROM_STAFF:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GET_TIME_OF_CLIENT_FROM_STAFF, Resuilt);
                break;
            case FORGOT_PASSWORD:
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(FORGOT_PASSWORD, Resuilt);
                break;
            case GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY, Resuilt);
                break;
            case ORDER_SERVICE_BY_STAFF:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(ORDER_SERVICE_BY_STAFF, Resuilt);
                break;
            case GET_CLIENT_OF_STAFF:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(GET_CLIENT_OF_STAFF, Resuilt);
                break;
            case LOGIN:
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(LOGIN, Resuilt);
                break;
            case GET_USER_BY_ID:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_USER_BY_ID, Resuilt);
                break;
            case CHANGE_PASSWORD:
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.CHANGE_PASSWORD, Resuilt);
                break;
            case CHANGE_AVATAR:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.CHANGE_AVATAR, Resuilt);
                break;
            case CREATE_ACCOUNT_CUSTOMER:
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.CREATE_ACCOUNT_CUSTOMER, Resuilt);
                break;
            case KeyManager.LOGIN_OLD_CUSTOMER:
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.LOGIN_OLD_CUSTOMER, Resuilt);
                break;
            case KeyManager.GET_CATEGORY_LIST:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_CATEGORY_LIST, Resuilt);
                break;
            case KeyManager.GET_PRODUCTS_BY_CATEGORY:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_PRODUCTS_BY_CATEGORY, Resuilt);
                break;
            case KeyManager.GET_ALL_STAFF_ID:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_ALL_STAFF_ID, Resuilt);
                break;
            case KeyManager.GET_ALL_SERVICE_ID:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_ALL_SERVICE_ID, Resuilt);
                break;
            case BOOK_ONLINE:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(BOOK_ONLINE, Resuilt);
                break;
            case CHECK_TIME_BOOK_ONLINE:
                Resuilt = caseManagers[0].makePostRequestJson(caseManagers[0].getUrl(), caseManagers[0].getParamJson(), caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(CHECK_TIME_BOOK_ONLINE, Resuilt);
                break;
        }
        return mResuiltObject;
    }


    @Override
    protected void onPostExecute(ResuiltObject resuiltObject) {
        if (resuiltObject != null) {
            AsynCallBack.onTaskCompleted(resuiltObject.getResuilt(), resuiltObject.getCase());
        }
        super.onPostExecute(resuiltObject);
    }
}
