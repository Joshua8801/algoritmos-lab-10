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
   
    public boolean isIsomorfo(Graph<V, ?> otro) {

        ListLinked<V> v1 = graph.getVertices();
        ListLinked<V> v2 = otro.getVertices();

        if(v1.size() != v2.size())return false;

        ListLinked<V> asignados = new ListLinked<>();

        return buscarIsomorfismo(v1,v2,asignados,0,otro);
    }

    private boolean buscarIsomorfismo(ListLinked<V> g1,ListLinked<V> g2,ListLinked<V> mapa,int pos,Graph<V, ?> otro) {

        // todos los vértices fueron asignados
        if(pos == g1.size())
            return true;

        V actual = g1.get(pos);

        for(int i=0;i<g2.size();i++){

            V candidato = g2.get(i);
            
            // evitar repetir vértice
            if(contains(mapa,candidato))continue;

            mapa.addLast(candidato);

            if(verificarCompatibilidad(g1,mapa,pos,otro)) {

                if(buscarIsomorfismo(g1,g2,mapa,pos+1,otro))
                    return true;
            }
            mapa.remove(mapa.size()-1);
        }
        return false;
    }

    private boolean verificarCompatibilidad(ListLinked<V> g1,ListLinked<V> mapa,int pos,Graph<V, ?> otro){

        V original = g1.get(pos);
        V asignado = mapa.get(pos);

        // compara conexiones con los anteriores
        for(int i=0;i<pos;i++){
        	
            V anteriorOriginal = g1.get(i);
            V anteriorAsignado = mapa.get(i);
            
            boolean e1 =graph.searchEdge(original,anteriorOriginal);
            boolean e2 =otro.searchEdge(asignado,anteriorAsignado);
            if(e1 != e2)return false;
        }

        return true;
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
