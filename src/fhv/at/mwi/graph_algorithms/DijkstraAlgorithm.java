package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.*;

/**
 * Dijkstra Algorithm class. Constructor takes parameters and operate
 * executes the algorithm.
 */
public class DijkstraAlgorithm implements IAlgorithm<List<Vertex>> {

    private AdjacencyStructure<Long> _adjStruct;
    private Vertex _start, _end;

    /**
     * Set the initial parameters the Dijkstra algorithm is performed on
     * @param adjacencyStructure Graph representation structure for the dijkstra algorithm to iterate through
     * @param start Start vertex
     * @param end End vertex
     */
    public DijkstraAlgorithm(AdjacencyStructure<Long> adjacencyStructure, Vertex start, Vertex end){
        _adjStruct = adjacencyStructure;
        _start = start;
        _end = end;
    }

    /**
     * Execute dijkstra on the previously passed parameters in the constructor
     * @return Shortest path from start to end like defined in the constructor as a list of vertices
     */
    @Override
    public List<Vertex> operate() {
        int verticesNo = _adjStruct.getVertices().size();
        /* Save the parent for every vertex for extracting the path */
        HashMap<Vertex, Vertex> _parentVertices = new HashMap<>(verticesNo);
        /* Priority queue with a comparator for comparing vertices by their cost */
        PriorityQueue<Vertex> _queue = new PriorityQueue<>(verticesNo,
                (o1, o2) -> (Long.compare(o1.getProperty(), o2.getProperty())));

        /* Init all vertices with infinite costs and the start vertex with costs of 0
        *  and save all vertices to the queue. */
        init(_queue);
        /* Start vertex can't have a parent */
        _parentVertices.put(_start, null);
        /* If the queue isn't empty, it means that there are still nodes left, that
        *  might lead to the destination vertex */
        while(!_queue.isEmpty()){
            /* According to the comparator the vertex with the smallest cost is dequeued */
            Vertex current = _queue.remove();
            /* If the current vertex is the end vertex, there is no need to continue the search */
            if(current != _end) {
                /* Visit all neighbours outgoing from the current vertex */
                for (Vertex neighbourVertex : _adjStruct.getOutgoingVertices(current)) {
                    /* If the neighbour hasn't been visited yet it's still in the queue */
                    if(_queue.contains(neighbourVertex)){
                        /* Calculate new costs by adding the weight to the costs of the parent (current) */
                        long newCosts = current.getProperty() + _adjStruct.getWeight(current, neighbourVertex);
                        /* Relaxation operation: if a lower cost from the current vertex is possible it is set
                        *  as a parent and the costs are overwritten */
                        if(newCosts < neighbourVertex.getProperty()){
                            neighbourVertex.setProperty(newCosts);
                            _parentVertices.put(neighbourVertex, current);
                        }
                    }
                }
            }
        }

        List<Vertex> _path = new LinkedList<>();
        _path.add(_end);
        Vertex pathCurrent = _end;
        /* Null means, that the start vertex was reached */
        while(_parentVertices.get(pathCurrent) != null){
            /* Starting from the end the parent node is set as current, so the previously calculated shortest cost
            *  node is visited next */
            pathCurrent = _parentVertices.get(pathCurrent);
            /* LIFO like queue */
            _path.add(0, pathCurrent);
        }

        return _path;
    }

    /* Basic dijkstra initialization with infinite costs */
    private void init(PriorityQueue<Vertex> Q){
        for(Vertex v : _adjStruct.getVertices()){
            v.setProperty(Long.MAX_VALUE);
            if(v==_start){
                v.setProperty(0L);
            }
            Q.add(v);
        }
    }
}
