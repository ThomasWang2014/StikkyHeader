package it.carlom.stikkyheader.core;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThomasWang on 2016/11/28.
 */

public class RootLayout extends FrameLayout {
    private List<View> mClickCandidates = new ArrayList<>();

    private ListView mScrollableView;

    private ViewGroup mHeaderView;

    private GestureDetectorCompat mGestureDetectorCompat;

    public RootLayout(Context context) {
        super(context);
        init();
    }

    public RootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RootLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                boolean result = resolveClick(e);
                return result;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mScrollableView.scrollListBy((int) distanceY);
                } else {

                }
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mScrollableView.fling((int) -velocityY);
                } else {

                }
                return false;
            }
        });
    }

    private boolean resolveClick(MotionEvent e) {
        for (View candidate : mClickCandidates) {
            Rect outRect = new Rect();
            candidate.getHitRect(outRect);
            if (outRect.contains((int) e.getX(), (int) e.getY())) {
                return candidate.performClick();
            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mGestureDetectorCompat.onTouchEvent(ev);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return mGestureDetectorCompat.onTouchEvent(event);
//    }

    public void setScrollableView(ListView scrollView) {
        this.mScrollableView = scrollView;
    }

    public void addClickCandidates(View candidate) {
        mClickCandidates.add(candidate);
    }

    public void setHeaderView(ViewGroup headerView) {
        this.mHeaderView = headerView;
    }
}
