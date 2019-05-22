package com.barnettwong.basepro.mvp.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * RecyclerView  的分割线
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private boolean mShowLastLine; 
    private int mSpanSpace = 2; 
    private int mLeftPadding; 
    private int mRightPadding; 
    
    public LinearItemDecoration(int span,int leftPadding,int rightPadding,int color,boolean show){ 
        mSpanSpace = span; 
        mShowLastLine = show; 
        mLeftPadding = leftPadding; 
        mRightPadding = rightPadding; 
        mDivider = new ColorDrawable(color);
    } 
    
    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int count = mShowLastLine ? parent.getAdapter().getItemCount() : parent.getAdapter().getItemCount() - 1; 
        if (isVertical(parent)) { 
            if (parent.getChildAdapterPosition(view) < count) { 
                outRect.set(0, 0, 0, mSpanSpace); 
            } else { 
                outRect.set(0, 0, 0, 0); 
            } 
        } else { 
            if (parent.getChildAdapterPosition(view) < count) { 
                outRect.set(0, 0, mSpanSpace, 0); 
            } else { 
                outRect.set(0, 0, 0, 0); 
            } 
        } 
    } 
    
    private boolean isVertical(RecyclerView parent) { 
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager(); 
        if (layoutManager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) layoutManager) .getOrientation(); 
            return orientation == LinearLayoutManager.VERTICAL; 
        } 
        return false; 
    } 
            
    @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (isVertical(parent)) { 
            drawVertical(c, parent); 
        } else { 
            drawHorizontal(c, parent); 
        } 
    } 
    
    private void drawVertical(Canvas c, RecyclerView parent) { 
        final int left = parent.getPaddingLeft() + mLeftPadding; 
        final int right = parent.getWidth() - parent.getPaddingRight() - mRightPadding; 
        final int childCount = parent.getChildCount(); 
        for (int i = 0; i < childCount; i++) { 
            final View child = parent.getChildAt(i); 
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child .getLayoutParams(); 
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child)); 
            final int bottom = top + mSpanSpace; int count = mShowLastLine ? parent.getAdapter().getItemCount() : parent.getAdapter().getItemCount() - 1; 
            if (i < count) { 
                mDivider.setBounds(left, top, right, bottom); 
                mDivider.draw(c); 
            } else { 
                mDivider.setBounds(left, top, right, top); 
                mDivider.draw(c); 
            } 
        }
    } 
    
    private void drawHorizontal(Canvas c, RecyclerView parent) { 
        final int top = parent.getPaddingTop(); 
        final int bottom = parent.getHeight() - parent.getPaddingBottom(); 
        final int childCount = parent.getChildCount(); 
        for (int i = 0; i < childCount; i++) { 
            final View child = parent.getChildAt(i); 
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child .getLayoutParams(); 
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mSpanSpace; int count = mShowLastLine ? parent.getAdapter().getItemCount() : parent.getAdapter().getItemCount() - 1; 
            if (i < count) { 
                mDivider.setBounds(left, top, right, bottom); 
                mDivider.draw(c); 
            } 
        } 
    } 
    
    /** 
    * Builder模式 
    * */ 
    public static class Builder{ 
    
        private Context mContext;
        private Resources mResources;
        private int mSpanSpace; 
        private boolean mShowLastLine; 
        private int mLeftPadding; 
        private int mRightPadding; 
        private int mColor; 
        
        public Builder(Context context){ 
            mContext = context; 
            mResources = context.getResources(); 
            mSpanSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1f, context.getResources().getDisplayMetrics());
            mLeftPadding = 0; 
            mRightPadding = 0; 
            mShowLastLine = false; 
            mColor = Color.BLACK;
        } 
        
        /** 
        * 设置分割线宽（高）度 
        */ 
        public Builder setSpan(float pixels) { 
            mSpanSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.getDisplayMetrics()); 
            return this;        
        } 
        
        /** 
        * 设置分割线宽（高）度 
        */ 
        public Builder setSpan(@DimenRes int resource) {
            mSpanSpace = mResources.getDimensionPixelSize(resource); 
            return this; 
        } 
        
        /** 
        * 设置左右间距 
        */ 
        public Builder setPadding(float pixels) { 
            setLeftPadding(pixels); 
            setRightPadding(pixels); 
            return this; 
        } 
        
        /** 
        * 设置左右间距 
        */ 
        public Builder setPadding(@DimenRes int resource) { 
            setLeftPadding(resource); 
            setRightPadding(resource); 
            return this; 
        } 
        
        /** 
        * 设置左间距 
        */ 
        public Builder setLeftPadding(float pixelPadding) { 
            mLeftPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixelPadding, mResources.getDisplayMetrics()); 
            return this; 
        } 
        
        /** 
        * 设置右间距 
        */ 
        public Builder setRightPadding(float pixelPadding) { 
            mRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixelPadding, mResources.getDisplayMetrics()); 
            return this; 
        } 
        
        /** 
        * 通过资源id设置左间距 
        */ 
        public Builder setLeftPadding(@DimenRes int resource) { 
            mLeftPadding = mResources.getDimensionPixelSize(resource); 
            return this; 
        } 
        
        /** 
        * 通过资源id设置右间距 
        */ 
        public Builder setRightPadding(@DimenRes int resource) { 
            mRightPadding = mResources.getDimensionPixelSize(resource); 
            return this; 
        } 
        
        /** 
        * 通过资源id设置颜色 
        */ 
        public Builder setColorResource(@ColorRes int resource) {
            setColor(ContextCompat.getColor(mContext,resource));
            return this; 
        } 
        
        /** 
        * 设置颜色 
        */ 
        public Builder setColor(@ColorInt int color) {
            mColor = color; 
            return this; 
        } 
        
        /** 
        * 是否最后一条显示分割线 
        * */ 
        public Builder setShowLastLine(boolean show){ 
            mShowLastLine = show; 
            return this; 
        } 
        
        /** 
        * Instantiates a LinearItemDecoration with the specified parameters. 
        * @return a properly initialized LinearItemDecoration instance 
        */ 
        public LinearItemDecoration build() { 
            return new LinearItemDecoration(mSpanSpace,mLeftPadding,mRightPadding,mColor,mShowLastLine); 
        }    
    }   
}
