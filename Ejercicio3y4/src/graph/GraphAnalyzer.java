package graph;

import listlinked.ListLinked;

public class GraphAnalyzer<V> {

    private Graph<V, ?> graph;

    public GraphAnalyzer(Graph<V, ?> graph) {
        this.graph = graph;
    }
    
    // 1. CONEXO
    public boolean isConexo(V start) {

        ListLinked<V> visited = new ListLinked<>();
        dfs(start, visited);

        return visited.size() == graph.getVertices().size();
    }

    private void dfs(V v, ListLinked<V> visited) {

        if (contains(visited, v)) return;

        visited.addLast(v);

        ListLinked<V> adj = graph.adjacentVertices(v);

        for (int i = 0; i < adj.size(); i++) {
            dfs(adj.get(i), visited);
        }
    }

    private boolean contains(ListLinked<V> list, V v) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(v)) return true;
        }
        return false;
    }

    // 2. ISOMORFO
    public boolean isIsomorfo(Graph<V, ?> g2) {

        return graph.getVertices().size() == g2.getVertices().size()
                && edgeCount(graph) == edgeCount(g2);
    }

    private int edgeCount(Graph<V, ?> g) {

        ListLinked<V> vertices = g.getVertices();
        int count = 0;

        for (int i = 0; i < vertices.size(); i++) {

            ListLinked<V> adj = g.adjacentVertices(vertices.get(i));

            count += adj.size();
        }

        return count / 2;
    }

    // 3. PLANO 
    public boolean isPlano() {

        int V = graph.getVertices().size();
        int E = edgeCount(graph);

        if (V <= 2) return true;

        return E <= (3 * V - 6);
    }

    // 4. AUTO-COMPLEMENTARIO
    public boolean isAutoComplementario() {

        Graph<V, ?> complement = buildComplement();

        return isIsomorfo(complement);
    }

    private Graph<V, ?> buildComplement() {

        GraphLink<V, Object> comp = new GraphLink<>();

        ListLinked<V> vertices = graph.getVertices();

        for (int i = 0; i < vertices.size(); i++) {
            comp.insertVertex(vertices.get(i));
        }

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {

                if (i != j) {

                    V a = vertices.get(i);
                    V b = vertices.get(j);

                    if (!graph.searchEdge(a, b)) {
                        comp.insertEdge(a, b);
                    }
                }
            }
        }

        return comp;
    }
}