package com.jersey.smartmi.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by Vishnu on 23/02/16.
 */
public class MyScrollView extends ObservableScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return true;
    }
}
