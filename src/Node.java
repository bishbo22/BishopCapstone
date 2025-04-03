//foundation for linked list class and usage of that class
public class Node{
    double data;
    Node next=null;

    //default constructor
    public Node(double i){
        data=i;
    }

    //parametrized constructor
    public Node(double i, Node n){
        data=i;
        next=n;
    }
}