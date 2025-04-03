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

//    public void sort(LinkedList doubles) {
//        //check if the list is empty or only has one value, meaning it is already sorted and the program can end
//        if (doubles.head == null || doubles.head.next == null){
//            return;
//        }
//        boolean swapped = false; //initialize the boolean condition for the while loop to be exited (if statement at the bottom of the following while loop)
//        Node prev = null; //initialize the previous node
//        Node curr = doubles.head; //initialize the current node
//        // this loop will sort everything in descending critic rating order until complete
//        //find the lower of the two adjacent nodes and move it/keep it to the right of the linked list, this also leaves nodes that are the in the correct order/are equal where they are
//        while (curr.next != null) {
//            Node next = curr.next; //initialize the next node
//            //compare two elements (using criticScore for comparison)
//            while (!swapped) {
//                if (compare(curr.data, next.data) < 0) { //make this ">0" for ascending order
//                    swap(curr, next); //uses the swap method to swap their order so that it can continue to run the sorting for the rest of the values in the critic score variable of the game objects
//
//                    //the following if-else statement checks if the previous node has been declared yet and either sets the head of the list to the next value or the previous node to the next value to continue the loop working through the linked list
//                    if (prev == null){
//                        doubles.head = next;
//                    }
//                    else{
//                        prev.next = next;
//                    }
//
//                    //this declares the previous node as the next node because it has had its position placed next due to the swap
//                    prev = next;
//                    swapped = true; //let the for loop know that a swap occurred
//                }
//                else{
//                    prev = curr;
//                    curr = curr.next;
//                }
//            }
//        }
//    }

    // this swap method takes the current node and swaps it with the next one depending on the swapping condition in the sort method, taking in a linked list of type game and next of type node
    public void swap(Node curr,Node next){
        curr.next = next.next;
        next.next = curr;
    }
}