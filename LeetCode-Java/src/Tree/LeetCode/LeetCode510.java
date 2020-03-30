package Tree.LeetCode;

/**
 * 510. 二叉搜索树中的中序后继 II
 */
public class LeetCode510 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    public Node inorderSuccessor(Node node) {
        if (node.parent == null || node.right != null) {
            Node temp = node.right;
            while (temp != null && temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }
        if (node == node.parent.right) {
            Node temp = node.parent;
            while (temp != null && temp.parent != null && temp != temp.parent.left) {
                temp = temp.parent;
            }
            return temp.parent;
        }
        return node.parent;
    }


}
