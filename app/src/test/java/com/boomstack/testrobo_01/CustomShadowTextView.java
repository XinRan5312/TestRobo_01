package com.boomstack.testrobo_01;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowTextView;

/**
 * Created by bjhl on 16/3/31.
 */
@Implements(MyTextView.class)
public class CustomShadowTextView extends ShadowTextView {

    @Implementation
    public static int myMethod() {
        return 90;
    }
}
