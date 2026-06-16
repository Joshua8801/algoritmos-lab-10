package listlinked;

public class ListLinked<T> {

    private Node<T> head;
    private int size;

    public ListLinked() {
        this.head = null;
        this.size = 0;
    }

    // INSERTAR AL FINAL
    public void addLast(T data) {

        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {

            Node<T> current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }

        size++;
    }

    // OBTENER POR ÍNDICE
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    // ELIMINAR POR ÍNDICE
    public void remove(int index) {

        if (index < 0 || index >= size) return;

        if (index == 0) {
            head = head.getNext();
        } else {

            Node<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());
        }

        size--;
    }

    // TAMAÑO
    public int size() {
        return size;
    }

    // VACÍO
    public boolean isEmpty() {
        return head == null;
    }

    // LIMPIAR LISTA
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Node<T> current = head;

        while (current != null) {
            sb.append(current.getData()).append(" -> ");
            current = current.getNext();
        }

        sb.append("null");

        return sb.toString();
    }
}