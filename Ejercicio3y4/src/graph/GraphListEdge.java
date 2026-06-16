package graph;

public class GraphListEdge<V> {

    private V origin;
    private V destination;

    public GraphListEdge(V origin, V destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public V getOrigin() {
        return origin;
    }

    public V getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return origin + " -> " + destination;
    }
}