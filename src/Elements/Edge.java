/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

/**
 *
 * @author Juan Eduardo
 */
public class Edge {
    public String e_key;
    public double weight;

    /**
     * 
     * @param e_key name or key of the edge
     * @param w weight of the edge
     */
    public Edge(String e_key, double w) {
        this.e_key = e_key;
        this.weight = w;
    }
    
    /**
     * 
     * @param e_key name or key of the edge
     */
    public Edge(String e_key) {
        this.e_key = e_key;
    }
    
    
}
