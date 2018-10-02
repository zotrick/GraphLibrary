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
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
        boolean[][] adjMatrix = new boolean[n][n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {
            if (allowSelfEdge) {
                Edge e = new Edge("E" + 0, 0, 0);
                nodes.get(0).increaseDegree();
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
                adjMatrix[0][0] = true;
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
                Edge e = new Edge("E" + i, n1, n2,rnd.nextInt(n));
                edges.put(nodes.get(n1).getN_key() + "--" + nodes.get(n2).getN_key(), e);
                adjMatrix[n1][n2] = true;
                adjMatrix[n2][n1] = true;
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjMatrix);
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
        boolean[][] adjMatrix = new boolean[n][n];
        p /= 100;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }
        if (n < 2) {
            if (allowSelfEdge && (Math.random() <= p)) {
                Edge e = new Edge("E" + 0, 0, 0);
                nodes.get(0).increaseDegree();
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
                adjMatrix[0][0] = true;
            } else {
                System.out.println("No Edges have been created, allow to create edges to itself");
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!allowSelfEdge) {
                        if (i != j) {
                            Edge e = new Edge("E" + j, i, j);
                            if (Math.random() <= p) {
                                edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                                adjMatrix[i][j] = true;
                                adjMatrix[j][i] = true;
                            }
                        }
                    } else {
                        Edge e = new Edge("E" + j, i, j);
                        if (Math.random() <= p) {
                            edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                            adjMatrix[i][j] = true;
                            adjMatrix[j][i] = true;
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjMatrix);
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
        boolean[][] adjMatrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i), rnd.nextDouble(), rnd.nextDouble()));
        }
        if (n < 2) {
            if (!allowSelfEdge) {
                System.out.println("Self edges are not allowed");
            } else {
                Edge e = new Edge("E" + 0, 0, 0);
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
                adjMatrix[0][0] = true;
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
                                Edge e = new Edge("E" + i, i, j);
                                edges.put(n1.getN_key() + "--" + n2.getN_key(), e);
                                adjMatrix[i][j] = true;
                                adjMatrix[j][i] = true;
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
                            Edge e = new Edge("E" + i, i, j);
                            edges.put(n1.getN_key() + "--" + n2.getN_key(), e);
                            adjMatrix[i][j] = true;
                            adjMatrix[j][i] = true;
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjMatrix);
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
        boolean[][] adjMatrix = new boolean[n][n];
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
                            Edge e = new Edge("E" + nE, i, j);
                            edges.put(nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key(), e);
                            adjMatrix[i][j] = true;
                            adjMatrix[j][i] = true;
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
        Graph g = new Graph(false, n, nodes, edges, adjMatrix);
        return g;
    }

    /**
     *
     * @param g Graph to check
     * @param s Initial node (integer from 0 to n)
     * @return t BFS Tree
     */
    public static Graph BFS(Graph g, int s) {
        LinkedList<LinkedList<Node>> parentLayer = new LinkedList<>();
        boolean[] discovered = new boolean[g.getNodes().size()];
        HashMap<String, Edge> edges = new HashMap<>();
        discovered[s] = true;
        parentLayer.add(new LinkedList<>());
        parentLayer.get(0).add(g.getNodes().get(s));
        int i = 0;
        Iterator<Map.Entry<String, Edge>> it;
        while (!parentLayer.get(i).isEmpty()) {
            LinkedList<Node> childLayer = new LinkedList<>();
            for (int j = 0; j < parentLayer.get(i).size(); j++) {
                it = g.getEdges().entrySet().iterator();
                while (it.hasNext()) {
                    int n = parentLayer.get(i).get(j).getIkey();
                    Map.Entry<String, Edge> edge = it.next();
                    Edge e = edge.getValue();
                    if (n == e.getU()) {
                        if (!discovered[e.getV()]) {
                            discovered[e.getV()] = true;
                            childLayer.add(g.getNodes().get(e.getV()));
                            edges.put(edge.getKey(), e);
                        }
                    } else if (n == e.getV()) {
                        if (!discovered[e.getU()]) {
                            discovered[e.getU()] = true;
                            childLayer.add(g.getNodes().get(e.getU()));
                            edges.put(edge.getKey(), e);
                        }
                    }
                }
            }
            parentLayer.add(childLayer);
            i++;
        }
        Graph t = new Graph(false, g.getN(), g.getNodes(), edges);
        return t;
    }

    /**
     *
     * @param g Graph to check
     * @param s Initial node
     * @return t DFS Tree
     */
    public static Graph DFSIterative(Graph g, int s) {
        boolean[] discovered = new boolean[g.getN()];
        HashMap<String, Edge> edges = new HashMap<>();
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(g.getNodes().get(s));
        Iterator<Map.Entry<String, Edge>> it;
        ArrayList<Integer> edg = new ArrayList<>();
        int[] parent = new int[g.getN()];
       int i =0;
        while (!stack.isEmpty()) {
            int n = stack.get(0).getIkey();
             stack.remove(0);
            if (!discovered[n]) {
                discovered[n] = true;
                edg.add(n);
                if (n != s) {
                    edges.put("N"+n+"--N"+parent[n], new Edge("E"+i++,n,parent[n]));
                    }
                it = g.getEdges().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Edge> edge = it.next();
                    Edge e = edge.getValue();
                    if (n == e.getU() && !discovered[e.getV()]) {
                        stack.addFirst(g.getNodes().get(e.getV()));
                        parent[e.getV()] = n;
                    } else if (n == e.getV() && !discovered[e.getU()]) {
                        stack.addFirst(g.getNodes().get(e.getU()));
                        parent[e.getU()] = n;
                    }
                }
            }
        }
        
        Graph t = new Graph(false, g.getN(), g.getNodes(), edges);
        return t;
    }

    private static HashMap<String, Edge> edgesDFSR = new HashMap<>();

    /**
     *
     * @param g Graph to check
     * @param n Initial node (integer from 0 to n)
     * @return t DFS Tree
     */
    public static Graph DFSRecursive(Graph g, int n, boolean[] discovered) {
        discovered[n] = true;
        Iterator<Map.Entry<String, Edge>> it2 = g.getEdges().entrySet().iterator();
        int i = n;
        while (it2.hasNext()) {
            Map.Entry<String, Edge> edge = it2.next();
            Edge e = edge.getValue();
            if (i == e.getU()) {
                if (!discovered[e.getV()]) {
                    edgesDFSR.put(edge.getKey(), e);
                    DFSRecursive(g, e.getV(), discovered);
                }
            } else if (i == e.getV()) {
                if (!discovered[e.getU()]) {
                    edgesDFSR.put(edge.getKey(), e);
                    DFSRecursive(g, e.getU(), discovered);
                }
            }
        }
        Graph t = new Graph(false, g.getN(), g.getNodes(), edgesDFSR);
        return t;
    }

    public static Graph Dijkstra(Graph g, int l){
        LinkedList<Node> S = new LinkedList<>();
        int[] distances = new int[g.getM()];
        
        S.add(g.getNodes().get(l));
        distances[l] = 0;
        
        HashMap<String, Edge> edges = new HashMap<>();
        Iterator<Map.Entry<String, Edge>> it;
        
        while(S.size()!= g.getNodes().size()){
            it = g.getEdges().entrySet().iterator();
            
            while(it.hasNext()){
                Map.Entry<String, Edge> edge = it.next();
                Edge e = edge.getValue();
                
                    if(e.getU()== S.get(l).ikey){
                        int aux = distances[e.getV()]+e.weight;
                        if(distances[e.getU()]<aux){
                            S.add(g.getNodes().get(e.getV()));
                        }
                    }else if(e.getV()== S.get(l).ikey){
                        int aux = distances[e.getU()]+e.weight;
                        if(distances[e.getV()]<aux){
                            S.add(g.getNodes().get(e.getU()));
                        }
                    }
            }
            
        }
        Graph d = new Graph(5, false);
        return d;
    }
}
