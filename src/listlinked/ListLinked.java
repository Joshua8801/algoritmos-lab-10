package listlinked;

public class ListLinked<E> {

    private Node<E> head;
    private int size;

    public ListLinked() {
        head = null;
        size = 0;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<E> aux = head;

            while (aux.next != null) {
                aux = aux.next;
            }

            aux.next = newNode;
        }

        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<E> aux = head;

        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }

        return aux.data;
    }

    //cantidad de elementos de la lista
    public int size() {
        return size;
    }
}