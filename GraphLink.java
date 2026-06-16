package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import listlinked.ListLinked;

/*
 * Grafo no dirigido usando listas de adyacencia
 */
public class GraphLink<E> {

    // Lista principal del grafo
    private ListLinked<AdjList<E>> graph;

    // Constructor
    public GraphLink() {
        graph = new ListLinked<>();
    }

    // Inserta un nuevo vértice
    public void insertVertex(E data) {

        Vertex<E> vertex =
                new Vertex<>(data);

        graph.addLast(
                new AdjList<>(vertex)
        );
    }

    // Busca un vértice en el grafo
    private AdjList<E> findVertex(E data) {

        for(int i = 0; i < graph.size(); i++) {

            AdjList<E> adj =
                    graph.get(i);

            if(adj.getVertex()
                    .getData()
                    .equals(data)) {

                return adj;
            }
        }

        return null;
    }

    // Inserta una arista sin peso
    public void insertEdge(E origin,
                           E destination) {

        AdjList<E> v1 =
                findVertex(origin);

        AdjList<E> v2 =
                findVertex(destination);

        if(v1 == null || v2 == null)
            return;

        v1.getEdges().addLast(
                new Edge<>(v2.getVertex())
        );

        v2.getEdges().addLast(
                new Edge<>(v1.getVertex())
        );
    }

    // Inserta una arista con peso
    public void insertEdgeWeight(E origin,
                                 E destination,
                                 int weight) {

        // Buscar vértices
        AdjList<E> v1 =
                findVertex(origin);

        AdjList<E> v2 =
                findVertex(destination);

        // Verificar existencia
        if(v1 == null || v2 == null)
            return;

        // Origen -> destino
        v1.getEdges().addLast(
                new Edge<>(v2.getVertex(),
                        weight)
        );

        // Destino -> origen
        v2.getEdges().addLast(
                new Edge<>(v1.getVertex(),
                        weight)
        );
    }

    // Algoritmo de Dijkstra
    public Stack<E> Dijkstra(E start,
                             E end) {

        // Distancias mínimas
        Map<E,Integer> dist =
                new HashMap<>();

        // Nodo anterior
        Map<E,E> prev =
                new HashMap<>();

        // Cola de prioridad
        PriorityQueue<E> pq =
                new PriorityQueue<>(
                        (a,b) ->
                                dist.get(a)
                                        - dist.get(b)
                );

        // Inicializar distancias
        for(int i = 0;
            i < graph.size();
            i++) {

            E vertex =
                    graph.get(i)
                            .getVertex()
                            .getData();

            dist.put(
                    vertex,
                    Integer.MAX_VALUE
            );
        }

        // Distancia inicial
        dist.put(start,0);

        // Insertar origen
        pq.add(start);

        while(!pq.isEmpty()) {

            // Nodo más cercano
            E current =
                    pq.poll();

            // Obtener vecinos
            AdjList<E> adj =
                    findVertex(current);

            for(int i = 0;
                i < adj.getEdges().size();
                i++) {

                // Arista actual
                Edge<E> edge =
                        adj.getEdges()
                                .get(i);

                // Vecino actual
                E neighbor =
                        edge.getDestination()
                                .getData();

                // Calcular nueva distancia
                int newDistance =
                        dist.get(current)
                                + edge.getWeight();

                // Mejor camino encontrado
                if(newDistance <
                        dist.get(neighbor)) {

                    // Actualizar distancia
                    dist.put(
                            neighbor,
                            newDistance
                    );

                    // Guardar predecesor
                    prev.put(
                            neighbor,
                            current
                    );

                    // Agregar a la cola
                    pq.add(neighbor);
                }
            }
        }

        // Ruta inversa
        Stack<E> reverse =
                new Stack<>();

        E current = end;

        while(current != null) {

            reverse.push(current);

            current =
                    prev.get(current);
        }

        // Ruta final
        Stack<E> path =
                new Stack<>();

        while(!reverse.isEmpty()) {

            path.push(
                    reverse.pop()
            );
        }

        return path;
    }

    // Devuelve la ruta más corta
    public ArrayList<E> shortPath(E origin,
                                  E destination) {

        // Ejecutar Dijkstra
        Stack<E> stack =
                Dijkstra(origin,
                        destination);

        // Lista resultado
        ArrayList<E> path =
                new ArrayList<>();

        while(!stack.isEmpty()) {

            path.add(
                    stack.pop()
            );
        }

        return path;
    }

    // Verifica si el grafo es conexo
    public boolean isConexo() {

        // Grafo vacío
        if(graph.size() == 0)
            return true;

        // Vértices visitados
        Set<E> visited =
                new HashSet<>();

        // Cola BFS
        Queue<E> queue =
                new LinkedList<>();

        // Primer vértice
        E start =
                graph.get(0)
                        .getVertex()
                        .getData();

        visited.add(start);
        queue.add(start);

        while(!queue.isEmpty()) {

            // Sacar vértice
            E current =
                    queue.poll();

            // Obtener vecinos
            AdjList<E> adj =
                    findVertex(current);

            for(int i = 0;
                i < adj.getEdges().size();
                i++) {

                E neighbor =
                        adj.getEdges()
                                .get(i)
                                .getDestination()
                                .getData();

                // Si no fue visitado
                if(!visited.contains(
                        neighbor)) {

                    visited.add(
                            neighbor
                    );

                    queue.add(
                            neighbor
                    );
                }
            }
        }

        // Comparar visitados
        return visited.size()
                == graph.size();
    }

    // Mostrar el grafo
    @Override
    public String toString() {

        StringBuilder sb =
                new StringBuilder();

        for(int i = 0;
            i < graph.size();
            i++) {

            AdjList<E> adj =
                    graph.get(i);

            sb.append(
                    adj.getVertex()
            );

            sb.append(" -> ");

            for(int j = 0;
                j < adj.getEdges().size();
                j++) {

                Edge<E> edge =
                        adj.getEdges()
                                .get(j);

                sb.append(
                        edge.getDestination()
                );

                sb.append("(");

                sb.append(
                        edge.getWeight()
                );

                sb.append(") ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}