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
        //30
       Graph g1 = new Graph(30,false);
        
        GraphFactory.ErdosRenyi(g1,20,false);
        DataBuilder.generateFile(g1,"Erdos_30");
        g1 = new Graph(30,false);
        GraphFactory.Gilbert(g1,25, false);
        DataBuilder.generateFile(g1,"Gilbert_30");
        g1 = new Graph(30,false);
        GraphFactory.SimpleGeographic(g1, 50,false);
        DataBuilder.generateFile(g1,"SimpleGeo_30");
        g1 = new Graph(30,false);       
        GraphFactory.BarabasiAlbert(g1,15);
        DataBuilder.generateFile(g1,"Barabasi_30");
//        
        //100
        Graph g2 = new Graph(100,false);
        
        GraphFactory.ErdosRenyi(g2,50,false);
        DataBuilder.generateFile(g2,"Erdos_100");
        g2 = new Graph(100,false);
        GraphFactory.Gilbert(g2,10, false);
        DataBuilder.generateFile(g2,"Gilbert_100");
        g2 = new Graph(100,false);
        GraphFactory.SimpleGeographic(g2, 25,false);
        DataBuilder.generateFile(g2,"SimpleGeo_100");
        g2 = new Graph(100,false);       
        GraphFactory.BarabasiAlbert(g2,15);
        DataBuilder.generateFile(g2,"Barabasi_100");
        
        //500
        Graph g3 = new Graph(500,false);

        GraphFactory.ErdosRenyi(g3,50,false);
        DataBuilder.generateFile(g3,"Erdos_500");
        g3 = new Graph(500,false);
        GraphFactory.Gilbert(g3,.1, false);
        DataBuilder.generateFile(g3,"Gilbert_500");
        g3 = new Graph(500,false);
        GraphFactory.SimpleGeographic(g3, 5,false);
        DataBuilder.generateFile(g3,"SimpleGeo_500");
        g3 = new Graph(500,false);       
        GraphFactory.BarabasiAlbert(g3,15);
        DataBuilder.generateFile(g3,"Barabasi_500");
        
        //DataBuilder.printEdges(g);
       
            
        }
            
    }

