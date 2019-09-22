package BFS;

import java.util.*;

/**
 * 133. 克隆图
 */
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class LeetCode133 {


    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> clonedNodeQueue = new LinkedList<>();
        // origin node map to cloned node
        Map<Node, Node> clonedNodeMap = new HashMap<>();

        Set<Node> visitedNode = new HashSet<>();
        queue.offer(node);

        Node clonedNode = cloneNode(node);
        clonedNodeQueue.offer(clonedNode);
        clonedNodeMap.put(node, clonedNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Node currentClonedNode = clonedNodeQueue.poll();
            if (visitedNode.contains(currentNode)) {
                continue;
            }
            visitedNode.add(currentNode);

            for (int i = 0; i < currentNode.neighbors.size(); i++) {
                Node neighbor = currentNode.neighbors.get(i);
                queue.offer(neighbor);

                // clone neighbor
                Node clonedNeighbor = clonedNodeMap.get(neighbor);
                if (clonedNeighbor == null) {
                    clonedNeighbor = cloneNode(neighbor);
                    clonedNodeMap.put(neighbor, clonedNeighbor);
                }

                currentClonedNode.neighbors.add(clonedNeighbor);
                clonedNodeQueue.offer(clonedNeighbor);
            }

        }
        return clonedNode;
    }

    private Node cloneNode(Node node) {
        Node clonedNode = new Node();
        clonedNode.val = node.val;
        clonedNode.neighbors = new ArrayList<>(node.neighbors.size());
        return clonedNode;
    }

    public static void main(String[] args) {
        Node one = new Node(1, new LinkedList<>());
        Node two = new Node(2, new LinkedList<>());
        Node three = new Node(3, new LinkedList<>());
        Node four = new Node(4, new LinkedList<>());
        one.neighbors.add(two);
        one.neighbors.add(four);

        two.neighbors.add(one);
        two.neighbors.add(three);

        three.neighbors.add(two);
        three.neighbors.add(four);

        four.neighbors.add(one);
        four.neighbors.add(three);

        LeetCode133 leetCode133 = new LeetCode133();
        Node clonedGraph = leetCode133.cloneGraph(one);
        System.out.println(clonedGraph);
    }
}
