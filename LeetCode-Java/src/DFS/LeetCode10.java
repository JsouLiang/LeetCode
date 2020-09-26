package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 10. 正则表达式匹配
 */
public class LeetCode10 {

    public boolean isMatch(String s, String p) {
        NFA nfa = new NFA("(" + p + ")");
        boolean res = nfa.isMatch(s);
        return res;
    }
    public static void main(String[] ags) {
        LeetCode10 leetCode10 = new LeetCode10();
        leetCode10.isMatch("aa", ".*");
    }
}

class DFSSolution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        /// 1. 查看首元素是否一致
        /// s与 p 首字符一致或者 p 为 .
        boolean first_match = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        /// 如果p 中下一个元素是 *
        if (p.length() >= 2 && p.charAt(1) == '*') {
            /// 两种匹配策略
            /// 1. p 的第一个元素在 s 中出现 0 次
            /// * s: ab p: b*ab, 保持 s 不变，减去 p 前两个
            boolean matched_1 = isMatch(s, p.substring(2));
            /// 2. p 的第一个元素在 s 中出现 >= 1 次，前提是* 前面的字符与 s 中对应的字符一致
            /// * s: aaab p: a*b, 保持 p 不变，不断减去 s 第一个元素
            boolean matched_2 = first_match && isMatch(s.substring(1), p);
            return matched_1 || matched_2;
        } else {
            /// s, p 同时后移
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}

class NFA {
    private Digraph digraph;
    /// 正则表达式
    private String regexp;
    /// 正则表达式中字符数量
    private final int m;

    public NFA(String regexp) {
        this.regexp = regexp;
        m = regexp.length();
        digraph = new Digraph(m + 1);
        Stack<Integer> ops = new Stack<>();

        for (int i = 0; i < m; i++) {
            int lp = i;
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') {
                ops.push(i);
            } else if (regexp.charAt(i) == ')') {
                int top = ops.pop();
                /// or
                /// (A|B)C
                /// | -> ), ( -> C
                if (regexp.charAt(top) == '|') {
                    /// 此时 lp 为 (
                    lp = ops.pop();
                    /// | -> )
                    digraph.addEdge(top, i);
                    /// ( -> C
                    digraph.addEdge(lp, i + 1);

                } else if (regexp.charAt(top) == '(') {
                    /// lp 指向 (
                    lp = top;
                }
            }

            /// A* : A -> *, * -> A
            /// (A)* : ( -> *, * -> )
            /// lp 可以是字符，或者是前面从栈中获取的(
            if (i < m - 1 && regexp.charAt(i + 1) == '*') {
                digraph.addEdge(lp, i + 1);
                digraph.addEdge(i + 1, lp);
            }
            /// (, *, ) 这几个字符可以直接指向下一个字符
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') {
                digraph.addEdge(i, i + 1);
            }
        }
        System.out.println("");
    }

    public boolean isMatch(String txt) {
        VisitGraph visitGraph = new VisitGraph(digraph, 0);
        List<Integer> status = new ArrayList<>();
        for (int i = 0; i < digraph.getV(); i++) {
            if (visitGraph.isVisited(i)) {
                status.add(i);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            List<Integer> match = new ArrayList<>();
            for (int v : status) {
                if (v == m) { continue; }
                if (regexp.charAt(v) == txt.charAt(i) || regexp.charAt(v) == '.') {
                    match.add(v + 1);
                }
            }
            visitGraph = new VisitGraph(digraph, match);
            status.clear();
            for (int v = 0; v < digraph.getV(); v++) {
                if (visitGraph.isVisited(v)) {
                    status.add(v);
                }
            }

            if (status.size() == 0) {
                return false;
            }
        }

        for (int v : status) {
            if (v == m) {
                return true;
            }
        }
        return false;
    }
}

/**
 * 使用邻接表构建有向图
 */
class Digraph {
    /// 顶点
    private final int V;
    /// 边
    private int E;
    /// 每个点的入度
    private int[] indegree;
    /// adj[i] 表示顶点 i 的邻接表
    private List<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.adj = new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    /**
     * b 到 e 添加边 b->e
     * @param b 起点
     * @param e 终点
     */
    public void addEdge(int b, int e) {
        /// b 的邻接表中添加e 节点
        adj[b].add(e);
        /// b 的入度++
        indegree[b]++;
        /// 边数++
        E++;
    }

    /**
     * 获得节点 v 的相邻的点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 获得节点 v 的出度，即节点 v 指出去的点
     */
    public int outdegree(int v) {
        return adj[v].size();
    }

    public int indegree(int v) {
        return indegree[v];
    }
}

class VisitGraph {
    private boolean[] visited;
    private int count;
    /// 从 s 节点开始访问图
    public VisitGraph(Digraph digraph, int s) {
        visited = new boolean[digraph.getV()];
        visit(digraph, s);
    }

    public VisitGraph(Digraph digraph, Iterable<Integer> sources) {
        visited = new boolean[digraph.getV()];
        for (int v: sources) {
            if (!visited[v]) {
                visit(digraph, v);
            }
        }
    }

    private void visit(Digraph digraph, int v) {
        count++;
        visited[v] = true;
        for (int next: digraph.adj(v)) {
            if (!visited[v]) {
                visit(digraph, next);
            }
        }
    }

    /// 是否访问过节点 v
    public boolean isVisited(int v) {
        return visited[v];
    }
}
