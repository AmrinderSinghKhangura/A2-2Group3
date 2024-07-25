package utility;

/**
 * Node class for MyDLL
 * @param <E> Generic type
 */
public class Node<E>
{
    E data;
    Node next;
    Node prev;

    /**
     * Constructor
     * @param data Data to be stored in the node
     */
    public Node(E data)
    {
        this.data = data;
    }
}
