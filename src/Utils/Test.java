/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Algorithms.GraphFactory;
import Elements.Graph;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class Test {
    
    public static void main(String args[]){
        Graph g = new Graph(1000,false);
        GraphFactory.ErdosRenyi(g,500,false);
//        GraphFactory.Gilbert(g,2, 10, false);
//        GraphFactory.SimpleGeographic(g, 100);
//        GraphFactory.BarabasiAlbert(g,3);
//        DataBuilder.printEdges(g);


            DataBuilder.generateFile(g);
    }
}
