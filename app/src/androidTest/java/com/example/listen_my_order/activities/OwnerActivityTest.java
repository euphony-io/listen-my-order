package com.example.listen_my_order.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.listen_my_order.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OwnerActivityTest {

    @Rule
    public ActivityTestRule<OwnerActivity> ownerActivityActivityTestRule = new ActivityTestRule(OwnerActivity.class);

    @Test
    public void testExportMenu(){
        onView(withId(R.id.btn_export_menu)).check(matches(withText("Export\nMenu")));
        onView(withId(R.id.btn_export_menu)).perform(click()).check(matches(withText("Exporting\nMenu...")));
    }

    @Test
    public void testAddMenu(){
        onView(withId(R.id.btn_add)).perform(click());
    }

    @Test
    public void testGoBack(){
        onView(withId(R.id.iv_back)).perform(click());

    }
}