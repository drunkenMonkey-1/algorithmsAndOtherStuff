package fhv.at.mwi.graph_algorithms;

import fhv.at.mwi.graph_structure.Vertex;

import java.util.List;

public interface IAlgorithm<V> {
    V operate() throws GraphRequirementException;
}
