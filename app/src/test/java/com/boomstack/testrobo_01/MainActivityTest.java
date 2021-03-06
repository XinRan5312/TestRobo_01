package com.boomstack.testrobo_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowTextView;
import org.robolectric.util.ActivityController;

/**
 * Created by wangkangfei on 16/3/31.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, shadows = {CustomShadowTextView.class})
public class MainActivityTest {
    private MainActivity mainActivity;
    private TextView tv;
    private Button btn;
    private MyTextView mtv;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        tv = (TextView) mainActivity.findViewById(R.id.tv);
        btn = (Button) mainActivity.findViewById(R.id.btn);
        mtv = (MyTextView) mainActivity.findViewById(R.id.tv_my);
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

    @Test
    public void testLifecycle() {
        ActivityController controller = Robolectric.buildActivity(MainActivity.class).create().start();
        Activity activity = (Activity) controller.get();
        Assert.assertNotNull(activity);
        controller.resume();
        Assert.assertEquals("Hello World!", tv.getText().toString());
        btn.performClick();
        Assert.assertEquals("Hola", tv.getText().toString());
    }

    @Test
    public void testIntent() {
        tv.performClick();
        Intent exceptedIntent = new Intent(mainActivity, OtherActivity.class);
        //real activity
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(exceptedIntent, actualIntent);
    }

    @Test
    public void testTextView() {
        ShadowTextView stv = Shadows.shadowOf(tv);
        String innerText = stv.innerText();
        Assert.assertEquals("Hello World!", innerText);
    }

    @Test
    public void testCustomTextView() {
        ShadowTextView sv = Shadows.shadowOf(mtv);
        CustomShadowTextView cstv = (CustomShadowTextView) sv;
        Assert.assertEquals("mytextview", cstv.innerText());
    }
}