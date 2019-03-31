package com.example.test;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class FizzBuzzActivityTest {

    private FizzBuzzActivity fizzBuzzActivity;

    @Rule
    public ActivityTestRule<FizzBuzzActivity> activityTestRule =
            new ActivityTestRule<>(FizzBuzzActivity.class);

    @Test
    public void testTextMultiple3(){
        onView(withId(R.id.etNumber))
                .perform(typeText("36"),closeSoftKeyboard());

        onView(withId(R.id.btExecute))
                .perform(click());

        onView(withId(R.id.tvMessage))
                .check(matches(withText(FizzBuzz.FIZZ)));
    }

    @Before
    public void setUp() throws Exception {
        fizzBuzzActivity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        fizzBuzzActivity = null;
    }
}