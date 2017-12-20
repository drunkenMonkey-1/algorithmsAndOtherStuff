package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyList;
import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim algorithm class with default implementation for Prim's relax operation.
 */
public class PrimAlgorithm implements IAlgorithm<AdjacencyStructure<Long>>{

    private AdjacencyStructure<Long> _adjStruct;
    private Vertex _start;

    private IRelaxOperation _primRelax = new IRelaxOperation() {
        @Override
        public void relax(PriorityQueue<Vertex> queue, HashMap<Vertex, Vertex> parentVertices, Vertex current, Vertex next) {
            /* If there was an edge found, with a lower weight, than the current costs of the vertex it is
             * selected to be the currently smallest connection in the min. spanning tree */
            if(_adjStruct.getWeight(current, next) < next.getProperty()){
                next.setProperty(_adjStruct.getWeight(current, next));
                /* Important - cost update won't affect the priority queue if the vertex isn't replaced */
                queue.remove(next);
                queue.add(next);
                /* Parent update: if there is a shorter edge, the parent from which the shorter connection is possible
                 * needs to be added to the parent list (for the tree extraction) */
                parentVertices.put(next, current);
            }
        }

        @Override
        public AdjacencyStructure<Long> extract(HashMap<Vertex, Vertex> parentVertices) {
            AdjacencyStructure<Long> _outTree = new AdjacencyList<>(-1L);
            List<Vertex> vList = _adjStruct.getVertices();
            /* Simply get all vertices and their neighbours and connect them in the output tree */
            for(Vertex treeVertex : vList){
                _outTree.addVertex(treeVertex);
                Vertex parent = parentVertices.get(treeVertex);
                if(parent != null){
                    _outTree.addVertex(parent);
                    _outTree.doubleConnectVertices(treeVertex, parent, _adjStruct.getWeight(treeVertex, parent));
                }
            }
            return _outTree;
        }
    };

    /**
     * Prim's algorithm for finding the minimal spanning tree
     * @param adjacencyStructure Adjacency Structure that is searched for a minimal spanning tree
     * @param start Start vertex - could be any vertex
     */
    public PrimAlgorithm(AdjacencyStructure<Long> adjacencyStructure, Vertex start) {
        _adjStruct = adjacencyStructure;
        _start = start;
    }

    /**
     * Execute the prim algorithm based on dijkstra's algorithm. Default prim implementation used.
     * Use Dijkstra Algorithm's IRelaxOperation Interface to make an own version of the prim implementation
     * @return The minimal spanning tree as a adjacency list
     * @throws GraphRequirementException
     */
    @Override
    public AdjacencyStructure<Long> operate() throws GraphRequirementException {
        /* Since Prim and Dijkstra are pretty much the same algorithms, a new relax and extract method is
         * passed. The rest is exactly the same like Dijkstra's algorithm */
        DijkstraAlgorithm dja = new DijkstraAlgorithm(_adjStruct, _primRelax, _start, null);
        return dja.operate();
    }
}
