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
     * @param p Probability between 1 to 100
     * @param allowSelfEdge Allow to self connect
     */
    public static void Gilbert(Graph g, double p, boolean allowSelfEdge) {
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
                for (int j = 0; j < n; j++) {
                    if (!allowSelfEdge) {
                        if (i != j) {
                            Edge e = new Edge("E" + j);
                            if (Math.random() <= p) {
                                edges.put(nodes.get(i).n_key + "--" + nodes.get(j).n_key, e);
                            }
                        }
                    } else {
                        Edge e = new Edge("E" + j);
                        if (Math.random() <= p) {
                            edges.put(nodes.get(i).n_key + "--" + nodes.get(j).n_key, e);
                        }
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
     * @param allowSelfEdge Allow to assign auto edge
     */
    public static void SimpleGeographic(Graph g, double r, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        r /= 100;
        int n = g.getN();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i), rnd.nextDouble(), rnd.nextDouble()));
        }
        if (n < 2) {
            if (!allowSelfEdge) {
                System.out.println("Self edges are not allowed");
            } else {
                Edge e = new Edge("E" + 0);
                edges.put(nodes.get(0).n_key + "--" + nodes.get(0).n_key, e);
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!allowSelfEdge) {
                        if (i != j) {
                            Node n1 = nodes.get(i);
                            Node n2 = nodes.get(j);
                            double auxdistance = 0;
                            auxdistance = Math.sqrt(Math.pow(n2.x - n1.x, 2) + Math.pow(n2.y - n1.y, 2));
                            if (auxdistance <= r) {
                                n1.degree++;
                                n1.degree++;
                                Edge e = new Edge("E" + i);
                                edges.put(n1.n_key + "--" + n2.n_key, e);
                            }
                        }
                    }else{
                            Node n1 = nodes.get(i);
                            Node n2 = nodes.get(j);
                            double auxdistance = 0;
                            auxdistance = Math.sqrt(Math.pow(n2.x - n1.x, 2) + Math.pow(n2.y - n1.y, 2));
                            if (auxdistance <= r) {
                                n1.degree++;
                                n1.degree++;
                                Edge e = new Edge("E" + i);
                                edges.put(n1.n_key + "--" + n2.n_key, e);
                            }
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
        float prob = 0;
        if (d > 0) {
            for (int i = 0; i < n; i++) {
                nodes.put(i, new Node("N" + i));
                for (int j = 0; j < nodes.size(); j++) {
                    if (nodes.get(j) != nodes.get(i)) {
                        prob = 1 - (nodes.get(j).degree / d);
                        if (rnd.nextDouble() <= prob) {
                            Edge e = new Edge("E" + i);
                            edges.put(nodes.get(j).n_key + "--" + nodes.get(i).n_key, e);
                            nodes.get(j).degree++;
                            nodes.get(i).degree++;
                        }
                    }
                }
            }
        }
        g.setNodes(nodes);
        g.setEdges(edges);
    }
}
