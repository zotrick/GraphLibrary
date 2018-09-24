/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Algorithms.GraphFactory;
import Elements.Edge;
import Elements.Graph;
import Elements.Node;
import java.util.HashMap;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Test {

    private static void generateGraphsTest() {
        //30
        Graph g1 = GraphFactory.ErdosRenyi(30, 270, false);
        DataBuilder.generateFile(g1, "Erdos_30");
        Graph g2 = GraphFactory.Gilbert(30, 30, false);
        DataBuilder.generateFile(g2, "Gilbert_30");
        Graph g3 = GraphFactory.SimpleGeographic(30, 50, false);
        DataBuilder.generateFile(g3, "SimpleGeo_30");
        Graph g4 = GraphFactory.BarabasiAlbert(30, 5, false);
        DataBuilder.generateFile(g4, "Barabasi_30");
        //100
        Graph g5 = GraphFactory.ErdosRenyi(100, 270, false);
        DataBuilder.generateFile(g5, "Erdos_100");
        Graph g6 = GraphFactory.Gilbert(100, 30, false);
        DataBuilder.generateFile(g6, "Gilbert_100");
        Graph g7 = GraphFactory.SimpleGeographic(30, 50, false);
        DataBuilder.generateFile(g7, "SimpleGeo_100");
        Graph g8 = GraphFactory.BarabasiAlbert(100, 5, false);
        DataBuilder.generateFile(g8, "Barabasi_100");
        //500
        Graph g9 = GraphFactory.ErdosRenyi(500, 270, false);
        DataBuilder.generateFile(g9, "Erdos_500");
        Graph g10 = GraphFactory.Gilbert(500, 30, false);
        DataBuilder.generateFile(g10, "Gilbert_500");
        Graph g11 = GraphFactory.SimpleGeographic(30, 50, false);
        DataBuilder.generateFile(g11, "SimpleGeo_500");
        Graph g12 = GraphFactory.BarabasiAlbert(500, 5, false);
        DataBuilder.generateFile(g12, "Barabasi_500");
    }

    private static void generateBFSTest() {
        Graph g1 = GraphFactory.ErdosRenyi(500, 1500, false);
        DataBuilder.generateFile(g1, "Test_Graph");
        Graph t = GraphFactory.BFS(g1, 5);
        DataBuilder.generateFile(t, "Test_BFS");
        Graph g2 = GraphFactory.BarabasiAlbert(500, 20, false);
        DataBuilder.generateFile(g2, "Test_Graph_B");
        Graph t2 = GraphFactory.BFS(g2, 6);
        DataBuilder.generateFile(t2, "Test_BFS_Barabasi");
    }

    public static void main(String args[]) {

//          generateGraphsTest();
//            generateBFSTest();
        Graph g = generateCustomGraph();

//        DataBuilder.generateFile(g, "Graph_DFS");
        Graph t = GraphFactory.DFSIterative(g, 0);
        DataBuilder.generateFile(t, "Tree_DFSI");
        
//        Graph t2 = GraphFactory.DFSRecursive(g, 0, new boolean[g.getN()]);
//        DataBuilder.generateFile(t2, "Tree_DFSR");
        Graph t3 = GraphFactory.BFS(g, 0);
        DataBuilder.generateFile(t3, "Tree_BFS");
        System.out.println();
    }

    private static Graph generateCustomGraph() {
        HashMap<Integer, Node> nodes = new HashMap<>();
        nodes.put(0, new Node("N0"));
        nodes.put(1, new Node("N1"));
        nodes.put(2, new Node("N2"));
        nodes.put(3, new Node("N3"));
        nodes.put(4, new Node("N4"));
        nodes.put(5, new Node("N5"));
        nodes.put(6, new Node("N6"));
        HashMap<String, Edge> edges = new HashMap<>();
        edges.put("N0--N1", new Edge("E0",0,1));
        edges.put("N0--N2", new Edge("E1",0,2));
        edges.put("N0--N4", new Edge("E2",0,4));
        edges.put("N1--N3", new Edge("E3",1,3));
        edges.put("N1--N5", new Edge("E4",1,5));
        edges.put("N2--N6", new Edge("E5",2,6));
        edges.put("N6--N4", new Edge("E6",6,4));
        edges.put("N4--N6", new Edge("E7",4,6));
        
        Graph g = new Graph(false, nodes.size(), nodes, edges);
        return g;
    }

}
