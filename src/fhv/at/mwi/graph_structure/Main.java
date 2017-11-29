package fhv.at.mwi.graph_structure;

public class Main {

    public static void main(String[] args){
        Graph testGraph = new Graph(StructType.MATRIX);
        Node nodes[] = { new Node<>("a"),
                         new Node<>("b"),
                         new Node<>("c"),
                         new Node<>("d")
        };


        for(int i = 0; i < nodes.length; i++){
            testGraph.add(nodes[i]);
        }

        

    }
}
