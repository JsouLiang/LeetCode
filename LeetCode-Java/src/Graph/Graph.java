package Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface Graph<V, W> {
    int verticesSize();
    int edgesSize();

    Vertex<V, W> addVertex(V v);
    void addEdge(V from, V to);
    void addEdge(V from, V to, W weight);

    void removeVertex(V v);
    void removeEdge(V from, V to);

    /**
     * 顶点
     */
    class Vertex<V, W> {
        V value;
        /// 以当前节点为起点的边
        Set<Edge<V, W>> toEdge = new HashSet<>();
        /// 以当前节点为终点的边
        Set<Edge<V, W>> fromEdge = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * 边
     */
    class Edge<V, W> {
        Vertex<V, W> from;
        Vertex<V, W> to;
        W weight;

        public Edge(Vertex<V, W> from, Vertex<V, W> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
