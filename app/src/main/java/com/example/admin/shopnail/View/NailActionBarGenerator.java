package com.example.admin.shopnail.View;

import android.app.Activity;

public class NailActionBarGenerator {

    /**
     * ActionBar
     *
     * @param activity
     * @param type
     */
    public void generate(final Activity activity, final BarType type) {
        if ((type == null) || (activity == null)) {
            throw new IllegalArgumentException("generate actionbar but null.");
        }

        NailActionBarBuildable actionBarBuilder = null;
        switch (type) {
            case LOGIN:
            case MENU_FOR_STAFF:
            case STAFF_INFO:
            case SELECT_CUSTOMER_SERVICE:
            case CUSTOMER_SERVICE_HISTORY:
            case VIEW_CART:
            case RESET_PASSWORD:
            case MANAGE_STAFF:
            case BOOK_APPOINTMENT:
            case CANCEL_APPOINTMENT:
            case MY_CUSTOMER:
                actionBarBuilder = new NailBuilder(activity, type);
                break;
            default:
                break;
        }

        if (actionBarBuilder != null) {
            actionBarBuilder.generate();
        }
    }

    /**
     * ActionBar
     */
    public enum BarType {
        LOGIN,
        MENU_FOR_STAFF,
        STAFF_INFO,
        SELECT_CUSTOMER_SERVICE,
        CUSTOMER_SERVICE_HISTORY,
        VIEW_CART,
        MANAGE_STAFF,
        RESET_PASSWORD,
        MY_CUSTOMER,
        BOOK_APPOINTMENT,
        CANCEL_APPOINTMENT,
    }
}
