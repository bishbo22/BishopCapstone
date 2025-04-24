//foundation for linked list class and usage of that class
public class Node<T> {
    T data;
    Node<T> next = null;

    // Default constructor
    public Node(T data) {
        this.data = data;
    }

    // Parameterized constructor
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}