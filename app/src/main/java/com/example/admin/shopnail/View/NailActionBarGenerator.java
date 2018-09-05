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
    }
}
