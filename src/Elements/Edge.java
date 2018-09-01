/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Edge {
    public int e_key;
    public double weight;
    private String n1,n2;

    /**
     * 
     * @param e_key number or key of the edge
     * @param w weight of the edge
     */
    public Edge(int e_key, double w) {
        this.e_key = e_key;
        this.weight = w;
    }
    
    /**
     * 
     * @param e_key name or key of the edge
     */
    public Edge(int e_key) {
        this.e_key = e_key;
    }
    /**
     * 
     * @param e_key name or key of the edge
     * @param n1 node 1
     * @param n2 node 2
     */
    public Edge(int e_key, String n1, String n2) {
        this.e_key = e_key;
        this.n1 = n1;
        this.n2 = n2;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN1() {
        return n1;
    }

    public String getN2() {
        return n2;
    }
    
    
    
        
}
