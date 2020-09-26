package Basic;

import java.util.HashMap;
import java.util.Map;

public class LeetCode990 {
    class UnionFind {
        class UnionFindNode {
            Character value;
            UnionFindNode parent;

            public UnionFindNode(Character value, UnionFindNode parent) {
                this.value = value;
                this.parent = parent;
            }

            public UnionFindNode(Character value) {
                this(value, null);
            }
        }
        Map<Character, UnionFindNode> values;

        public UnionFind() {
            this.values = new HashMap<>();
        }

        void insert(Character value) {
            UnionFindNode node = new UnionFindNode(value);
            node.parent = node;
            values.put(value, node);
        }

        boolean checkInSameSet(Character one, Character two) {
            UnionFindNode oneNode = get(one);
            UnionFindNode twoNode = get(two);

            while (oneNode.parent != oneNode || twoNode.parent!=twoNode) {
                oneNode = oneNode.parent;
                twoNode = twoNode.parent;
            }
            return oneNode.value == twoNode.value;
        }

        void attatch(Character attatched, Character parent) {
            if (attatched == parent) {
                return;
            }
            UnionFindNode node = get(parent);
            while (node.parent != node) {
                node = node.parent;
            }

            UnionFindNode attatchedNode = get(attatched);
            while (attatchedNode.parent != attatchedNode) {
                attatchedNode = attatchedNode.parent;
            }
            if (node == attatchedNode) {
                return;
            }
            attatchedNode.parent = node;
        }

        UnionFindNode get(Character value) {
            UnionFindNode node = values.get(value);
            if (node == null) {
                node = new UnionFindNode(value);
                node.parent = node;
                values.put(value, node);
            }
            return node;
        }

        boolean isContain(Character value) {
            return values.get(value) != null;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind();
        for (String equation: equations) {
            char[] characters = equation.toCharArray();
            Character one = characters[0]; Character two = characters[characters.length - 1];
            if (!unionFind.isContain(one) && !unionFind.isContain(two)) {
                unionFind.insert(one);
                unionFind.insert(two);
            }
            if (equation.charAt(1) == '=') {
                unionFind.attatch(two, one);
            }
        }

        for (String equation : equations) {
            char[] characters = equation.toCharArray();
            Character one = characters[0]; Character two = characters[characters.length - 1];
            boolean equal = characters[1] == '=';
            if (!equal && unionFind.checkInSameSet(one, two)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode990 leetCode990 = new LeetCode990();
        leetCode990.equationsPossible(new String[]{
                "f==a","a==b","f!=e","a==c","b==e","c==f"
        });
    }
}
