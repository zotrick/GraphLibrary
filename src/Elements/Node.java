/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.util.Comparator;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Node implements Comparable<Node> {

    public String n_key;
    public String label="";
    public String color;
    public int degree;
    public double x, y;
    public int ikey;
    public boolean discovered = false;
    public double distance = Double.POSITIVE_INFINITY;

    /**
     *
     * @param n_key number or key of the node
     * @param color color of the node
     */
    public Node(String n_key, String color) {
        this.degree = 0;
        this.n_key = n_key;
        this.ikey = Integer.parseInt(n_key.substring(1, n_key.length()));
        this.color = color;
        this.label = n_key;
    }

    /**
     *
     * @param n_key number or key of the node
     */
    public Node(String n_key) {
        this.degree = 0;
        this.n_key = n_key;
        this.ikey = Integer.parseInt(n_key.substring(1, n_key.length()));
        this.label = n_key;
    }

    /**
     *
     * @param n_key number or key of the node
     * @param x coordinate
     * @param y coordinate
     */
    public Node(String n_key, double x, double y) {
        this.n_key = n_key;
        this.ikey = Integer.parseInt(n_key.substring(1, n_key.length()));
        this.x = x;
        this.y = y;
        this.degree = 0;
    }

    public String getN_key() {
        return n_key;
    }

    public void setN_key(String n_key) {
        this.n_key = n_key;
        this.ikey = Integer.parseInt(n_key.substring(1, n_key.length()));
        this.label = n_key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    

    public int getDegree() {
        return degree;
    }

    public void increaseDegree() {
        this.degree++;
    }

    public int getIkey() {
        return ikey;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }
    
    

    @Override
    public int compareTo(Node n2) {
        if (this.distance > n2.getDistance()) {
            return 1;
        } else if (this.distance < n2.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }
}
