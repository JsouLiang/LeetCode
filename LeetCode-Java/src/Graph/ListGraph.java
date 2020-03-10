package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListGraph<V, W> implements Graph<V, W> {
    Map<V, Vertex<V, W>> vertexMap = new HashMap<>();
    Set<Edge<V, W>> edges = new HashSet<>();

    @Override
    public int verticesSize() {
        return vertexMap.size();
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public Vertex<V, W> addVertex(V v) {
        Vertex<V, W> vertex = new Vertex<>(v);
        vertexMap.putIfAbsent(v, vertex);
        return vertex;
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        Vertex fromVertex = addVertex(from);
        Vertex toVertex = addVertex(to);
        Edge edge = new Edge(fromVertex, toVertex);
        /// 删掉之前存在的边
        if (fromVertex.toEdge.remove(edge)) {
            toVertex.fromEdge.remove(edge);
            edges.remove(edge);
        }
        edge.weight = weight;
        fromVertex.toEdge.add(edge);
        toVertex.fromEdge.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void removeEdge(V from, V to) {

    }
}
