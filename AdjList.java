package graph;

import listlinked.ListLinked;

public class AdjList<E> {

    // Vértice principal
    private Vertex<E> vertex;

    // Lista de aristas adyacentes
    private ListLinked<Edge<E>> edges;

    // Constructor
    public AdjList(Vertex<E> vertex) {
        this.vertex = vertex;
        this.edges = new ListLinked<>();
    }

    // Retorna el vértice
    public Vertex<E> getVertex() {
        return vertex;
    }

    // Retorna la lista de aristas
    public ListLinked<Edge<E>> getEdges() {
        return edges;
    }
}