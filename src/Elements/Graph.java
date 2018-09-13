/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.util.HashMap;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Graph {
    final private boolean isDirected;
    private boolean allowSelfEdge;
    private int n,m;
    private HashMap<Integer,Node> nodes;
    private HashMap<String,Edge> edges;
            
    /**
     * 
     * @param n number of nodes
     * @param m number of edges
     * @param d is a directed Graph
     */
    public Graph(int n, int m, boolean d){
        this.isDirected = d;
        this.n = n;
        this.m = m;
    }

    /**
     * 
     * @param isDirected 
     * @param n Number of nodes
     * @param nodes List of nodes
     * @param edges  List of edges
     */
    public Graph(boolean isDirected, int n, HashMap<Integer, Node> nodes, HashMap<String, Edge> edges) {
        this.isDirected = isDirected;
        this.n = n;
        this.m = edges.size();
        this.nodes = nodes;
        this.edges = edges;
    }
    
    /**
     * 
     * @param n number of nodes
     * @param d is a directed Graph
     */
    public Graph(int n, boolean d){
        this.n = n;
        this.isDirected = d;
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

    public void setNodes(HashMap<Integer, Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(HashMap<String, Edge> edges) {
        this.edges = edges;
        this.m = edges.size();
    }
    
    
    
}
