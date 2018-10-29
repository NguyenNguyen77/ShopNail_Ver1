package com.example.admin.shopnail.CustomViewListExpand;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Vinh on 3/20/2018.
 */

public class SingleToast {
    private static Toast mToast;
    public static void show(Context context, String text, int duration) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }
}
