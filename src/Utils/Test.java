/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Algorithms.GraphFactory;
import Elements.Graph;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Test {
    
    public static void main(String args[]){
        Graph g = new Graph(10, 2, false);
        GraphFactory.ErdosRenyi(g,false);
        //GraphFactory.Gilbert(g, .1, false);
        DataBuilder.printEdges(g);
    }
}
