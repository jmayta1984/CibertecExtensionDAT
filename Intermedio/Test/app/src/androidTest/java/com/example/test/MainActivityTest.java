package com.example.test;


import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {


    MainActivity activity = null;

    Instrumentation.ActivityMonitor monitor
            = getInstrumentation().addMonitor(SecondActivity.class.getName(),null,false);

    // Permite inicializar MainActivity
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();

    }

    @Test
    public void testLaunch() {
        View view = activity.findViewById(R.id.tvMessage);
        assertNotNull(view);
    }

    @Test
    public void testLauncSecondActivityOnButtonClick(){
        assertNotNull(activity.findViewById(R.id.btGoTo));

        onView(withId(R.id.btGoTo)).perform(click());

        Activity secondActivity = getInstrumentation()
                .waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(secondActivity);

        secondActivity.finish();


    }


    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}