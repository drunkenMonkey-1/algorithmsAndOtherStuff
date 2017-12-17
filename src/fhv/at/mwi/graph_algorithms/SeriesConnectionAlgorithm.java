package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyList;
import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.List;

/**
 * A small algorithm to search for a path in a graph, that contains all vertices, and
 * only allows max. 2 connections from a vertex to an other vertex. Just like in an
 * electric series connection. The End and Start Vertex are the connection for plus and minus.
 */
public class SeriesConnectionAlgorithm implements IAlgorithm<AdjacencyStructure> {

    private AdjacencyStructure _adStruct;

    /**
     * Simply pass the representation of the graph, where a series circuit needs to be found
     * @param adStruct Adjacency Structure of the graph
     */
    public SeriesConnectionAlgorithm(AdjacencyStructure adStruct){
        _adStruct = adStruct;
    }

    /**
     * Find the circuit based on the graph passed in the constructor
     * @return Adjacency Structure with the correct connected circuit
     * @throws GraphRequirementException If no circuit is possible this exception is thrown
     */
    @Override
    public AdjacencyStructure operate() throws GraphRequirementException {
        /* Get all vertices in the graph */
        List<Vertex> vertices = _adStruct.getVertices();
        AdjacencyStructure<Integer> out = new AdjacencyList<>(-1);
        boolean startReconnected = true;

        /* Init the out adjacency structure by adding all possible vertices */
        for(Vertex vertex : vertices) {
            out.addVertex(vertex);
        }

        /* Choosing a random start vertex (index 0 of all vertices) */
        Vertex current = vertices.get(0), startVertex = current;
        /* The list 'vertices' represents the list of not yet visited vertices. If it is 0 no more vertices are left */
        while(vertices.size() != 0) {
            /* mark the current vertex as visited by removing it from the vertex list */
            vertices.remove(current);
            /* Get all neighbour vertices of the current vertex */
            List<Vertex> neighbourVertices = _adStruct.getOutgoingVertices(current);
            /* iterate through all neighbours by using this variable */
            int validNeighbourCount = 0;
            /* While there are neighbours left and the neighbour was already visited (because it isn't in vertex list)
             * increment the validNeighbourCount variable, to check the same thing for the next adjacent neighbour */
            while(validNeighbourCount < neighbourVertices.size() && !vertices.contains(neighbourVertices.get(validNeighbourCount))) {
                validNeighbourCount++;
            }
            /* If the validNeighbourCount var is in range of the neighbour list indexes the next candidate was found */
            if(validNeighbourCount < neighbourVertices.size()) {
                Vertex selectedNeighbour = neighbourVertices.get(validNeighbourCount);
                out.doubleConnectVertices(current, selectedNeighbour);
                /* Redo the procedure with the selected neighbour, that is visited next */
                current = selectedNeighbour;
            /* If no valid neighbour was found that means, that all adjacent neighbours have already been visited.
            *  That means we need to start again but somewhere else -> a node that hasn't been visited yet. */
            } else{
                if(!vertices.isEmpty()) {
                    /* Since the algorithm is stuck, while there are still vertices left, there is currently no connection
                     * to the start vertex. If at the end there is still no connection and all vertices are visited
                     * that means, that no circuit is possible */
                    startReconnected = false;
                    /* set an unvisited vertex as the current one and try to connect it to the start vertex */
                    current = vertices.get(0);
                    if(_adStruct.verticesConnected(current, startVertex)){
                        out.doubleConnectVertices(current, startVertex);
                        startReconnected = true;
                    } else if(_adStruct.numOfOutgoingNeighbour(current) == 0){
                        throw new GraphRequirementException("No series circuit possible.");
                    }
                } else{
                    /* no reconnection of the previously alternative vertex was made */
                    if(!startReconnected){
                        throw new GraphRequirementException("No series circuit possible.");
                    }
                }
            }
        }
        return out;
    }


    // Spanning Tree
    //        HashSet<Vertex> visited = new HashSet<>();
    //        for(Vertex vertex : vertices){
    //            List<Vertex> neighbourVertices = _adStruct.getOutgoingVertices(vertex);
    //            for(Vertex neighbourVertex : neighbourVertices){
    //                if(!visited.contains(neighbourVertex)){
    //                    out.doubleConnectVertices(vertex, neighbourVertex);
    //                    visited.add(neighbourVertex);
    //                }
    //            }
    //        }
}
