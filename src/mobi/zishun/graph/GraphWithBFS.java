package mobi.zishun.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 无向图
 * 邻接表存储
 * 实现BFS搜索
 */
public class GraphWithBFS {
    private final int vertexNum; //顶点的个数
    private final LinkedList<Integer>[] adjList; //邻接表(Adjacency List)

    public GraphWithBFS(int vertexNum) {
        this.vertexNum = vertexNum;
        adjList = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边
     *
     * @param s 顶点
     * @param t 顶点
     */
    public void addEdge(int s, int t) {
        adjList[s].add(t);
        adjList[t].add(s);
    }

    /**
     * bfs（广度优先搜索）
     *
     * @param start  起点
     * @param target 目标节点
     */
    public void bfs(int start, int target) {
        if (start == target) {
            return;
        }
        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问。
        boolean[] visited = new boolean[vertexNum];
        visited[start] = true;
        // queue是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        // prev用来记录搜索路径。
        int[] prev = new int[vertexNum];
        for (int i = 0; i < vertexNum; ++i) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            // 当前节点
            int curVertex = queue.poll();
            for (int i = 0; i < adjList[curVertex].size(); i++) {
                // 当前节点的邻接节点
                int adjVertex = adjList[curVertex].get(i);
                if (!visited[adjVertex]) {
                    prev[adjVertex] = curVertex;
                    if (adjVertex == target) {
                        print(prev, start, target);
                        return;
                    }
                    visited[adjVertex] = true;
                    queue.add(adjVertex);
                }
            }
        }
    }

    private void print(int[] prev, int start, int target) {
        // 递归打印 s->t 的路径
        if (prev[target] != -1 && target != start) {
            print(prev, start, prev[target]);
        }
        System.out.println(target + " ");
        // 循环打印 s->t 的路径（倒序+无起点）
//        while (true){
//            if (prev[target] != -1 && target != start) {
//                System.out.println(target + " ");
//            } else {
//                break;
//            }
//            target = prev[target];
//        }
    }

    public static void main(String[] args) {
        GraphWithBFS graph = new GraphWithBFS(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.bfs(0, 6);
    }


}
