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

        if (searchVertex(v)) return;

        Vertex<V> vertex = new Vertex<>(v);
        graph.addLast(new AdjList<>(vertex));
    }

    // BUSCAR VÉRTICE
    @Override
    public boolean searchVertex(V v) {

        for (int i = 0; i < graph.size(); i++) {

            if (graph.get(i).getVertex().getData().equals(v)) {
                return true;
            }
        }
        return false;
    }

    // BUSCAR NODO COMPLETO
    private AdjList<V> getVertex(V v) {

        for (int i = 0; i < graph.size(); i++) {

            if (graph.get(i).getVertex().getData().equals(v)) {
                return graph.get(i);
            }
        }
        return null;
    }

    // INSERTAR ARISTA (NO DIRIGIDO)
    @Override
    public void insertEdge(V v, V z) {

        AdjList<V> a = getVertex(v);
        AdjList<V> b = getVertex(z);

        if (a == null || b == null) return;

        // evitar duplicados
        if (searchEdge(v, z)) return;

        a.getEdges().addLast(new Edge<>(b.getVertex()));
        b.getEdges().addLast(new Edge<>(a.getVertex()));
    }

    // BUSCAR ARISTA
    @Override
    public boolean searchEdge(V v, V z) {

        AdjList<V> a = getVertex(v);

        if (a == null) return false;

        for (int i = 0; i < a.getEdges().size(); i++) {

            if (a.getEdges().get(i)
                    .getDestination()
                    .getData()
                    .equals(z)) {
                return true;
            }
        }

        return false;
    }

    // ELIMINAR ARISTA
    @Override
    public void removeEdge(V v, V z) {

        AdjList<V> a = getVertex(v);
        AdjList<V> b = getVertex(z);

        if (a == null || b == null) return;

        // eliminar v -> z
        for (int i = 0; i < a.getEdges().size(); i++) {

            if (a.getEdges().get(i)
                    .getDestination()
                    .getData()
                    .equals(z)) {
                a.getEdges().remove(i);
                break;
            }
        }

        // eliminar z -> v
        for (int i = 0; i < b.getEdges().size(); i++) {

            if (b.getEdges().get(i)
                    .getDestination()
                    .getData()
                    .equals(v)) {
                b.getEdges().remove(i);
                break;
            }
        }
    }

    // ELIMINAR VÉRTICE
    @Override
    public void removeVertex(V v) {

        // eliminar el vértice
        for (int i = 0; i < graph.size(); i++) {

            if (graph.get(i).getVertex().getData().equals(v)) {
                graph.remove(i);
                break;
            }
        }

        // eliminar todas las aristas asociadas
        for (int i = 0; i < graph.size(); i++) {

            removeEdge(
                graph.get(i).getVertex().getData(),
                v
            );
        }
    }

    // VÉRTICES ADYACENTES
    @Override
    public ListLinked<V> adjacentVertices(V v) {

        ListLinked<V> list = new ListLinked<>();

        AdjList<V> node = getVertex(v);

        if (node == null) return list;

        for (int i = 0; i < node.getEdges().size(); i++) {

            list.addLast(
                node.getEdges()
                    .get(i)
                    .getDestination()
                    .getData()
            );
        }

        return list;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {

            AdjList<V> adj = graph.get(i);

            sb.append(adj.getVertex().getData())
              .append(" -> ");

            for (int j = 0; j < adj.getEdges().size(); j++) {

                sb.append(
                    adj.getEdges().get(j)
                        .getDestination()
                        .getData()
                ).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
    
    @Override
    public ListLinked<V> getVertices() {

        ListLinked<V> list = new ListLinked<>();

        for (int i = 0; i < graph.size(); i++) {
            list.addLast(graph.get(i).getVertex().getData());
        }

        return list;
    }
}