public class LinkedList<T> {
    public Node<T> head;

    // Default constructor
    public LinkedList() {
        head = null;
    }

    // Parameterized constructor
    public LinkedList(Node<T> h) {
        head = h;
    }

    // Append a node to the linked list
    public void append(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node<>(data, null);
        }
    }

    // Deep copy constructor
    public Node<T> copyList(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> newNode = new Node<>(node.data);
        newNode.next = copyList(node.next);
        return newNode;
    }

    // Print the linked list
    public void printList() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}