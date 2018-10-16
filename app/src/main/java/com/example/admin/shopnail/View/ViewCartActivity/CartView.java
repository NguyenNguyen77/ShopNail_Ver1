package com.example.admin.shopnail.View.ViewCartActivity;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;

public interface CartView {
    void setAdapterProductChoosed(ViewProductAdapter viewProductAdapter);

    void setTotalExpectExtra(int totalPrice);

    int getTotal();

    int getExtraPrice();

    String getDateOrder();
}
