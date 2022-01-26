package mobi.zishun.graph;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 无向图
 * 邻接表存储
 * 实现DFS搜索
 */
public class GraphWithDFS {
    private final int vertexNum; //顶点的个数
    private final LinkedList<Integer>[] adjList; //邻接表(Adjacency List)

    private boolean found;

    public GraphWithDFS(int vertexNum) {
        this.vertexNum = vertexNum;
        adjList = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adjList[s].add(t);
        adjList[t].add(s);
    }

    public void dfs(int start, int target) {
        found = false;
        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问。
        boolean[] visited = new boolean[vertexNum];

        // prev用来记录搜索路径。
        int[] prev = new int[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            prev[i] = -1;
        }
        recurDfs(start, target, visited, prev);
        print(prev, start, target);
    }

    private void recurDfs(int start, int target, boolean[] visited, int[] prev) {
        // 提前终止递归
        if (found) {
            return;
        }
        visited[start] = true;
        if (start == target) {
            found = true;
            return;
        }

        for (int i = 0; i < adjList[start].size(); i++) {
            // start的节点的邻接节点
            int adjVertex = adjList[start].get(i);
            if (!visited[adjVertex]) {
                prev[adjVertex] = start;
                // 递归处理此邻接节点（作为新的start节点进入递归）
                recurDfs(adjVertex, target, visited, prev);
            }
        }
    }

    public void dfsByStack(int start, int target) {
        boolean[] visited = new boolean[vertexNum];
        int[] prev = new int[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            prev[i] = -1;
        }

        Deque<Integer> stack = new LinkedList<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int curVertex = stack.pop();
            for (int i = 0; i < adjList[curVertex].size(); i++) {
                int adjVertex = adjList[curVertex].get(i);
                if (!visited[adjVertex]){
                    prev[adjVertex] = curVertex;
                    if (adjVertex == target) {
                        print(prev, start, target);
                        return;
                    }
                    visited[adjVertex] = true;
                    stack.push(adjVertex);
                }
            }
        }
    }

    private void print(int[] prev, int start, int target) {
        // 递归打印 s->t 的路径
        if (prev[target] != -1 && target != start) {
            print(prev, start, prev[target]);
        }
        System.out.println(target);
    }

    public static void main(String[] args) {
        GraphWithDFS graph = new GraphWithDFS(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.dfs(0, 6);
        System.out.println("---");
        graph.dfsByStack(0, 6);

    }

}
