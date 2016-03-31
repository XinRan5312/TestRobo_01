package com.boomstack.testrobo_01;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by bjhl on 16/3/31.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {
    private MainActivity mainActivity;
    private TextView tv;
    private Button btn;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        tv = (TextView) mainActivity.findViewById(R.id.tv);
        btn = (Button) mainActivity.findViewById(R.id.btn);
    }

    @Test
    public void testInit() {
        Assert.assertNotNull(mainActivity);
        Assert.assertNotNull(tv);
        Assert.assertNotNull(btn);
        Assert.assertEquals("com.boomstack.testrobo_01", mainActivity.getPackageName());
        Assert.assertEquals("Hello World!", tv.getText().toString());
    }

    @Test
    public void testBtn() {
        btn.performClick();
        Assert.assertEquals("Hola", tv.getText().toString());
    }

}