package listlinked;

public class ListLinked<E> {

    private Node<E> head;
    private int size;

    public ListLinked() {
        head = null;
        size = 0;
    }

    public void addLast(E data) {

        Node<E> newNode =
                new Node<>(data);

        if(head == null) {
            head = newNode;
        }
        else {

            Node<E> aux = head;

            while(aux.getNext() != null)
                aux = aux.getNext();

            aux.setNext(newNode);
        }

        size++;
    }

    public E get(int index) {

        Node<E> aux = head;

        for(int i=0; i<index; i++)
            aux = aux.getNext();

        return aux.getData();
    }

    public int size() {
        return size;
    }
}