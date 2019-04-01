package com.xjp.wonderfulcinema.mvp.uiutil;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wang
 */
public class ViewPagerLayoutManager extends LinearLayoutManager {

    private static final String TAG = "ViewPagerLayoutManager";
    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;
    private RecyclerView mRecyclerView;
    private int mDrift;
    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        public void onChildViewAttachedToWindow(View view) {
            if (ViewPagerLayoutManager.this.mOnViewPagerListener != null && ViewPagerLayoutManager.this.getChildCount() == 1) {
                ViewPagerLayoutManager.this.mOnViewPagerListener.onInitComplete();
            }

        }

        public void onChildViewDetachedFromWindow(View view) {
            if (ViewPagerLayoutManager.this.mDrift >= 0) {
                if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                    ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(true, ViewPagerLayoutManager.this.getPosition(view));
                }
            } else if (ViewPagerLayoutManager.this.mOnViewPagerListener != null) {
                ViewPagerLayoutManager.this.mOnViewPagerListener.onPageRelease(false, ViewPagerLayoutManager.this.getPosition(view));
            }

        }
    };

    public ViewPagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        this.init();
    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.init();
    }

    private void init() {
        this.mPagerSnapHelper = new PagerSnapHelper();
    }

    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        this.mPagerSnapHelper.attachToRecyclerView(view);
        this.mRecyclerView = view;
        this.mRecyclerView.addOnChildAttachStateChangeListener(this.mChildAttachStateChangeListener);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    public void onScrollStateChanged(int state) {
        switch (state) {
            case 0:
                View viewIdle = this.mPagerSnapHelper.findSnapView(this);
                int positionIdle = this.getPosition(viewIdle);
                if (this.mOnViewPagerListener != null && this.getChildCount() == 1) {
                    this.mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == this.getItemCount() - 1);
                }
                break;
            case 1:
                View viewDrag = this.mPagerSnapHelper.findSnapView(this);
                this.getPosition(viewDrag);
                break;
            case 2:
                View viewSettling = this.mPagerSnapHelper.findSnapView(this);
                this.getPosition(viewSettling);
        }

    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }


    public interface OnViewPagerListener {
        void onInitComplete();

        void onPageRelease(boolean var1, int var2);

        void onPageSelected(int var1, boolean var2);
    }
}
