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
public class Edge implements Comparable<Edge>{
    public String e_name;
    public double weight = 1;
    private int u,v;
    
    
    /**
     * 
     * @param e_name number or key of the edge
     * @param w weight of the edge
     */
    public Edge(String e_name, int w) {
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
     * @param u number of node 1
     * @param v number of node 2
     */
    public Edge(String e_name,int u,int v) {
        this.e_name = e_name;
        this.u = u;
        this.v = v;
    }
    
    /**
     * 
     * @param e_name name or key of the edge
     * @param u number of node 1
     * @param v number of node 2
     * @param w weight
     */
    public Edge(String e_name,int u,int v,double w) {
        this.e_name = e_name;
        this.u = u;
        this.v = v;
        this.weight = w;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double w) {
        this.weight = w;
    }
    
    public Edge() {
    }

    @Override
    public int compareTo(Edge e2) {
        if (this.weight > e2.getWeight()) {
            return 1;
        } else if (this.weight < e2.getWeight()) {
            return -1;
        } else {
            return 0;
        }
    }
        
}
