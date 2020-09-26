package Tree.LeetCode;

import com.sun.source.tree.Tree;

public class LeetCode116 {
    class Node extends TreeNode {
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
    }


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null ) {
            root.right.next = root.next == null ? null :  root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }


}
