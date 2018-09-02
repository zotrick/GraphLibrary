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
    
    public Edge() {
    }
        
}
