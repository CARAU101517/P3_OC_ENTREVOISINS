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
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> listOutput = new ArrayList<>();
        for( Neighbour neighbour : neighbours){
            if ( neighbour.getFavorite())
                listOutput.add(neighbour);
        }
        return listOutput;
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
