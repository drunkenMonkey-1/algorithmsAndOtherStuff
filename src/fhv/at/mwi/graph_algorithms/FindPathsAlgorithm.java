package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.LongOperator;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.LinkedList;
import java.util.List;

public class FindPathsAlgorithm implements IAlgorithm<Vertex> {

    private AdjacencyStructure<LongOperator> _adStruct;
    private List<List<Vertex>> _outList;
    private Vertex _start, _end;

    public FindPathsAlgorithm(AdjacencyStructure<LongOperator> adStruct, Vertex start, Vertex end){
        _adStruct = adStruct;
        _start = start;
        _end = end;
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
                /* If the node hasn't been visited yet, it is an indicator for an alternative path - backtracking */
                if(!rootChildVertices.isVisited()){
                    int val = Math.toIntExact(_adStruct.getWeight(root, rootChildVertices).operate((long) root.getProperty()));
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
}
