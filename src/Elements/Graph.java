/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.util.HashMap;

/**
 *
 * @author Juan Eduardo
 */
public class Graph {
    public boolean directed;
    private int n,m;
    HashMap<String,Node> nodes;
    HashMap<String,Edge> edges;
            
    /**
     * 
     * @param n number of nodes
     * @param m number of edges
     * @param d is a directed Graph
     */
    public Graph(int n, int m, boolean d){
        
        this.directed = d;
        this.n = n;
        this.m = m;
    }
    
}
