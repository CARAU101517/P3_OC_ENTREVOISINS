package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.junit.Test;

import static org.junit.Assert.*;

public class NeighbourProfileDetailsTest {


    private NeighbourProfileActivity neighbourProfileActivity;


    @Test
    public void getProfileDetailsWithSuccess() {
        NeighbourProfileActivity neighbourProfile = new NeighbourProfileActivity();
                assertNotNull(neighbourProfile);

    }

}