package prueba;

import graph.Graph;
import graph.GraphLink;
import graph.GraphAnalyzer;

public class PruebaEjer4 {

    public static void main(String[] args) {

        Graph<String, Object> g = new GraphLink<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("B", "C");
        g.insertEdge("C", "D");
        g.insertEdge("D", "A");

        System.out.println("=== GRAFO ORIGINAL ===");
        System.out.println(g.toString());

        GraphAnalyzer<String> analyzer = new GraphAnalyzer<>(g);

        System.out.println("¿Es conexo?");
        System.out.println(analyzer.isConexo("A"));

        Graph<String, Object> g2 = new GraphLink<>();

        g2.insertVertex("A");
        g2.insertVertex("B");
        g2.insertVertex("C");
        g2.insertVertex("D");

        g2.insertEdge("A", "B");
        g2.insertEdge("B", "C");
        g2.insertEdge("C", "D");
        g2.insertEdge("D", "A");

        System.out.println("¿Es isomorfo con otro grafo igual?");
        System.out.println(analyzer.isIsomorfo(g2));

        System.out.println("¿Es plano?");
        System.out.println(analyzer.isPlano());

        System.out.println("¿Es auto-complementario?");
        System.out.println(analyzer.isAutoComplementario());
    }
}