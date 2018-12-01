/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.text.DecimalFormat;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Graph {

    final private boolean isDirected;
    private boolean allowSelfEdge;
    private int n, m;
    private HashMap<Integer, Node> nodes = new HashMap<>();
    private HashMap<String, Edge> edges = new HashMap<>();
    private boolean[][] adjMatrix;

    private HashMap<Node, ArrayList<Node>> adjList;
    private double treeWeight = 0;
    private generateMethod generateMethod;
    private treeMethod treeMethod;

    public enum generateMethod {
        Erdos, Gilbert, SimpleGeo, Barabasi
    }

    public enum treeMethod {
        BFS, DFSIterative, DFSRecursive
    }

    /**
     *
     * @param isDirected
     */
    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     *
     * @param n number of nodes
     * @param m number of edges
     * @param d is a directed Graph
     */
    public Graph(int n, int m, boolean d) {
        this.isDirected = d;
        this.n = n;
        this.m = m;
    }

    /**
     *
     * @param isDirected
     * @param n Number of nodes
     * @param nodes List of nodes
     * @param edges List of edges
     * @param adjL AdjacencyList
     */
    public Graph(boolean isDirected, int n, HashMap<Integer, Node> nodes, HashMap<String, Edge> edges, HashMap<Node, ArrayList<Node>> adjL) {
        this.isDirected = isDirected;
        this.n = n;
        this.m = edges.size();
        this.nodes = nodes;
        this.edges = edges;
        this.adjList = adjL;
//        createAdjacencyMatrix();
    }

    /**
     *
     * @param n number of nodes
     * @param d is a directed Graph
     */
    public Graph(int n, boolean d) {
        this.n = n;
        this.isDirected = d;
    }

    /**
     *
     * @param isDirected
     * @param n Number of nodes
     * @param nodes List of nodes
     * @param edges List of edges
     */
    public Graph(boolean isDirected, int n, HashMap<Integer, Node> nodes, HashMap<String, Edge> edges) {
        this.isDirected = isDirected;
        this.n = n;
        this.m = edges.size();
        this.nodes = nodes;
        this.edges = edges;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public HashMap<String, Edge> getEdges() {
        return edges;
    }

    /**
     * Establish a set of nodes for the graph
     *
     * @param nodes set of nodes to establish
     */
    public void setNodes(HashMap<Integer, Node> nodes) {
        this.nodes = nodes;
        this.n = nodes.size();
    }

    /**
     * Establish a set of edges for the graph
     *
     * @param edges set of edges to establish
     */
    public void setEdges(HashMap<String, Edge> edges) {
        this.edges = edges;
        this.m = edges.size();
    }

    /**
     * Add a new node to the graph
     *
     * @param n node to add
     */
    public void addNode(Node n) {
        this.nodes.put(n.ikey, n);
        this.n++;
    }

    /**
     * Add a new edge to the graph
     *
     * @param e edge to add
     */
    public void addEdge(Edge e) {
        this.edges.put("N" + e.getU() + "--" + "N" + e.getV(), e);
        this.m++;
    }

    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

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

        HashMap<Node, ArrayList<Node>> adjL = new HashMap<>();

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
        } else if (m <= ((n * n) - 1)) {
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
                String keye = nodes.get(n1).getN_key() + "--" + nodes.get(n2).getN_key();
                String keyi = nodes.get(n2).getN_key() + "--" + nodes.get(n1).getN_key();

                if (!edges.containsKey(keye) && !edges.containsKey(keyi)) {
                    Edge e = new Edge("E" + i, n1, n2);
                    edges.put(keye, e);
                    updateAdjacencyList(nodes.get(n1), nodes.get(n2), adjL);
                }

            }
        } else {
            System.out.println("Number of different edges can't be more than:" + (n * n));
        }
        Graph g = new Graph(false, n, nodes, edges, adjL);
        return g;
    }

    private static void updateAdjacencyList(Node u, Node v, HashMap<Node, ArrayList<Node>> adjL) {
        if (!adjL.containsKey(u)) {
            adjL.put(u, new ArrayList());
        }
        if (!adjL.containsKey(v)) {
            adjL.put(v, new ArrayList());
        }
        adjL.get(u).add(v);
        adjL.get(v).add(u);
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
        HashMap<Node, ArrayList<Node>> adjL = new HashMap<>();
        Random rnd = new Random();
        p /= 100;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node("N" + i));
        }

        int begin = allowSelfEdge ? 0 : 1;
        if (n < 2) {
            if (allowSelfEdge && (Math.random() <= p)) {
                Edge e = new Edge("E" + 0, 0, 0);
                nodes.get(0).increaseDegree();
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
            } else {
                System.out.println("No Edges have been created, allow to create edges to itself");
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i + begin; j < n; j++) {
                    if (Math.random() <= p) {
                        String keye = nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key();
                        if (!edges.containsKey(keye)) {
                            Edge e = new Edge("E" + i * j, i, j);
                            edges.put(keye, e);
                            updateAdjacencyList(nodes.get(i), nodes.get(j), adjL);
                            edges.put(keye, e);
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjL);
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
        HashMap<Node, ArrayList<Node>> adjL = new HashMap<>();
        Random rnd = new Random();
        r /= 100;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i), rnd.nextDouble(), rnd.nextDouble()));
        }
        int begin = allowSelfEdge ? 0 : 1;
        if (n < 2) {
            if (!allowSelfEdge) {
                System.out.println("Self edges are not allowed");
            } else {
                Edge e = new Edge("E" + 0, 0, 0);
                edges.put(nodes.get(0).getN_key() + "--" + nodes.get(0).getN_key(), e);
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i + begin; j < n; j++) {
                    Node n1 = nodes.get(i);
                    Node n2 = nodes.get(j);
                    double auxdistance = 0;
                    auxdistance = Math.sqrt(Math.pow(n2.x - n1.x, 2) + Math.pow(n2.y - n1.y, 2));
                    if (auxdistance <= r) {
                        n1.increaseDegree();
                        n2.increaseDegree();
                        String keye = n1.getN_key() + "--" + n2.getN_key();
                        if (!edges.containsKey(keye)) {
                            Edge e = new Edge("E" + i * j, i, j);
                            edges.put(keye, e);
                            updateAdjacencyList(nodes.get(i), nodes.get(j), adjL);
                            edges.put(keye, e);
                        }
                    }
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjL);
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
        HashMap<Node, ArrayList<Node>> adjL = new HashMap<>();
        double prob = 0;
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(("N" + i)));
        }
        int begin = allowSelfEdge ? 0 : 1;
        int nE = 0;
        if (d > 0) {
            for (int i = 0; i < n; i++) {
                int j = 0;
                while (j <= i && (nodes.get(i).getDegree() <= d)) {
                    if (j != i || allowSelfEdge) {
                        prob = 1 - (nodes.get(j).getDegree() / d);
                        if (Math.random() <= prob) {
                            nodes.get(i).increaseDegree();
                            if (j != i) {
                                nodes.get(j).increaseDegree();
                            }
                            String keye = nodes.get(i).getN_key() + "--" + nodes.get(j).getN_key();
                            if (!edges.containsKey(keye)) {
                                Edge e = new Edge("E" + nE, i, j);
                                edges.put(keye, e);
                                updateAdjacencyList(nodes.get(i), nodes.get(j), adjL);
                                edges.put(keye, e);
                            }
                            nE++;
                        }
                    }
                    j++;
                }
            }
        }
        Graph g = new Graph(false, n, nodes, edges, adjL);
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
        HashMap<Integer, Node> nodesToSave = new HashMap<>();
        nodesToSave.put(s,g.getNodes().get(s));
        int[] parent = new int[g.getN()];
        int i = 0;
        while (!stack.isEmpty()) {
            int n = stack.get(0).getIkey();
            stack.remove(0);
            if (!discovered[n]) {
                discovered[n] = true;
                edg.add(n);
                if (n != s) {
                     nodesToSave.put(n,g.getNodes().get(n));
                    edges.put("N" + n + "--N" + parent[n], new Edge("E" + i++, n, parent[n]));
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

        //Graph t = new Graph(false, g.getN(), g.getNodes(), edges);
        Graph t = new Graph(false, nodesToSave.size(), nodesToSave, edges);
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

    /**
     *
     * @param g graph to set Weights
     * @param min
     * @param max
     * @return
     */
    public static Graph EdgeValues(Graph g, double min, double max) {

        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
        Random rnd = new Random();
        while (it.hasNext()) {
            double value = (rnd.nextDouble() * (max - min)) + min;
            it.next().getValue().setWeight(value);
        }

        return g;
    }

    public static Graph Dijkstra(Graph gr, int s) {

        Graph g = new Graph(gr.isDirected);

        g.setEdges(gr.getEdges());
        g.setNodes(gr.getNodes());
        PriorityQueue pq = new PriorityQueue();
        HashMap<String, Edge> edges = new HashMap<>();
        int[] parent = new int[g.getN()];

        for (Node n : g.getNodes().values()) {
            pq.add(n);
            parent[n.getIkey()] = -1;
        }

        LinkedList<Node> S = new LinkedList<>();
        Node nodeS = g.getNodes().get(s);
        nodeS.setDistance(0.00);
        nodeS.setDiscovered(true);
        parent[s] = 0;
        pq.remove(nodeS);
        pq.add(nodeS);

        while (!pq.isEmpty()) {
            Node n = (Node) pq.element();
            pq.remove();
            n.setDiscovered(true);

            if (!Objects.equals(n.getIkey(), nodeS.getIkey())) {
                Node u = g.getNodes().get(parent[n.getIkey()]);
                Node v = g.getNodes().get(n.getIkey());
                double du = (Math.round(u.getDistance() * 100d) / 100d);
                double dv = (Math.round(v.getDistance() * 100d) / 100d);
                u.setLabel(u.getN_key() + "(" + du + ")");
                v.setLabel(v.getN_key() + "(" + dv + ")");
                String key = u.getN_key() + "--" + v.getN_key();
                Edge p = new Edge(key, u.getIkey(), v.getIkey(), v.getDistance() - u.getDistance());
                edges.put(key, p);
            }

            Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
            it = g.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Edge> edge = it.next();
                Edge e = edge.getValue();
                if (e.getU() == n.ikey && !g.getNodes().get(e.getV()).isDiscovered()) {
                    Node v = g.getNodes().get(e.getV());
                    double aux = n.getDistance() + e.weight;
                    if (aux < v.getDistance()) {
                        v.setDistance(aux);
                        parent[e.getV()] = e.getU();
                        if (pq.contains(v)) {
                            pq.remove(v);
                        }
                        pq.add(v);
                    }
                } else if (e.getV() == n.ikey && !g.getNodes().get(e.getU()).isDiscovered()) {
                    Node u = g.getNodes().get(e.getU());
                    double aux = n.getDistance() + e.weight;
                    if (aux < u.getDistance()) {
                        u.setDistance(aux);
                        parent[e.getU()] = e.getV();
                        if (pq.contains(u)) {
                            pq.remove(u);
                        }
                        pq.add(u);
                    }
                }
            }
        }
        Graph t = new Graph(false, g.getN(), g.getNodes(), edges);
        return t;
    }

    /**
     * Get a minimum expansion tree using Kruskal Direct method
     * @param g Graph used to calculate tree
     * @return 
     */
    public static Graph Kruskal_D(Graph g) {
        HashMap<String, Edge> edges = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue();
        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
        it = g.getEdges().entrySet().iterator();
        while(it.hasNext()){
            pq.add(it.next().getValue());
        }
        int m = g.getM();
        Graph tree = new Graph(g.isDirected);
        tree.setNodes(g.getNodes());
        for (int i = 0; i < m; i++) {
            Edge e = pq.poll();
            Graph dfs = Graph.DFSIterative(tree, e.getU());
            
            if(!dfs.getNodes().containsKey(e.getV()))
                tree.addEdge(e);
        }
        
        Iterator<Map.Entry<String, Edge>> it2 = tree.getEdges().entrySet().iterator();
        double totalWeight = 0;
        while(it2.hasNext()){
            totalWeight += it2.next().getValue().getWeight();
        }
        tree.getNodes().get(0).setLabel("Total weight="+totalWeight);
        return tree;
    }

    public HashMap<Node, ArrayList<Node>> getAdjList() {
        return adjList;
    }

    private static Edge getEdge(int u, int v, Graph g) {

        String key = g.nodes.get(u).getN_key() + "--" + g.nodes.get(v).getN_key();
        String keyi = g.nodes.get(v).getN_key() + "--" + g.nodes.get(u).getN_key();

        if (g.getEdges().containsKey(key)) {
            return g.getEdges().get(key);
        } else if (g.getEdges().containsKey(keyi)) {
            return g.getEdges().get(keyi);
        }

        return null;
    }

    public void setTreeWeight(double treeWeight) {
        this.treeWeight = treeWeight;
    }

    public double getTreeWeight() {
        return treeWeight;
    }
    
    /**
     * Get a minimum expansion tree using Kruskal Inverse method
     * @param g Graph used to calculate tree
     * @return 
     */
    public static Graph Kruskal_I(Graph g){
        PriorityQueue<Edge> pq = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<String, Edge> edges = new HashMap<>();
        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
        it = g.getEdges().entrySet().iterator();
        while(it.hasNext()){
            pq.add(it.next().getValue());
        }
        int n = g.getN();
        int m = g.getM();
        for (int i = 0; i < m; i++) {
            Edge e = pq.poll();
            g.getEdges().remove(e.getEkey());
            int rdfs =Graph.DFSIterative(g, 0).getNodes().size();
            if(rdfs<n){
                g.addEdge(e);
            }
        }
        
        Iterator<Map.Entry<String, Edge>> it2 = g.getEdges().entrySet().iterator();
        it2 = g.getEdges().entrySet().iterator();
        double totalWeight = 0;
        while(it2.hasNext()){
            totalWeight += it2.next().getValue().getWeight();
        }
        
        g.getNodes().get(0).setLabel("Total weight="+totalWeight);
        Graph t = new Graph(false, g.getN(), g.getNodes(), g.getEdges());
        return t;
    }
    
    /**
     * Get a minimum expansion tree using Prim´s method
     * @param gr Graph used to calculate tree
     * @return 
     */
    public static Graph Prim(Graph gr) {
        PriorityQueue pq = new PriorityQueue();
        HashMap<String, Edge> edges = new HashMap<>();
        int[] parent = new int[gr.getN()];
        double tweight = 0;
        for (Node n : gr.getNodes().values()) {
            pq.add(n);
            parent[n.getIkey()] = -1;
            n.setDistance(Double.POSITIVE_INFINITY);
        }

        while (!pq.isEmpty()) {
            Node n = (Node) pq.remove();
            n.setDiscovered(true);

            if (parent[n.getIkey()] != -1) {
                Node u = gr.getNodes().get(parent[n.getIkey()]); double du = (Math.round(u.getDistance() * 100d) / 100d);
                Node v = gr.getNodes().get(n.getIkey());  double dv = (Math.round(v.getDistance() * 100d) / 100d);
//                u.setLabel(u.getN_key() + "(" + du + ")");
//                v.setLabel(v.getN_key() + "(" + dv + ")");
                String key = u.getN_key() + "--" + v.getN_key();
                edges.put(key, getEdge(u.getIkey(), v.getIkey(), gr));
                tweight += getEdge(u.getIkey(), v.getIkey(), gr).weight;
            }
            for (Node x : gr.getAdjList().get(n)) {
                if (!x.isDiscovered() && (x.getDistance() > getEdge(n.getIkey(), x.getIkey(), gr).getWeight())) {
                    x.setDistance(getEdge(n.getIkey(), x.getIkey(), gr).getWeight());
                    parent[x.getIkey()] = n.getIkey();

                    if (pq.contains(x)) {
                        pq.remove(x);
                    }
                    pq.add(x);
                }
            }
        }
        gr.getNodes().get(0).setLabel( "Total weight(" + tweight + ")");
        Graph t = new Graph(false, gr.getN(), gr.getNodes(), edges);
        t.setTreeWeight(tweight);
        
        return t;
    }

    public static Graph Dijkstra2(Graph gr, int s) {
        PriorityQueue pq = new PriorityQueue();
        HashMap<String, Edge> edges = new HashMap<>();
        int[] parent = new int[gr.getN()];
        for (Node n : gr.getNodes().values()) {
            pq.add(n);
            parent[n.getIkey()] = -1;
        }
        Node nodeS = gr.getNodes().get(s);
        nodeS.setDistance(0.00);
        nodeS.setDiscovered(true);
        parent[s] = -1;
        pq.remove(nodeS);
        pq.add(nodeS);
        while (!pq.isEmpty()) {
            Node n = (Node) pq.remove();
            n.setDiscovered(true);
            if (parent[n.getIkey()] != -1) {
                Node u = gr.getNodes().get(parent[n.getIkey()]); double du = (Math.round(u.getDistance() * 100d) / 100d);
                Node v = gr.getNodes().get(n.getIkey());  double dv = (Math.round(v.getDistance() * 100d) / 100d);
               u.setLabel(u.getN_key() + "(" + du + ")");
                v.setLabel(v.getN_key() + "(" + dv + ")");
                String key = u.getN_key() + "--" + v.getN_key();
                edges.put(key, getEdge(u.getIkey(), v.getIkey(), gr));
            }
            for (Node x : gr.getAdjList().get(n)) {
                if (!x.isDiscovered() && (x.getDistance() > n.getDistance()+getEdge(n.getIkey(), x.getIkey(), gr).getWeight())) {
                    x.setDistance(n.getDistance()+ getEdge(n.getIkey(), x.getIkey(), gr).getWeight());
                    parent[x.getIkey()] = n.getIkey();
                    if (pq.contains(x)) {
                        pq.remove(x);
                    }
                    pq.add(x);
                }
            }
        }
    
        Graph t = new Graph(false, gr.getN(), gr.getNodes(), edges);
        return t;
    }
}
