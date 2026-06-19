package graph;

public class Edge<E> {

    private AdjList<E> destination;
    private int weight;

    public Edge(AdjList<E> destination) {
        this(destination, 1);
    }

    public Edge(AdjList<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public AdjList<E> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setDestination(AdjList<E> destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return destination.getVertex().toString();
    }
}