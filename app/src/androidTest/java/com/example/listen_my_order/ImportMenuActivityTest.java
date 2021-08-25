package com.example.listen_my_order;

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
    public void testEmpty() {

    }
}
