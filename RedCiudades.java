package graph;

public class RedCiudades {

    public static void main(String[] args) {

        GraphLink<String> red =
                new GraphLink<>();

        // Ciudades
        red.insertVertex("Arequipa");
        red.insertVertex("Cusco");
        red.insertVertex("Puno");
        red.insertVertex("Tacna");
        red.insertVertex("Moquegua");

        // Carreteras
        red.insertEdgeWeight("Arequipa", "Cusco", 510);
        red.insertEdgeWeight("Arequipa", "Moquegua", 230);
        red.insertEdgeWeight("Moquegua", "Tacna", 160);
        red.insertEdgeWeight("Cusco", "Puno", 390);
        red.insertEdgeWeight("Puno", "Tacna", 420);

        System.out.println("===== LISTA DE CIUDADES =====");
        System.out.println(red);

        System.out.println("===== CAMINO MAS CORTO =====");
        System.out.println(
                red.shortPath(
                        "Arequipa",
                        "Tacna")
        );

        System.out.println("===== ES CONEXO =====");
        System.out.println(
                red.isConexo()
        );
    }
}