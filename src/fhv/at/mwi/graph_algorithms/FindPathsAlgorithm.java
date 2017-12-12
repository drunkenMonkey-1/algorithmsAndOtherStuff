package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.Edge;
import fhv.at.mwi.graph_structure.LongOperator;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FindPathsAlgorithm implements IAlgorithm<List<List<Vertex>>> {

    private AdjacencyStructure<LongOperator> _adStruct;
    private HashMap<Vertex, List<Edge<LongOperator>>> _edgeMap;
    private List<List<Vertex>> _outList;
    private List<List<Edge>> _outListEdge;
    private Vertex _start, _end;

    public FindPathsAlgorithm(AdjacencyStructure<LongOperator> adStruct, Vertex start, Vertex end){
        _adStruct = adStruct;
        _start = start;
        _end = end;
        _edgeMap = _adStruct.getEdgeMap();
    }

    @Override
    public List<List<Vertex>> operate() {
        for(Vertex v : _adStruct.getVertices()){
            v.setVisited(false);
        }
        _outList = new LinkedList<>();
        findPaths(_start, _end, new LinkedList<>());
        return _outList;
    }

    private void findPaths(Vertex root, Vertex destinationVertex, List<Vertex> cl){
        /* Current node is set to visited, so that the search of it's child won't come back to his parent */
        root.setVisited(true);
        /* Is part of the current path. If it actually isn't a part, it will be removed while going back to a suitable node */
        cl.add(root);

        /* Destination found and path is added to out list */
        if(root == destinationVertex){
            /* not in use: Call Copy constructor of LinkedList, so we don't remove nodes from our cl reference which would
             * be part of the outList as well, if the copy constructor wouldn't be called. Only does a shallow copy */
            // _outList.add(new LinkedList<>(cl));

            /* The linked list copy constructor is only a shallow copy constructor so the vertices remained
             * to be the same reference. While setting the property the original vertex reference was overwritten.
             * So this "not so efficient" solution pretty much replaces a deep copy constructor. So the
             * distance value in all vertices remains while finding an other path */
            LinkedList<Vertex> temp = new LinkedList<>();
            for(Vertex v : cl){
                temp.add(new Vertex<>(v.getLabel(), v.getProperty()));
            }
            _outList.add(temp);
        } else{
            /* Get all outgoing vertices from the current from */
            List<Vertex> rootChildren = _adStruct.getOutgoingVertices(root);
            for(Vertex rootChildVertices : rootChildren){
                /* If a child node hasn't been visited yet, it is an indicator for an path to the destination*/
                if(!rootChildVertices.isVisited()){
                    /* Every node has distance saved for the current path. The next distance that needs to be
                     * calculated is the one in the child that is visited. Therefore operate adds, subtracts, divides
                     * or multiplies the current distance with the weight. The weight contains an operator (+,-,*,/)
                     * together with the factor. */
                    long val = _adStruct.getWeight(root, rootChildVertices).operate(root.getProperty());
                    rootChildVertices.setProperty(val);
                    /* recursive call to search for a path from the selected child to the destination */
                    findPaths(rootChildVertices, destinationVertex, cl);
                }
            }
        }
        /* If the there are no neighbours left to visit, backtracking is applied and all changes are undone */
        /* If it has been a valid path to destination it was already saved above */
        root.setVisited(false);
        cl.remove(root);
    }


    private void findPathByEdges(Edge<LongOperator> current, Vertex dest, List<Edge> cl){
        /* Add the current edge to the path and set it as visited so you don't visit the edge in the same path again */
        cl.add(current);
        current.setVisited(true);

        long val = current.get_weight().operate( current.get_v1().getProperty());
        long propertyFix = current.get_v2().getProperty();
        current.get_v2().setProperty(val);

        /* For reconnection when applying backtracking mechanism */
        Edge reConnect = null;
        /* Finding the reconnect edge and blocking the edge from v2 to v1: when <a, b> is the current edge
         * the algorithm moves on to b in the next round. When the graph is undirected, <b, a> must be set
         * visited as well. Therefore the edgeMap of the Adjacent Structure needs to be searched */
        for(Edge e : _edgeMap.get(current.get_v2())) {
            if(e.get_v2() == current.get_v1()){
                reConnect = e;
                e.setVisited(true);
            }
        }

        /* From the current edge every neighbour is searched */
        for(Edge e : _edgeMap.get(current.get_v2())){
            /* if the neighbour edge hasn't been visited yet, it needs to be searched  */
            if(!e.isVisited()){
                findPathByEdges(e, dest, cl);
            }
        }

        /* When the end node was found, after the last neighbour was searched it is added to the path list */
        if(current.get_v2() == dest){
            _outListEdge.add(new LinkedList<>(cl));
        }

        /* For backtracking remove the markers (visited) for the selected edge */
        cl.remove(current);
        current.setVisited(false);
        current.get_v2().setProperty(propertyFix);
        reConnect.setVisited(false);
    }

    public List<List<Edge>> findAll(){
        _outListEdge = new LinkedList<>();
        for(Edge e: _edgeMap.get(_start)){
            findPathByEdges(e, _end, new LinkedList<>());
        }
        return _outListEdge;
    }
}
