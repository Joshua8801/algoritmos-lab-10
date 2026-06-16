package graph;

import listlinked.ListLinked;

public class AdjList<E> {

    private Vertex<E> vertex;
    private ListLinked<Edge<E>> edges; // aristas

    public AdjList(Vertex<E> vertex) {
        this.vertex = vertex;
        this.edges = new ListLinked(); // lista de aristas
    }

    public Vertex<E> getVertex() {
        return vertex;
    }

    public ListLinked getEdges() {
        return edges; //adyacentes
    }
}