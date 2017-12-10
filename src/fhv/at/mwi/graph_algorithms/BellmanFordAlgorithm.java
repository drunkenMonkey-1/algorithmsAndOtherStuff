package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.AdjacencyStructure;
import fhv.at.mwi.graph_structure.LongOperator;
import fhv.at.mwi.graph_structure.Vertex;

import java.util.List;

public class BellmanFordAlgorithm implements IAlgorithm<List<Vertex>>{

    private AdjacencyStructure<LongOperator> _adStruct;

    public BellmanFordAlgorithm(AdjacencyStructure<LongOperator> adStruct){
        _adStruct = adStruct;
    }


    @Override
    public List<Vertex> operate() {
        return null;
    }
}
