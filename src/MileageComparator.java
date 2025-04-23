import java.util.Comparator;

// implementing a comparator interface
public class MileageComparator implements Comparator<Double> {
    //the compare method takes the two parameters of games and sees if the first score is smaller (-1), the same (0), or larger (1)
    @Override
    public int compare(Double firstMileage,Double secondMileage){
        return Double.compare(firstMileage,secondMileage);
    }

    // the sort method is for sorting the list of games by critic score using the parameters provided for the linked list and how many times the loop needs to be executed
    public void sort(LinkedList doubles) {
        if (doubles.head == null || doubles.head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node prev = null;
            Node curr = doubles.head;

            while (curr != null && curr.next != null) {
                Node next = curr.next;

                if (compare(curr.data, next.data) < 0) { // Compare properly
                    // Swap nodes correctly
                    if (prev != null) {
                        prev.next = next;
                    } else {
                        doubles.head = next;
                    }
                    curr.next = next.next;
                    next.next = curr;
                    prev = next;
                    swapped = true;
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            }
        } while (swapped); // Ensure multiple passes until fully sorted
    }

    // this swap method takes the current node and swaps it with the next one depending on the swapping condition in the sort method, taking in a linked list of type game and next of type node
    public void swap(Node curr,Node next){
        curr.next = next.next;
        next.next = curr;
    }
}