package com.example.admin.shopnail.AsynTaskManager;

import android.os.AsyncTask;

import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Manager.KeyManager;

import static com.example.admin.shopnail.Manager.KeyManager.CHANGE_PASSWORD;
import static com.example.admin.shopnail.Manager.KeyManager.CREATE_ACCOUNT_CUSTOMER;
import static com.example.admin.shopnail.Manager.KeyManager.GET_USER_BY_ID;
import static com.example.admin.shopnail.Manager.KeyManager.LOGIN;

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
