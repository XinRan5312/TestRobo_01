package com.boomstack.testrobo_01;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by bjhl on 16/3/31.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(30, 10);
    }
    public static int myMethod(){
        return 10;
    }
}
