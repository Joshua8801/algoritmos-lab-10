package graph;

import listlinked.ListLinked;

// clase grafo

public class GraphLink<E> {
    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked(); // grafo vacio
    }

    public void insertVertex(E data) {
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex)); // se agrega al final
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i); // obtiene lista de adyacencia

            if (adj.getVertex().getData().equals(data)) { // compara el dato del vertice si es igual al buscado
                return adj; // devuelve lista de adyacencia
            }
        }
        return null;
    }

    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        v1.getEdges().addLast(new Edge<>(v2.getVertex())); // A hacia B
        v2.getEdges().addLast(new Edge<>(v1.getVertex())); // B hacia A
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // acumulador de texto

        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);

            sb.append(adj.getVertex()).append(" -> "); // Agrega el vértice principal.

            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j)).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}