/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Elements.Edge;
import Elements.Graph;
import Elements.Node;
import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class GraphFactory {

    /**
     *
     * @param n Number of nodes
     * @param m Number of edges
     * @param allowSelfEdge
     * @return Graph created
     */
    public static Graph ErdosRenyi(int n, int m, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {
            if (allowSelfEdge) {
//                Edge e = new Edge("E" + 0);
                Edge e = new Edge("E" + 0, 0, 0);
                nodes.get(0).increaseDegree();
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
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
                nodes.get(n1).increaseDegree();
                nodes.get(n2).increaseDegree();
//                Edge e = new Edge("E" + i);
                Edge e = new Edge("E" + i, n1, n2);
                edges.put(nodes.get(n1).getN_key() + "--" + nodes.get(n2).getN_key(), e);
            }
        }
        Graph g = new Graph(false, n, nodes, edges);
        return g;
    }

    /**
     *
     * @param n Number of nodes
     * @param p Probability between 1 to 100
     * @param allowSelfEdge Allow to self connect
     * @return Graph created
     */
    public static Graph Gilbert(int n, double p, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        p /= 100;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {
            if (allowSelfEdge && (Math.random() <= p)) {
//                Edge e = new Edge("E" + 0);
                Edge e = new Edge("E" + 0, 0, 0);
                nodes.get(0).increaseDegree();
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
            } else {
                System.out.println("No Edges have been created, allow to create edges to itself");
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!allowSelfEdge) {
                        if (i != j) {
//                            Edge e = new Edge("E" + j);
                            Edge e = new Edge("E" + j, i, j);
                            if (Math.random() <= p) {
                                edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                            }
                        }
                    } else {
//                        Edge e = new Edge("E" + j);
                        Edge e = new Edge("E" + j, i, j);
                        if (Math.random() <= p) {
                            edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges);
        return g;
    }

    /**
     *
     * @param n number of nodes
     * @param r Distance between 1 and 100 (it allows decimal point)
     * @param allowSelfEdge Allow to assign auto edge
     * @return Graph created
     */
    public static Graph SimpleGeographic(int n, double r, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        Random rnd = new Random();
        r /= 100;

        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i), rnd.nextDouble(), rnd.nextDouble()));
        }
        if (n < 2) {
            if (!allowSelfEdge) {
                System.out.println("Self edges are not allowed");
            } else {
//                Edge e = new Edge("E" + 0);
                Edge e = new Edge("E" + 0, 0, 0);
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
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
                                n1.increaseDegree();
                                n1.increaseDegree();
//                                Edge e = new Edge("E" + i);
                                Edge e = new Edge("E" + i, i, j);
                                edges.put(n1.getN_key() + "--" + n2.getN_key(), e);
                            }
                        }
                    } else {
                        Node n1 = nodes.get(i);
                        Node n2 = nodes.get(j);
                        double auxdistance = 0;
                        auxdistance = Math.sqrt(Math.pow(n2.x - n1.x, 2) + Math.pow(n2.y - n1.y, 2));
                        if (auxdistance <= r) {
                            n1.increaseDegree();
                            n1.increaseDegree();
//                            Edge e = new Edge("E" + i);
                            Edge e = new Edge("E" + i, i, j);
                            edges.put(n1.getN_key() + "--" + n2.getN_key(), e);
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges);
        return g;
    }

    /**
     *
     * @param n Number of nodes
     * @param d Max connections
     * @param allowSelfEdge
     * @return Graph created
     */
    public static Graph BarabasiAlbert(int n, double d, boolean allowSelfEdge) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        double prob = 0;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i)));
        }
        int nE = 0;
        if (d > 0) {
            for (int i = 0; i < n; i++) {
                int j = 0;
                while (j <= i && (nodes.get(i).getDegree() <= d)) {
                    if (j != i || allowSelfEdge) {
                        prob = 1 - (nodes.get(j).getDegree() / d);
                        if (Math.random() <= prob) {
//                            Edge e = new Edge("E" + nE);
                            Edge e = new Edge("E" + nE, i, j);
                            edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                            nodes.get(i).increaseDegree();
                            if (j != i) {
                                nodes.get(j).increaseDegree();
                            }
                            nE++;
                        }
                    }
                    j++;
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges);
        return g;
    }

    /**
     *
     * @param g Graph to check
     * @param s Initial node (integer)
     * @return t Tree
     */
    public static Graph BFS(Graph g, int s) {
        LinkedList<LinkedList<Node>> daddyLayer = new LinkedList<>();
        boolean[] discovered = new boolean[g.getNodes().size()];
        discovered[s] = true;
        Graph t = new Graph(false);
        daddyLayer.add(new LinkedList<>());
        daddyLayer.get(0).add(g.getNodes().get(s));
        int i = 0;
        Iterator<Map.Entry<String, Edge>> it2;
        while (!daddyLayer.get(i).isEmpty()) {
            LinkedList<Node> babyLayer = new LinkedList<>();
            
            for (int j = 0; j < daddyLayer.get(i).size(); j++) {
                it2 = g.getEdges().entrySet().iterator();
                while (it2.hasNext()) {
                    int n = daddyLayer.get(i).get(j).getIkey();
                    Edge e = it2.next().getValue();
                    if (n == e.getN1()) {
                        if (!discovered[e.getN2()]) {
                            discovered[e.getN2()] = true;
                            babyLayer.add(g.getNodes().get(e.getN2()));
                        }
                    } else if (n == e.getN2()) {
                        if (!discovered[e.getN1()]) {
                            discovered[e.getN1()] = true;
                            babyLayer.add(g.getNodes().get(e.getN1()));
                        }
                    }
 
                }
                System.out.println();
            }
            daddyLayer.add(babyLayer);
            i++;
        }
        System.out.println();

        return t;
    }
}
