
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourProfileActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService = new DummyNeighbourApiService();
    private List<Neighbour> mNeighbours = mApiService.getFavoriteNeighbours();
    private Neighbour neighbourProfile = new Neighbour();


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click an item, the Profile Page is launching
     */

    @Test
    public void LaunchOfNeighbourProfileActivity() {
        // Given : When we click on an Item
        onView(allOf(withId(R.id.list_neighbours), withText("My neighbours")))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Then : The Profile Page is opening
        onView(withId(R.id.avatarUrlProfile)).check(matches(isDisplayed()));
    }

    @Test
    public void WhenNeighbourProfileIsOpen_NameShouldNotBeEmpty() {
        View view = mActivity.findViewById(R.id.name);
        assertNotNull(view);
    }

    @Test
    public void MyFavoriteList_ShouldShows_OnlyFavorites() {
        neighbourProfile.setFavorite(true);
        // Given : we clear the favorites list We add 2 neighbours in the Favorites List
        onView(withId(R.id.container)).perform((swipeRight()));
        mNeighbours.clear();
        // We add 5 neighbours in the Favorites List
        onData(mApiService.modifyNeighbour(neighbourProfile))
                .atPosition(0+2+4+6+8);
        onView(withId(R.id.container)).perform((swipeRight()));
        // Then : we check the number of favorites in the favorites list
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT=5));
    }
}