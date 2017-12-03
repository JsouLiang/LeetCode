package Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Link
 * Created by X-Liang
 * 2017-12-03-14:17
 *
 * @Description:
 */
public class LinkedListOption {

    /**
     * 递归方式创建 LinkedList
     * Creates a linked list
     * @param data the data to create the list
     * @return head of the linked list; the returned linked list ends with last node with getNext() == null
     */
    public Node createdLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }

        Node firstNode = new Node(data.get(0));

        Node headOfSublist = createdLinkedList(data.subList(1, data.size()));

        firstNode.setNext(headOfSublist);

        return firstNode;
    }

    /**
     * 递归方式翻转链表
     * Reverses a linked list
     * @param head head the linked list to reverse
     * @return  head of the reversed linked list, 返回指向翻转完成后链表的头部
     */
    public Node reverserLinkedList(Node head) {
//        if (head == null) {
//            return null;
//        }
//        // 一个节点的链表
//        if (head.getNext() == null) {
//            return head;
//        }
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node newHead = reverserLinkedList(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    /**
     * 非递归方式翻转链表
     * @param head
     * @return
     */
    public Node reverseLinkedListByLoop(Node head) {
        if (head == null) {
            return head;
        }
        Node currentHead = head;
        Node newHead = null;
        while (currentHead != null) {
            Node currentHeadNext = currentHead.getNext();
            currentHead.setNext(newHead);
            newHead = currentHead;
            currentHead = currentHeadNext;
        }
        return newHead;
    }

    /**
     * 将链表中值为 target 的节点都删除掉
     * @param head  链表头结点
     * @param targetNum 要删除的节点值
     */
    public Node deleteIf(Node head, Integer targetNum) {
//        Node currentNode = head;
//        while (currentNode.getNext() != null) {
//            if (currentNode.getNext().getVal() == targetNum) {    // 当前节点下一个节点的值值为 target
//                currentNode.setNext(currentNode.getNext().getNext());
//            }
//            currentNode = currentNode.getNext();
//        }
//        return head;

        // 如果头结点连续几个节点的值都是 targetNum
        while (head != null && head.getVal() == targetNum) {
            head = head.getNext();
        }
        // 上面循环结束后，整个链表有可能都被删除，此时 head == null
        if (head == null) {
            return null;
        }

        Node prev = head;
        while (prev.getNext() != null) {
            if (prev.getNext().getVal() == targetNum) {
                // delete it
                prev.setNext(prev.getNext().getNext());

            } else {
                prev = prev.getNext();
            }
        }
        return head;

    }

    public static void main(String[] args) {
        LinkedListOption creator = new LinkedListOption();
        Node.prinfLinkedList(creator.reverserLinkedList(creator.createdLinkedList(new ArrayList<>())));
        Node.prinfLinkedList(creator.reverserLinkedList(creator.createdLinkedList(Arrays.asList(1))));
        Node.prinfLinkedList(creator.reverserLinkedList(creator.createdLinkedList(Arrays.asList(1, 2, 3, 4, 5))));
        Node.prinfLinkedList(creator.reverseLinkedListByLoop(creator.createdLinkedList(Arrays.asList(1,2,3,4,5))));
        Node.prinfLinkedList(creator.reverseLinkedListByLoop(creator.createdLinkedList(Arrays.asList(1,2,3))));
        Node.prinfLinkedList(creator.reverseLinkedListByLoop(creator.createdLinkedList(Arrays.asList(1,2))));
        Node.prinfLinkedList(creator.reverseLinkedListByLoop(creator.createdLinkedList(Arrays.asList(1))));
        Node.prinfLinkedList(creator.reverseLinkedListByLoop(creator.createdLinkedList(Arrays.asList())));
        Node head = creator.createdLinkedList(Arrays.asList(1,2, 2, 3, 2, 4, 2,5));
        Node.prinfLinkedList(creator.deleteIf(head,2));
    }
}

