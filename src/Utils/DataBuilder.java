/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Elements.Edge;
import Elements.Graph;
import Elements.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Lujan - Zotrick
 */
public class DataBuilder {

    public static void generateFile(Graph g) {
        
        File f = new File("C:\\Users\\Juan Eduardo\\Desktop\\graph3.gv");
        String struct = "graph G1 {\n";
        Iterator<Map.Entry<Integer, Node>> it2 = g.getNodes().entrySet().iterator();
        while (it2.hasNext()) {
            struct += it2.next().getValue().n_key + ";\n";
        }
        struct += "\n";
        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();
        while (it.hasNext()) {
            struct += it.next().getKey() + ";\n";
        }
        struct += "}";

        PrintWriter pw;
        try {
            pw = new PrintWriter(f);
            pw.write(struct);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printEdges(Graph g) {
        Iterator<Map.Entry<String, Edge>> it = g.getEdges().entrySet().iterator();

        while (it.hasNext()) {
            //
            System.out.println(it.next().getKey());
        }
        Iterator<Map.Entry<Integer, Node>> it2 = g.getNodes().entrySet().iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next().getValue().n_key);
        }
    }

}
