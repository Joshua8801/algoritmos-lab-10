package graph;

public class Edge<E> {

    // Vértice destino
    private Vertex<E> destination;

    // Peso de la arista
    private int weight;

    // Constructor con peso por defecto = 1
    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    // Constructor con peso especificado
    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    // Retorna el vértice destino
    public Vertex<E> getDestination() {
        return destination;
    }

    // Retorna el peso de la arista
    public int getWeight() {
        return weight;
    }

    // Modifica el vértice destino
    public void setDestination(Vertex<E> destination) {
        this.destination = destination;
    }

    // Modifica el peso
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Muestra destino y peso
    @Override
    public String toString() {
        return destination + "(" + weight + ")";
    }
}