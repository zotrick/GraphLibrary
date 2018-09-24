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
        edges.put("N0--N1", new Edge("E0", 0, 1));
        edges.put("N0--N2", new Edge("E1", 0, 2));
        edges.put("N0--N4", new Edge("E2", 0, 4));
        edges.put("N1--N3", new Edge("E3", 1, 3));
        edges.put("N1--N5", new Edge("E4", 1, 5));
        edges.put("N2--N6", new Edge("E5", 2, 6));
        edges.put("N6--N4", new Edge("E6", 6, 4));
        edges.put("N4--N6", new Edge("E7", 4, 6));

        Graph g = new Graph(false, nodes.size(), nodes, edges);
        return g;
    }

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

    private static void generateBFS_DFS_Test() {

        //30 nodes Erdos
        Graph g1 = GraphFactory.ErdosRenyi(30, 270, false);
        DataBuilder.generateFile(g1, "Erdos_30");
         Graph tB = GraphFactory.BFS(g1, 0);
        DataBuilder.generateFile(tB, "BFS_Erdos_30");
         Graph tDi = GraphFactory.DFSIterative(g1, 0);
        DataBuilder.generateFile(tDi, "DFSi_Erdos_30");
         Graph tDr = GraphFactory.DFSRecursive(g1, 0, new boolean[g1.getN()]);
        DataBuilder.generateFile(tDr, "DFSr_Erdos_30");
        
        //30 nodes Gilbert
        Graph g2 = GraphFactory.Gilbert(30, 30, false);
        DataBuilder.generateFile(g2, "Gilbert_30");
        Graph tB2 = GraphFactory.BFS(g2, 0);
        DataBuilder.generateFile(tB2, "BFS_Gilbert_30");
        Graph tDi2 = GraphFactory.DFSIterative(g2, 0);
        DataBuilder.generateFile(tDi2, "DFSi_Gilbert_30");
        Graph tDr2 = GraphFactory.DFSRecursive(g2, 0, new boolean[g2.getN()]);
        DataBuilder.generateFile(tDr2, "DFSr_Gilbert_30");
        
        //30 nodes SimpleGeo
        Graph g3 = GraphFactory.SimpleGeographic(30, 50, false);
        DataBuilder.generateFile(g3, "SimpleGeo_30");
        Graph tB3 = GraphFactory.BFS(g3, 0);
        DataBuilder.generateFile(tB3, "BFS_SimpleGeo_30");
         Graph tDi3 = GraphFactory.DFSIterative(g3, 0);
        DataBuilder.generateFile(tDi3, "DFSi_SimpleGeo_30");
        Graph tDr3 = GraphFactory.DFSRecursive(g3, 0, new boolean[g3.getN()]);
        DataBuilder.generateFile(tDr3, "DFSr_SimpleGeo_30");
        
        //30 nodes Barabasi
        Graph g4 = GraphFactory.BarabasiAlbert(30, 5, false);
        DataBuilder.generateFile(g4, "Barabasi_30");
        Graph tB4 = GraphFactory.BFS(g4, 0);
        DataBuilder.generateFile(tB4, "BFS_Barabasi_30");
        Graph tDi4 = GraphFactory.DFSIterative(g4, 0);
        DataBuilder.generateFile(tDi4, "DFSi_Barabasi_30");
        Graph tDr4 = GraphFactory.DFSRecursive(g4, 0, new boolean[g4.getN()]);
        DataBuilder.generateFile(tDr4, "DFSr_Barabasi_30");
//       
        
        //500 nodes Erdos
        Graph g5 = GraphFactory.ErdosRenyi(500, 2500, false);
        DataBuilder.generateFile(g5, "Erdos_500");
        Graph tB_ = GraphFactory.BFS(g5, 0);
        DataBuilder.generateFile(tB_, "BFS_Erdos_500");
        Graph tDi_ = GraphFactory.DFSIterative(g5, 0);
        DataBuilder.generateFile(tDi_, "DFSi_Erdos_500");
         Graph tDr_ = GraphFactory.DFSRecursive(g5, 0, new boolean[g5.getN()]);
        DataBuilder.generateFile(tDr_, "DFSr_Erdos_500");
        
        //500 nodes Gilbert
        Graph g6 = GraphFactory.Gilbert(500, 10, false);
        DataBuilder.generateFile(g6, "Gilbert_500");
        Graph tB2_ = GraphFactory.BFS(g6, 0);
        DataBuilder.generateFile(tB2_, "BFS_Gilbert_500");
        Graph tDi2_ = GraphFactory.DFSIterative(g6, 0);
        DataBuilder.generateFile(tDi2_, "DFSi_Gilbert_500");
         Graph tDr2_ = GraphFactory.DFSRecursive(g6, 0, new boolean[g6.getN()]);
        DataBuilder.generateFile(tDr2_, "DFSr_Gilbert_500");
        
        //500 nodes SimpleGeo
        Graph g7 = GraphFactory.SimpleGeographic(500, 20, false);
        DataBuilder.generateFile(g7, "SimpleGeo_500");
        Graph tB3_ = GraphFactory.BFS(g7, 0);
        DataBuilder.generateFile(tB3_, "BFS_SimpleGeo_500");
        Graph tDi3_ = GraphFactory.DFSIterative(g7, 0);
        DataBuilder.generateFile(tDi3_, "DFSi_SimpleGeo_500");
        Graph tDr3_ = GraphFactory.DFSRecursive(g7, 0, new boolean[g7.getN()]);
        DataBuilder.generateFile(tDr3_, "DFSr_SimpleGeo_500");
        
        //500 nodes Barabasi
        Graph g8 = GraphFactory.BarabasiAlbert(500, 5, false);
        DataBuilder.generateFile(g8, "Barabasi_500");
        Graph tB4_ = GraphFactory.BFS(g8, 0);
        DataBuilder.generateFile(tB4_, "BFS_Barabasi_500");
        Graph tDi4_ = GraphFactory.DFSIterative(g8, 0);
        DataBuilder.generateFile(tDi4_, "DFSi_Barabasi_500");
        Graph tDr4_ = GraphFactory.DFSRecursive(g8, 0, new boolean[g8.getN()]);
        DataBuilder.generateFile(tDr4_, "DFSr_Barabasi_500");
    }

    public static void main(String args[]) {

//      generateGraphsTest();
//      generateBFSTest();
//      Graph g = generateCustomGraph();
        generateBFS_DFS_Test();

    }

   
}
