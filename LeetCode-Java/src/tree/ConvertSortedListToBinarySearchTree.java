package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Tree
 * Created by X-Liang
 * 2017-10-03-09:34
 *
 * @Description:
 */

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
public class ConvertSortedListToBinarySearchTree {

//    public TreeNode sortedListToBST(ListNode head) {
//        if (head == null || head.next == null) {
//            return null;
//        }
//
//        ListNode middleNode = findMiddleNode(head);
//        ListNode tail = middleNode.next;
//        middleNode.next = null;
//
//
//        TreeNode root = new TreeNode(middleNode.val);
//        root.left = sortedListToBST(head);
//        root.right = sortedListToBST(tail);
//        return root;
//    }
//
//    private ListNode findMiddleNode(ListNode Link) {
//        ListNode fastNode = Link;
//        ListNode slowNode = Link;
//
//        while (fastNode != null && fastNode.next != null) {
//            fastNode = fastNode.next.next;
//            slowNode = slowNode.next;
//        }
//        return slowNode;
//    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode middle = findMiddleNode(head, tail);
        TreeNode root = new TreeNode(middle.val);
        root.left = toBST(head, middle);
        root.right = toBST(middle.next, tail);
        return root;
    }

    private ListNode findMiddleNode(ListNode head, ListNode tail) {
        ListNode fastNode = head;
        ListNode slowNode = head;

        while (fastNode != tail && fastNode.next != tail) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }
}

class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String treeNodeToString(TreeNode root) {
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            TreeNode ret = new ConvertSortedListToBinarySearchTree().sortedListToBST(head);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}
