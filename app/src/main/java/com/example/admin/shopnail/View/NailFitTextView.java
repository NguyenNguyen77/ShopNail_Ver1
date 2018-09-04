package com.example.admin.shopnail.View;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class NailFitTextView extends AppCompatTextView {
    private Paint mPaint = new Paint();

    private float mOrgTextSize = 1f;

    private boolean mResizeText = true;

    public NailFitTextView(Context context) {
        super(context);
        init();
    }

    public NailFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NailFitTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mOrgTextSize = getTextSize();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!mResizeText) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int parentWidth = View.MeasureSpec.getSize(widthMeasureSpec);

        float textSize = mOrgTextSize;
        String text = getText().toString();
        mPaint.setTextSize(textSize);
        int leftRightPadding = getPaddingLeft() + getPaddingRight();
        float textWidth = mPaint.measureText(text) + leftRightPadding;

        while (textWidth > parentWidth) {
            textSize--;
            mPaint.setTextSize(textSize);
            textWidth = mPaint.measureText(text) + leftRightPadding;
            if (textSize == 1) {
                break;
            }
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        switch (View.MeasureSpec.getMode(widthMeasureSpec)) {
            case View.MeasureSpec.EXACTLY:
                textWidth = parentWidth;
                break;
            case View.MeasureSpec.AT_MOST:
                if (parentWidth < textWidth) {
                    textWidth = parentWidth;
                }
                break;
            case View.MeasureSpec.UNSPECIFIED:
            default:
                break;
        }

        float textHeight = mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top;
        setMeasuredDimension((int) textWidth, (int) textHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void seResizable(boolean resizable) {
        mResizeText = resizable;
    }
}
