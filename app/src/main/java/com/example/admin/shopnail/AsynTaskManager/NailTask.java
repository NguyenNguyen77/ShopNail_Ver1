package com.example.admin.shopnail.AsynTaskManager;

import android.os.AsyncTask;

import com.example.admin.shopnail.Manager.KeyManager;

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
                Resuilt = caseManagers[0].makePostRequest(caseManagers[0].getUrl(), caseManagers[0].getmBuilder());
                mResuiltObject = new ResuiltObject(LOGIN, Resuilt);
                break;
            case GET_USER_BY_ID:
                Resuilt = caseManagers[0].makeGetRequest(caseManagers[0].getUrl(),  caseManagers[0].getToken());
                mResuiltObject = new ResuiltObject(KeyManager.GET_USER_BY_ID, Resuilt);
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
