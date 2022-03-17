package mobi.zishun.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 542. 01 矩阵
给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
两个相邻元素间的距离为 1 。
输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]
https://leetcode-cn.com/problems/01-matrix/
 */
public class UpdateMatrix {

    // BFS
    // 最近距离 - bfs （最远距离 - dfs）
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // 是多个起始点的 BFS，需要先遍历一遍矩阵，把所有为 0 的坐标先放进队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    // 0所在位置全部设为visted
                    visited[i][j] = true;
                }
            }
        }
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // 从每个0出发
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            for (int[] direction : directions) {
                int newi = i + direction[0];
                int newj = j + direction[1];
                if (newi >= 0 && newi < m && newj >= 0 && newj < n && !visited[newi][newj]) {
                    // 满足条件的新一轮遍历等于上次遍历的结果+1（多一圈）
                    res[newi][newj] = res[i][j] + 1;
                    visited[newi][newj] = true;
                    // 加入队列，进行下一轮遍历
                    queue.offer(new int[]{newi, newj});
                }
            }
        }
        return res;
    }


    // 初版bfs - 从每个1出发bfs - 超出时间限制
    public int[][] updateMatrixV2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    int minDist = 0;
                    loopOne:
                    while (true) {
                        int curQueueSize = queue.size();
                        for (int k = 0; k < curQueueSize; k++) {
                            int[] cur = queue.remove();
                            if (cur[0] > 0) {
                                queue.offer(new int[]{cur[0] - 1, cur[1]});
                            }
                            if (cur[0] < m - 1) {
                                queue.offer(new int[]{cur[0] + 1, cur[1]});
                            }
                            if (cur[1] > 0) {
                                queue.offer(new int[]{cur[0], cur[1] - 1});
                            }
                            if (cur[1] < n - 1) {
                                queue.offer(new int[]{cur[0], cur[1] + 1});
                            }
                        }
                        minDist++;
                        for (int[] pair : queue) {
                            if (mat[pair[0]][pair[1]] == 0) {
                                break loopOne;
                            }
                        }
                    }
                    res[i][j] = minDist;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        UpdateMatrix m = new UpdateMatrix();
        int[][] mat = {{0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 1, 1}, {1, 0, 0, 0, 1}};
        System.out.println(Arrays.deepToString(m.updateMatrix(mat))); //[[0,1,0,1,2],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]
    }

}
