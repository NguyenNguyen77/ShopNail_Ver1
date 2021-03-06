package com.example.admin.shopnail.View;

import android.app.Activity;
import com.example.admin.shopnail.R;

public class NailBuilder implements NailActionBarBuildable {

    private Activity mActivity;

    private NailActionBarGenerator.BarType mBarType;

    /**
     * @param activity ActionBar
     */
    public NailBuilder(final Activity activity,
                       final NailActionBarGenerator.BarType barType) {
        mActivity = activity;
        mBarType = barType;
    }

    @Override
    public void generate() {
        NailActionBar actionBar = getActionBarBuilder().build();
        actionBar.generate();
    }

    /**
     * ActionBar
     *
     * @return Builder
     */
    private NailActionBar.Builder getActionBarBuilder() {

        NailActionBar.Builder builder = new NailActionBar.Builder(
                mActivity,
                R.string.app_name,
                R.color.textTitle,
                false);
        switch (mBarType) {
            case LOGIN:
                builder.setTitle(R.string.title_main);
                break;
            case MENU_FOR_STAFF:
                builder.setTitle(R.string.menu_for_staff);
                break;
            case MANAGE_STAFF:
                builder.setTitle(R.string.manage_staff);
                break;
            case STAFF_INFO:
                builder.setTitle(R.string.staff_info);
                break;
            case SELECT_CUSTOMER_SERVICE:
                builder.setTitle(R.string.select_service);
                break;
            case CUSTOMER_SERVICE_HISTORY:
                builder.setTitle(R.string.customer_service_history);
                break;
            case VIEW_CART:
                builder.setTitle(R.string.view_cart);
                break;
            case RESET_PASSWORD:
                builder.setTitle(R.string.reset_password);
                break;
            case BOOK_APPOINTMENT:
                builder.setTitle(R.string.book_appointment);
                break;
            case MY_CUSTOMER:
                builder.setTitle(R.string.my_customer);
                break;
            case CANCEL_APPOINTMENT:
                builder.setTitle(R.string.cancel_appointment);

            case ORDER_MANAGEMENT_ONLINE:
                builder.setTitle(R.string.order_management_online);
                break;
            default:
                throw new IllegalArgumentException("Unsupported BarType. [" + mBarType + "]");
        }

        return builder;
    }
}
