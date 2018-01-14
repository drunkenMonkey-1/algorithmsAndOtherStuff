package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyList;
import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.*;

/**
 * Dijkstra Algorithm class. Constructor takes parameters and operate
 * executes the algorithm.
 */
public class DijkstraAlgorithm implements IAlgorithm<AdjacencyStructure<Long>> {

    private AdjacencyStructure<Long> _adjStruct;
    private HashMap<Vertex, Vertex> _parentVertices;
    private Vertex _start, _end;
    private IRelaxOperation _relaxInterface;

    private List<Vertex> _simpliefiedOutPath;

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

        /* Default dijkstra relax and extract operation */
        _relaxInterface = new IRelaxOperation() {
            @Override
            public void relax(PriorityQueue<Vertex> queue, HashMap<Vertex, Vertex> parentVertices, Vertex current, Vertex next) {
                /* Calculate new costs by adding the weight to the costs of the parent (current) */
                long newCosts = current.getProperty() + _adjStruct.getWeight(current, next);
                /* Relaxation operation: if a lower cost from the current vertex is possible it is set
                 *  as a parent and the costs are overwritten */
                if(newCosts < next.getProperty()){
                    next.setProperty(newCosts);
                    /* Important - cost update won't affect the priority queue if the vertex isn't replaced */
                    queue.add(next);
                    /* Parent update: if there is a shorter path, the parent from which the shorter path is possible
                     * needs to be added to the parent list (for the path extraction) */
                    parentVertices.put(next, current);
                }
            }

            @Override
            public AdjacencyStructure<Long> extract(HashMap<Vertex, Vertex> parentVertices) {
                List<Vertex> _path = new LinkedList<>();
                _path.add(_end);
                Vertex pathCurrent = _end;

                AdjacencyStructure<Long> _outTree = new AdjacencyList<>(-1L);
                _outTree.addVertex(pathCurrent);

                /* Null means, that the start vertex was reached */
                while(parentVertices.get(pathCurrent) != null){
                    /* Starting from the end the parent vertex is set as current, so the previously calculated shortest cost
                     *  vertex is visited next */
                    Vertex tempParentVertex = pathCurrent;
                    pathCurrent = parentVertices.get(pathCurrent);

                    _outTree.addVertex(pathCurrent);
                    _outTree.doubleConnectVertices(tempParentVertex, pathCurrent, _adjStruct.getWeight(tempParentVertex, pathCurrent));

                    /* LIFO like queue, so adding at 0 will shift the previously added elements*/
                    _path.add(0, pathCurrent);
                }
                _simpliefiedOutPath = _path;
                return _outTree;
            }
        };
    }

    /**
     * Alternative constructor, to pass an additional interface for relaxing vertices.
     * @param adjacencyStructure Graph representation structure for the dijkstra algorithm to iterate through
     * @param relaxInterface An interface to pass an own implementation for extracting and relaxing vertices
     *                       Could be useful for Prim's minimal spanning tree algorithm or for finding the longest
     *                       path in a graph
     * @param start Start vertex
     * @param end End vertex
     * @throws GraphRequirementException Throws this exception, when the relaxInterface is null
     */
    public DijkstraAlgorithm(AdjacencyStructure<Long> adjacencyStructure, IRelaxOperation relaxInterface, Vertex start, Vertex end) throws GraphRequirementException {
        this(adjacencyStructure, start, end);
        if(_relaxInterface == null){
            throw new GraphRequirementException("Relax interface for dijkstra algorithm, can't be null");
        }
        _relaxInterface = relaxInterface;
    }


    /**
     * Execute dijkstra on the previously passed parameters in the constructor
     * @return Shortest path from start to end like defined in the constructor as a list of vertices
     */
    @Override
    public  AdjacencyStructure<Long> operate() {
        int verticesNo = _adjStruct.getVertices().size();
        /* Save the parent for every vertex for extracting the path */
        _parentVertices = new HashMap<>(verticesNo);
        /* Priority queue with a comparator for comparing vertices by their cost */
        PriorityQueue<Vertex> queue = new PriorityQueue<>(verticesNo,
                (o1, o2) -> (Long.compare(o1.getProperty(), o2.getProperty())));

        /* Init all vertices with infinite costs and the start vertex with costs of 0
        *  and save all vertices to the queue. */
        init(queue);
        /* Start vertex can't have a parent */
        _parentVertices.put(_start, null);
        /* If the queue isn't empty, it means that there are still nodes left, that
        *  might lead to the destination vertex */
        while(!queue.isEmpty()){
            /* According to the comparator the vertex with the smallest cost is dequeued */
            Vertex current = queue.remove();
            /* If the current vertex is the end vertex, there is no need to continue the search */
            if(current != _end) {
                /* Visit all neighbours outgoing from the current vertex */
                for (Vertex neighbourVertex : _adjStruct.getOutgoingVertices(current)) {
                    /* If the neighbour hasn't been visited yet it's still in the queue */
                    if(queue.contains(neighbourVertex)){
                        _relaxInterface.relax(queue, _parentVertices, current, neighbourVertex);
                    }
                }
            }
        }
        return _relaxInterface.extract(_parentVertices);
    }

    /**
     * Get the path in the graph as a list of vertices
     * @return List of vertices of the path, found with dijkstra algorithm
     */
    public List<Vertex> getSimplifiedPath(){
        return _simpliefiedOutPath;
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
