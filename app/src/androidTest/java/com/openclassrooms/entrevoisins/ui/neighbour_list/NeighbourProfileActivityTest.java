package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class NeighbourProfileActivityTest {

   @Rule
   public ActivityTestRule <NeighbourProfileActivity> mActivityTestRule =
           new ActivityTestRule <NeighbourProfileActivity> (NeighbourProfileActivity.class);

   private NeighbourProfileActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunchOfNeighbourProfileActivity() {

        View view = mActivity.findViewById(R.id.avatarUrl);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}