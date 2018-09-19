package com.example.admin.shopnail.AsynTaskManager;

import android.os.AsyncTask;

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
                Resuilt = caseManagers[0].makePostRequestLogin(caseManagers[0].getUrl(), caseManagers[0].getUserName(), caseManagers[0].getPassWord());
                mResuiltObject = new ResuiltObject(LOGIN, Resuilt);
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
