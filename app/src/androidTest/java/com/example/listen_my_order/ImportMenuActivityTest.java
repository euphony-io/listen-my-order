package com.example.listen_my_order;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.listen_my_order.activities.ImportMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ImportMenuActivityTest {

    @Rule
    public ActivityTestRule<ImportMenuActivity> importMenuActivityTestRule = new ActivityTestRule<>(ImportMenuActivity.class);

    @Test
    public void testStopImport() {
        onView(withId(R.id.btn_set_import))
                .perform(click())
                .check(matches(withText(R.string.btn_start_listen)));
    }
}
