package com.example.admin.shopnail.View;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.shopnail.R;

import java.lang.reflect.Field;

public class NailActionBar {
    private Activity mActivity;
    private int mBackgroundResId = Const.EmptyValues.BACKGROUND_RES_ID;
    private int mTitleColor = Const.EmptyValues.TITLE_COLOR;
    private int mTitleResId = Const.EmptyValues.TITLE_TEXT_RES_ID;
    private boolean mIsMultiLine = Const.DefaultValues.ACTIONBAR_MULTILINE;
    private int mCurrentHeightActionbar = Const.DefaultValues.DEFAULT_HEIGHT_ACTIONBAR_VALUE;
    private boolean mIsScaleTitleSize = Const.DefaultValues.ACTIONBAR_SCALE_TITLE;
    private boolean mIsTitleEllipsize = Const.DefaultValues.DEFAULT_TITLE_ELLIPSIZE;
    private View mActionBarLayout;

    private NailFitTextView mTextTitleView;

    private String mTitleString = null;

    /**
     *
     * @param builder
     */
    private NailActionBar(final Builder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("builder is null.");
        }
        setParameters(builder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (mActivity.getActionBar() == null) {
                return;
            }
        }

        mActivity.getActionBar().setDisplayShowCustomEnabled(true);
        mActivity.getActionBar().setDisplayHomeAsUpEnabled(false);
        mActivity.getActionBar().setDisplayShowHomeEnabled(false);
        mActivity.getActionBar().setDisplayShowTitleEnabled(false);
        mActivity.getActionBar().setDisplayUseLogoEnabled(false);
        mActivity.getActionBar().setCustomView(R.layout.nail_action_bar);

        mActionBarLayout = mActivity.getActionBar().getCustomView();
        mTextTitleView = (NailFitTextView) mActionBarLayout.findViewById(R.id.textTitle);
    }

    private void setParameters(Builder builder) {
        mActivity = builder.mActivity;
        mBackgroundResId = builder.mBackgroundResId;
        mTitleColor = builder.mTitleColor;
        mTitleResId = builder.mTitleResId;
        mIsMultiLine = builder.mIsMultiLine;
        mIsScaleTitleSize = builder.mIsScaleTitleSize;
        mIsTitleEllipsize = builder.mIsTitleEllipsize;
        mTitleString = builder.mTitleString;
    }

    private void resizeActionBarHeight(int dp) {
        int px = (int) (dp * mActivity.getResources().getDisplayMetrics().density);
        View v = mActivity.getWindow().getDecorView();
        int actionBarId = mActivity.getResources().getIdentifier("action_bar", "id", "android");
        ViewGroup actionBarView = (ViewGroup) v.findViewById(actionBarId);
        try {
            Field f = actionBarView.getClass().getSuperclass().getDeclaredField("mContentHeight");
            f.setAccessible(true);
            f.set(actionBarView, px);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param textView
     * @param dp
     * @param oGlL
     */
    private void autoFitTextView(TextView textView, int dp,
                                 ViewTreeObserver.OnGlobalLayoutListener oGlL) {
        textView.setEllipsize(null);
        textView.setSingleLine(false);
        Layout textViewLayout = textView.getLayout();
        if (textViewLayout != null) {
            int lines = textViewLayout.getLineCount();
            if (lines > 1) {
                textView.setMaxLines(textView.getLineCount());
                mCurrentHeightActionbar += lines * dp;
                resizeActionBarHeight(mCurrentHeightActionbar);
            }
        }
    }

    public void generate() {
        if ((mBackgroundResId != Const.EmptyValues.BACKGROUND_RES_ID)
                && mActionBarLayout != null) {
            mActionBarLayout.setBackgroundResource(mBackgroundResId);
        }

        if (mTextTitleView != null) {
            if ((mTitleColor != Const.EmptyValues.TITLE_COLOR)) {
                mTextTitleView.setTextColor(mTitleColor);
            }

            if (mTitleResId != Const.EmptyValues.TITLE_TEXT_RES_ID) {
                mTextTitleView.setText(mTitleResId);
            }

            if (mTitleString != null) {
                mTextTitleView.setText(mTitleString);
            }

            mTextTitleView.seResizable(false);
            if (!TextUtils.isEmpty(mTextTitleView.getText())) {
                if (mIsTitleEllipsize) {
                    mTextTitleView.setEllipsize(TextUtils.TruncateAt.END);
                    mTextTitleView.setSingleLine(true);
                    mTextTitleView.setMaxLines(1);
                } else if (mIsMultiLine) {
                    final int dp = 20;
                    mTextTitleView.getViewTreeObserver().addOnGlobalLayoutListener(
                            new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    autoFitTextView(mTextTitleView, dp, this);
                                    mTextTitleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                                }
                            });
                } else if (mIsScaleTitleSize) {
                    mTextTitleView.seResizable(true);
                }
            }
        }
    }

    public static class Builder {
        private Activity mActivity;
        private int mBackgroundResId = Const.EmptyValues.BACKGROUND_RES_ID;
        private int mTitleColor = Const.EmptyValues.TITLE_COLOR;
        private int mTitleResId = Const.EmptyValues.TITLE_TEXT_RES_ID;
        private boolean mIsMultiLine = Const.DefaultValues.ACTIONBAR_MULTILINE;
        private boolean mIsScaleTitleSize = Const.DefaultValues.ACTIONBAR_SCALE_TITLE;
        private boolean mIsTitleEllipsize = Const.DefaultValues.DEFAULT_TITLE_ELLIPSIZE;
        private String mTitleString = null;

        /**
         * @param activity        ActionBar
         * @param titleResId
         * @param titleColorResId
         * @param iconVisibility
         */
        Builder(final Activity activity, final int titleResId, final int titleColorResId, final boolean iconVisibility) {
            if (activity == null) {
                throw new IllegalArgumentException("activity is null.");
            }

            mActivity = activity;
            mTitleResId = titleResId;
            mTitleColor = activity.getResources().getColor(titleColorResId);
        }

        public void setBackgroundResId(int backgroundResId) {
            mBackgroundResId = backgroundResId;
        }

        public void setTitleColor(int titleColorResId) {
            mTitleColor = mActivity.getResources().getColor(titleColorResId);
        }

        public void setTitle(int titleResId) {
            mTitleResId = titleResId;
        }

        public void setTitle(String titleString) {
            mTitleString = titleString;
        }

        public void setScaleTitle(boolean isScaleTextView) {
            mIsScaleTitleSize = isScaleTextView;
        }


        public NailActionBar build() {
            return new NailActionBar(this);
        }
    }

    private static class Const {
        private static class EmptyValues {
            static final int BACKGROUND_RES_ID = -1;
            static final int TITLE_COLOR = -1;
            static final int TITLE_TEXT_RES_ID = -1;
        }

        private static class DefaultValues {
            static final boolean ICON_VISIBILITY = true;
            static final boolean ACTIONBAR_MULTILINE = true;
            static final int DEFAULT_HEIGHT_ACTIONBAR_VALUE = 48;
            static final boolean ACTIONBAR_SCALE_TITLE = false;
            static final boolean DEFAULT_TITLE_ELLIPSIZE = false;
        }
    }

}
