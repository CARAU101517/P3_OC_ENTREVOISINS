package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc} get the list of all neighbour
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * get a new list for favorite neighbours
     * @return
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighboursList = new ArrayList<>();
        for( Neighbour neighbour : neighbours){
            if (neighbour.getFavorite()) {
                favoriteNeighboursList.add(neighbour);
            }
        }
        return favoriteNeighboursList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

   /**
     * {@inheritDoc}
     * permet de modifier le neighbour dans la liste des voisins
     * @param neighbour
     */
    @Override
    public void modifyNeighbour(Neighbour neighbour) {
        neighbours.set(neighbours.indexOf(neighbour), neighbour);
    }

}
