package prueba;

import graph.Graph;
import graph.GraphLink;

public class PruebaEjer3 {

    public static void main(String[] args) {

        // Usamos el TAD Graph con implementación GraphLink
        Graph<String, String> g = new GraphLink<>();

        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Puno");
        g.insertVertex("Tacna");
        g.insertVertex("Moquegua");

        // Intento de duplicado (no debe agregarse)
        g.insertVertex("Arequipa");

        g.insertEdge("Arequipa", "Cusco");
        g.insertEdge("Arequipa", "Moquegua");
        g.insertEdge("Moquegua", "Tacna");
        g.insertEdge("Cusco", "Puno");
        g.insertEdge("Puno", "Tacna");

        System.out.println("=== GRAFO ===");
        System.out.println(g.toString());

        System.out.println("Existe Cusco? " + g.searchVertex("Cusco"));
        System.out.println("Existe Lima? " + g.searchVertex("Lima"));

        System.out.println("Arequipa - Moquegua existe? " +
                g.searchEdge("Arequipa", "Moquegua"));

        System.out.println("Cusco - Tacna existe? " +
                g.searchEdge("Cusco", "Tacna"));

        System.out.println("\nVecinos de Arequipa:");
        System.out.println(g.adjacentVertices("Arequipa"));

        System.out.println("\nVecinos de Moquegua:");
        System.out.println(g.adjacentVertices("Moquegua"));

        System.out.println("\nEliminando arista Arequipa - Cusco...");
        g.removeEdge("Arequipa", "Cusco");

        System.out.println(g.toString());

        System.out.println("Eliminando vértice Puno...");
        g.removeVertex("Puno");

        System.out.println(g.toString());
    }
}