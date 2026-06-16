package graph;

public class TestGraph {

    public static void main(String[] args) {

        GraphLink<String> g =
                new GraphLink<>();

        // Insertar vértices
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        // Insertar aristas con peso
        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 2);
        g.insertEdgeWeight("B", "D", 5);
        g.insertEdgeWeight("C", "D", 1);
        g.insertEdgeWeight("D", "E", 3);

        // Mostrar grafo
        System.out.println("===== GRAFO =====");
        System.out.println(g);

        // Ruta más corta
        System.out.println("===== SHORT PATH =====");
        System.out.println(
                g.shortPath("A", "E")
        );

        // Dijkstra
        System.out.println("===== DIJKSTRA =====");
        System.out.println(
                g.Dijkstra("A", "E")
        );

        // Conectividad
        System.out.println("===== ES CONEXO =====");
        System.out.println(
                g.isConexo()
        );
    }
}