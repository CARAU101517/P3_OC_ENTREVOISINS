package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourProfileActivity;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    NeighbourProfileActivity neighbourProfileActivity;
    NeighbourProfileActivity mName;
    private List<Neighbour> mNeighbours;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighboursListWithSuccess() {
        List<Neighbour> favoriteNeighboursList = new ArrayList<>();
        List<Neighbour> neighbours = service.getFavoriteNeighbours();
        List<Neighbour> expectedFavoriteNeighbours = favoriteNeighboursList;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbours.toArray()));
    }

    @Test
    public void getProfilePageWithSuccess() {
        assertNotNull(neighbourProfileActivity);
    }

    @Test
    public void getNameInNeighbourProfileActivity() {
        assertNotNull(mName);
    }

    @Test
    public void getCorrectNameForCorrectNeighbour() {
        Neighbour neighbour = mNeighbours.get(0);
        assertEquals("Caroline", neighbour.getName());
    }

}

