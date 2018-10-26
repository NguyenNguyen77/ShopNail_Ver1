package com.example.admin.shopnail.CustomViewListExpand;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;

/**
 * Created by Vinh on 1/2/2018.
 */

public class CustomFrameLayout extends FrameLayout {

    // android:fitsSystemWindows="true" non padding

    public CustomFrameLayout(Context context) {
        super(context);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        // this is added so we can "consume" the padding which is added because
        // `android:fitsSystemWindows="true"` was added to the XML tag of View.
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < 20) {
            insets.top = 0;
            // remove height of NavBar so that it does add padding at bottom.
            insets.bottom -= 25;
        }
        return super.fitSystemWindows(insets);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        // executed by API >= 20.
        // removes the empty padding at the bottom which equals that of the height of NavBar.
        setPadding(0, 0, 0, insets.getSystemWindowInsetBottom() - 25);
        return insets.consumeSystemWindowInsets();
    }

}
