package com.example.admin.shopnail.AsynTaskManager;

import android.content.Context;

import com.example.admin.shopnail.Manager.BaseMethod;

public class CaseManager extends BaseMethod {
    Context Context;
    String Case;
    String Url;

    public CaseManager(Context context, String aCase, String url ) {
        Context = context;
        Case = aCase;
        Url = url;
        getInforAccountFromShareReferenced(context);
    }

    public String getCase() {
        return Case;
    }

    public void setCase(String aCase) {
        Case = aCase;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

