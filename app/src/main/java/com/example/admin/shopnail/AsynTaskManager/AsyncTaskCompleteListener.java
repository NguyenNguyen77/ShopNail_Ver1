package com.example.admin.shopnail.AsynTaskManager;

public interface AsyncTaskCompleteListener<ResuiltObject> {
    void onTaskCompleted(String s, String CaseRequest);

    void onTaskError(String s, String CaseRequest);

}
