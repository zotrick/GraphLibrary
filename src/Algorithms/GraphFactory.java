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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Juan Eduardo
 */
public class GraphFactory {

    /**
     *
     * @param n
     * @param m
     * @param allowSelfEdge
     * @param g
     */

    public static void ErdosRenyi(Graph g, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<Integer, Edge> edges = new HashMap<>();
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
                if(!allowSelfEdge){
                    while(n1==n2){
                        n2 = rnd.nextInt(n);
                    }
                }
                edges.put(i, new Edge(i, nodes.get(n1).n_key, nodes.get(n2).n_key));
            }
            
        } else {
            System.out.println("No Edges have been created, n must be > 1");
        }
        g.setNodes(nodes);
        g.setEdges(edges);
        
        
    }
}