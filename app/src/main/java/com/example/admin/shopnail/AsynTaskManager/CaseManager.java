package com.example.admin.shopnail.AsynTaskManager;

import android.content.Context;
import android.net.Uri;

import com.example.admin.shopnail.Manager.BaseMethod;

public class CaseManager extends BaseMethod {
    Context Context;
    String Case;
    String Url;
    Uri.Builder mBuilder;

    public CaseManager(android.content.Context context, String aCase, String url, Uri.Builder mBuilder) {
        Context = context;
        Case = aCase;
        Url = url;
        this.mBuilder = mBuilder;
        getInforAccountFromShareReferenced(context);
    }

    public android.content.Context getContext() {
        return Context;
    }

    public void setContext(android.content.Context context) {
        Context = context;
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

    public Uri.Builder getmBuilder() {
        return mBuilder;
    }

    public void setmBuilder(Uri.Builder mBuilder) {
        this.mBuilder = mBuilder;
    }
}

