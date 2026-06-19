package graph;

import listlinked.ListLinked;

public class GraphLink<V, E> implements Graph<V, E> {

    private ListLinked<AdjList<V>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    // INSERTAR VÉRTICE
    @Override
    public void insertVertex(V v) {

        if (getVertex(v) != null) return;

        Vertex<V> vertex = new Vertex<>(v);

        graph.addLast(new AdjList<>(vertex));
    }

    // BUSCAR VÉRTICE
    @Override
    public boolean searchVertex(V v) {

        return getVertex(v) != null;
    }

    // BUSCAR NODO COMPLETO
    private AdjList<V> getVertex(V v) {

        for (int i = 0; i < graph.size(); i++) {

            AdjList<V> current = graph.get(i);

            if (current.getVertex().getData().equals(v)) {
                return current;
            }
        }
        return null;
    }

    // BUSCAR ARISTA USANDO NODO DIRECTO
    private boolean searchEdge(AdjList<V> source, V destination) {

        for (int i = 0; i < source.getEdges().size(); i++) {

            if (source.getEdges().get(i).getDestination().getVertex().getData().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    // INSERTAR ARISTA (NO DIRIGIDO)
    @Override
    public void insertEdge(V v, V z) {

        AdjList<V> a = getVertex(v);
        AdjList<V> b = getVertex(z);

        if (a == null || b == null)
            return;

        if (searchEdge(a, z))
            return;

        // v -> z
        a.getEdges().addLast(new Edge<>(b));

        // z -> v
        b.getEdges().addLast(new Edge<>(a));
    }

    // BUSCAR ARISTA
    @Override
    public boolean searchEdge(V v, V z) {

        AdjList<V> a = getVertex(v);

        if (a == null)return false;
       
        return searchEdge(a, z);
    }

    // ELIMINAR ARISTA
    @Override
    public void removeEdge(V v, V z) {

        AdjList<V> a = getVertex(v);
        AdjList<V> b = getVertex(z);

        if (a == null || b == null)return;

        // eliminar v--z
        for (int i = 0; i < a.getEdges().size(); i++) {

            if (a.getEdges().get(i).getDestination().equals(b)) {
                a.getEdges().remove(i);
                break;
            }
        }

        // eliminar z--v
        for (int i = 0; i < b.getEdges().size(); i++) {

            if (b.getEdges().get(i).getDestination().equals(a)) {
                b.getEdges().remove(i);
                break;
            }
        }
    }

    // ELIMINAR VÉRTICE
    @Override
    public void removeVertex(V v) {

        AdjList<V> nodo = getVertex(v);

        if (nodo == null)
            return;

        // eliminar conexiones de sus vecinos
        for (int i = 0; i < nodo.getEdges().size(); i++) {

            Edge<V> edge = nodo.getEdges().get(i);

            AdjList<V> vecino = edge.getDestination();

            for (int j = 0; j < vecino.getEdges().size(); j++) {
                if (vecino.getEdges().get(j).getDestination().equals(nodo)) {
                    vecino.getEdges().remove(j);
                    break;
                }
            }
        }

        // eliminar el vértice del grafo
        for (int i = 0; i < graph.size(); i++) {
        	if(graph.get(i).equals(nodo)) {

        	    graph.remove(i);
        	    break;
            }
        }
    }

    // VÉRTICES ADYACENTES
    @Override
    public ListLinked<V> adjacentVertices(V v) {

        ListLinked<V> list = new ListLinked<>();

        AdjList<V> node = getVertex(v);

        if (node == null) return list;

        for (int i = 0; i < node.getEdges().size(); i++) {

            list.addLast(node.getEdges().get(i).getDestination().getVertex().getData());
        }

        return list;
    }

    // OBTENER VÉRTICES
    @Override
    public ListLinked<V> getVertices() {

        ListLinked<V> list = new ListLinked<>();
        for (int i = 0; i < graph.size(); i++) {

            list.addLast(graph.get(i).getVertex().getData()
            );
        }
        return list;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {

            AdjList<V> adj =graph.get(i);

            sb.append(adj.getVertex().getData()).append(" -> ");

            for (int j = 0; j < adj.getEdges().size(); j++) {

                sb.append(adj.getEdges().get(j).getDestination().getVertex().getData()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}