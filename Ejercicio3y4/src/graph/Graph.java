package graph;

import listlinked.ListLinked;

public interface Graph<V, E> {

    void insertVertex(V v);

    void insertEdge(V v, V z);

    void removeVertex(V v);

    void removeEdge(V v, V z);

    boolean searchVertex(V v);

    boolean searchEdge(V v, V z);

    ListLinked<V> adjacentVertices(V v);
    
    ListLinked<V> getVertices();
}
