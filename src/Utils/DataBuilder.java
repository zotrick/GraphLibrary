/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Elements.Edge;
import Elements.Graph;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class DataBuilder {
    
    public static void printEdges(Graph g){
        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                //
                System.out.println(it.next().getKey());
            }
    }
    
}
