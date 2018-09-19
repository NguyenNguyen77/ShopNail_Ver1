package com.example.admin.shopnail.AsynTaskManager;

public class ResuiltObject {
    String Case;
    String Resuilt;

    public ResuiltObject(String aCase, String resuilt) {
        Case = aCase;
        Resuilt = resuilt;
    }

    public String getCase() {
        return Case;
    }

    public void setCase(String aCase) {
        Case = aCase;
    }

    public String getResuilt() {
        return Resuilt;
    }

    public void setResuilt(String resuilt) {
        Resuilt = resuilt;
    }
}
