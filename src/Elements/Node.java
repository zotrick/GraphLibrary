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
public class Node {
    public String n_key;
    public String color;
    public int degree;
    public double x,y;

    /**
     * 
     * @param n_key number or key of the node
     * @param color color of the node
     */
    public Node(String n_key, String color) {
        this.degree = 0;
        this.n_key = n_key;
        this.color = color;
    }
    
    /**
     * 
     * @param n_key number or key of the node
     */
    public Node(String n_key) {
        this.degree = 0;
        this.n_key = n_key;
    }

    /**
     * 
     * @param n_key number or key of the node
     * @param x coordinate
     * @param y coordinate
     */
    public Node(String n_key, double x, double y) {
        this.n_key = n_key;
        this.x = x;
        this.y = y;
        this.degree = 0;
    }
    
    
        
}
