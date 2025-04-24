import java.util.Comparator;

public class MileageComparator<T extends Comparable<T>> implements Comparator<T> {

    @Override
    public int compare(T firstMileage, T secondMileage) {
        return firstMileage.compareTo(secondMileage);
    }

    public void sort(LinkedList<T> list) {
        list.head = mergeSort(list.head);
    }

    public Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    public Node<T> sortedMerge(Node<T> a, Node<T> b) {
        if (a == null) return b;
        if (b == null) return a;

        Node<T> result;

        if (compare(a.data, b.data) > 0) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }

        return result;
    }

    public Node<T> getMiddle(Node<T> head) {
        if (head == null) return head;

        Node<T> slow = head;
        Node<T> fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}