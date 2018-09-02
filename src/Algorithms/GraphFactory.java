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
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class GraphFactory {

    /**
     *
     * @param g Graph to create
     * @param m Number of edges
     * @param allowSelfEdge
     */
    public static void ErdosRenyi(Graph g, int m, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        int n = g.getN();

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {
            if (allowSelfEdge) {
                Edge e = new Edge("E" + 0);
                nodes.get(0).degree++;
                edges.put(nodes.get(0).n_key + "--" + nodes.get(0).n_key, e);
            } else {
                System.out.println("No Edges have been created, allow to create edges to itself");
            }
        } else {
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
        }
        g.setNodes(nodes);
        g.setEdges(edges);

    }

    /**
     *
     * @param g Graph to create
     * @param m Number of edges
     * @param p Probability between 1 to 100
     * @param allowSelfEdge Allow to self connect
     */
    public static void Gilbert(Graph g, int m, double p, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        p /= 100;
        int n = g.getN();

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {

            if (allowSelfEdge && (Math.random() <= p)) {
                Edge e = new Edge("E" + 0);
                nodes.get(0).degree++;
                edges.put(nodes.get(0).n_key + "--" + nodes.get(0).n_key, e);
            } else {
                System.out.println("No Edges have been created, allow to create edges to itself");
            }
        } else {
            for (int i = 0; i < n; i++) {
                int n1 = i;
                int n2 = 0;
                if ((i + 1 < n) && m > 0) {
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
        }
        g.setNodes(nodes);
        g.setEdges(edges);
    }

    /**
     *
     * @param g Graph to create
     * @param r Distance between 1 and 100 (it allows decimal point)
     */
    public static void SimpleGeographic(Graph g, double r) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        r /= 100;
        int n = g.getN();

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i), rnd.nextDouble(), rnd.nextDouble()));
        }

        if (n < 2) {
            System.out.println("Self edges are not allowed");
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Node n1 = nodes.get(i);
                    Node n2 = nodes.get(j);
                    double auxdistance = 0;
                    auxdistance = Math.sqrt(Math.pow(n2.x - n1.x, 2) + Math.pow(n2.y - n1.y, 2));
                    if (auxdistance <= r) {
                        n1.degree++;
                        n1.degree++;
                        Edge e = new Edge("E" + i);
                        edges.put(n1.n_key + "--" + n2.n_key, e);
                        i++;
                    }
                }

            }
        }
        g.setNodes(nodes);
        g.setEdges(edges);

    }

    /**
     *
     * @param g Graph to create
     * @param d Edges to assign
     */
    public static void BarabasiAlbert(Graph g, int d) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        int n = g.getN();
        int m = g.getM();
    }
}
