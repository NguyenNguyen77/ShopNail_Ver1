package com.example.admin.shopnail.View.ViewCartActivity;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.Manager.ViewManager;

public interface CartView {
    void setAdapterProductChoosed(ViewProductAdapter viewProductAdapter);

    void setTotalExpectExtra(int totalPrice);

    int getTotal();

    int getExtraPrice();

    String getDateOrder();

    void updateUIAfterOrder();
    public void showErrorDialog (ViewManager.ERROR_CODE errorCode);
}
