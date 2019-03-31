package com.example.test;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {


    MainActivity activity = null;

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


    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}