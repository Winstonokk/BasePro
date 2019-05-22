package com.barnettwong.basepro.mvp.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;


/**
 * 根据宽度自适应
 */
public class ScaleTextView extends AppCompatTextView {
    public ScaleTextView(Context context) {
        this(context, null, 0);
    }

    public ScaleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {@Override public void onGlobalLayout() {
            //测量字符串的长度
            float measureWidth = getPaint().measureText(String.valueOf(getText()));
            //得到TextView 的宽度
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            //当前size大小
            float textSize = getTextSize();
            if (width < measureWidth) {
                textSize = (width / measureWidth) * textSize;
            }
            //注意，使用像素大小设置
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        });
    }
}
