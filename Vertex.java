package graph;

public class Vertex<E> {

    // Dato almacenado en el vértice
    private E data;

    // Constructor
    public Vertex(E data) {
        this.data = data;
    }

    // Retorna el dato del vértice
    public E getData() {
        return data;
    }

    // Modifica el dato del vértice
    public void setData(E data) {
        this.data = data;
    }

    // Representación en texto del vértice
    @Override
    public String toString() {
        return data.toString();
    }
}