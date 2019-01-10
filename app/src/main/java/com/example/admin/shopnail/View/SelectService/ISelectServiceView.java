package com.example.admin.shopnail.View.SelectService;

import android.widget.ArrayAdapter;

import com.example.admin.shopnail.Adapter.CategoryAdapter;
import com.example.admin.shopnail.Adapter.SelectServiceAdapter;

import org.json.JSONArray;

public interface ISelectServiceView {

    void setCategoryAdapter(CategoryAdapter adapter);

    void setProductsByCategoryAdapter(SelectServiceAdapter selectServiceAdapter);

    void addJsonArrayService(boolean isChecked, String name, int id, String price, String format);

    JSONArray getArrayChecked();

    void dismissProgress();

    void disibleProgressbar();
}
