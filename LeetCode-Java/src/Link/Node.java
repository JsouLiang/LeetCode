package Link;

/**
 * Link
 * Created by X-Liang
 * 2017-10-03-09:35
 *
 * @Description:
 */
class Node {
    private final int val;
    private Node next;
    Node(int x) { val = x; next = null; }

    public int getVal() {
        return val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static void prinfLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.getVal());
            System.out.print(" ");
            head = head.getNext();
        }
        System.out.print("\n");
    }
}
