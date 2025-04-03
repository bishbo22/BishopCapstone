public class LinkedList {
    public Node head;

    //default constructor
    public LinkedList() {
        head = null;
    }

    //parametrized constructor
    public LinkedList(Node h) {
        head = h;
    }

    //append a node to the linked list, worked on during in-class activity
    public void append(double num) {
        if (head == null) {
            head = new Node(num);
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node(num, null);
        }
    }

    //deep copy constructor from the in-class practice for future linked list work
    public Node copy_list(Node head) {
        if (head == null) {
            return null;
        }
        Node newNode = new Node(head.data);
        newNode.next = copy_list(head.next);
        return newNode;
    }

    //printing the linked list for debugging the test file
    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}