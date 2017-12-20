package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.HashMap;
import java.util.PriorityQueue;

public interface IRelaxOperation {
    /**
     * Relax operation for dijkstra algorithm. Relaxing a vertex means to evaluate how a neighbour node
     * is chosen. For example for adding it to a path. The cost of a vertex could be overwritten and added
     * to the path.
     * @param queue A priority queue for adding vertices. The default comparator chooses the vertex with the smallest costs
     * @param parentVertices A list of vertices with their corresponding parent vertex. Dijkstra uses this to extract
     *                       the shortest path later on
     * @param current Current vertex - pretty much the parent of 'next'
     * @param next Chosen neighbour vertex, that is still in the priority queue (will be removed automatically from the priority queue)
     */
    void relax(PriorityQueue<Vertex> queue, HashMap<Vertex, Vertex> parentVertices, Vertex current, Vertex next);

    /**
     * Method to extract a path/tree from the parent vertices map
     * @param parentVertices Map of vertices with their corresponding parent vertex
     * @return An adjacency structure with a path or tree generated from the parent vertices
     */
    AdjacencyStructure<Long> extract(HashMap<Vertex, Vertex> parentVertices);
}
