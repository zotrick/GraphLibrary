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
    public String e_name;
    public double weight;
    private int n1,n2;
    
    /**
     * 
     * @param e_name number or key of the edge
     * @param w weight of the edge
     */
    public Edge(String e_name, double w) {
        this.e_name = e_name;
        this.weight = w;
    }
    
    /**
     * 
     * @param e_name name or key of the edge
     */
    public Edge(String e_name) {
        this.e_name = e_name;
    }
    
     /**
     * 
     * @param e_name name or key of the edge
     * @param n1 number of node 1
     * @param n2 number of node 2
     */
    public Edge(String e_name,int n1,int n2) {
        this.e_name = e_name;
        this.n1 = n1;
        this.n2 = n2;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }
    
    
    
    public Edge() {
    }
        
}
