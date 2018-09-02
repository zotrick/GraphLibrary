/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Elements.Edge;
import Elements.Graph;
import Elements.Node;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class GraphFactory {

    /**
     *
     * @param g
     * @param allowSelfEdge
     */
    public static void ErdosRenyi(Graph g, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        int n = g.getN();
        int m = g.getM();

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n > 1) {
            for (int i = 0; i < m; i++) {
                int n1 = rnd.nextInt(n);
                int n2 = rnd.nextInt(n);
                if (!allowSelfEdge) {
                    while (n1 == n2) {
                        n2 = rnd.nextInt(n);
                    }
                }
                nodes.get(n1).degree++;
                nodes.get(n2).degree++;
                Edge e = new Edge("E" + i);
                edges.put(nodes.get(n1).n_key + "--" + nodes.get(n2).n_key, e);
            }

        } else {
            System.out.println("No Edges have been created, n must be > 1");
        }
        g.setNodes(nodes);
        g.setEdges(edges);

    }

    /**
     *
     * @param g Graph to create
     * @param p Probability
     * @param allowSelfEdge Allow to self connect
     */
    public static void Gilbert(Graph g, double p, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        int n = g.getN();
        int m = g.getM();

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n > 1) {
            for (int i = 0; i < n; i++) {
                int n1 = i;
                int n2 = 0;
                if ((i + 1 < n)&&m>0) {
                    n2 = i + 1;
                } else {
                    break;
                }
                for (int j = 0; j < m; j++) {

//                    if (!allowSelfEdge) {
//                        while (n1 == n2) {
//                            n2 = rnd.nextInt(n);
//                        }
//                    }
                    Edge e = new Edge("E" + j);
                    if (Math.random() <= p && (nodes.get(n1).degree == 0) && (nodes.get(n2).degree == 0)) {
                        nodes.get(n1).degree++;
                        nodes.get(n2).degree++;
                        edges.put(nodes.get(n1).n_key + "--" + nodes.get(n2).n_key, e);
                        m--;
                    }
                }
            }
        } else {
            System.out.println("No Edges have been created, n must be > 1");
        }
        g.setNodes(nodes);
        g.setEdges(edges);

    }
}
