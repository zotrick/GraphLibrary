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

    /**
     * 
     * @param n_key number or key of the node
     * @param color color of the node
     */
    public Node(String n_key, String color) {
        this.n_key = n_key;
        this.color = color;
    }
    
    /**
     * 
     * @param n_key number or key of the node
     */
    public Node(String n_key) {
        this.n_key = n_key;
    }
        
}
