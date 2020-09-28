package Tree.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;

public class LeetCode117 {
    static class Node extends TreeNode {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
            super();
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public static Node createTreeWith(ArrayList<Integer> nodes) {
            if (nodes == null || nodes.size() == 0) return null;
            Node root = new Node(nodes.get(0));
            ArrayList<Node> tree = new ArrayList<>();
            tree.add(root);
            Node current = root;
            for (int i = 1; i < nodes.size(); i++) {
                int currentIndex = (int) Math.ceil(i / 2.0) - 1;
                current = tree.get(currentIndex);
                Node treeNode = null;

                if (nodes.get(i) != null) {
                    treeNode = new Node(nodes.get(i));
                    tree.add(treeNode);
                }

                if (i % 2 == 1) {
                    current.left = treeNode;
                } else if (i % 2 == 0) {
                    current.right = treeNode;
                }
            }
            return root;
        }


        public static Node createTreeWith(Integer[] nodes) {
            ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(nodes));
            return createTreeWith(list);
        }

    }

    public Node connect(Node node) {
        if (node == null) {
            return null;
        }
        Node broNex = findBroNode(node.next);

        if (node.left != null) {
            node.left.next = node.right != null ? node.right : broNex;
        }
        if (node.right != null) {
            node.right.next =  broNex;
        }
        connect(node.right);

        connect(node.left);
        return node;
    }

    Node findBroNode(Node node) {
        while (node != null) {
            if (node.left != null) {
                return node.left;
            }
            if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCode117 leetCode117 = new LeetCode117();
        Node tree = Node.createTreeWith(new Integer[]{2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7});

        leetCode117.connect(tree);

        System.out.println(tree);
    }

}
