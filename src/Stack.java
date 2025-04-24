public class Stack<T> {
    public Node<T> head = null;

    // Default constructor
    public Stack() {
        head = null;
    }

    // Constructor with a node
    public Stack(Node<T> node) {
        head = new Node<>(node.data);
    }

    // Push a value to the stack
    public void push(T value) {
        head = new Node<>(value, head);
    }

    // Pop and return the top node
    public Node<T> popNode() {
        if (isEmpty()) return null;
        Node<T> ret = head;
        head = head.next;
        return ret;
    }

    // Peek at the top value
    public T peek() {
        if (isEmpty()) return null;
        return head.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Print stack contents without destroying
    public void print() {
        Stack<T> temp = new Stack<>();
        Node<T> current = head;

        // Push all nodes to temp while printing
        while (current != null) {
            System.out.println(current.data);
            temp.push(current.data);
            current = current.next;
        }

        // Reconstruct the original stack
        while (!temp.isEmpty()) {
            this.push(temp.popNode().data);
        }
    }

    public void reverse() {
        Stack<T> temp = new Stack<>();

        while (!this.isEmpty()) {
            temp.push(this.popNode().data);
        }

        // Reassign the head to the reversed stack's head
        this.head = temp.head;
    }
}