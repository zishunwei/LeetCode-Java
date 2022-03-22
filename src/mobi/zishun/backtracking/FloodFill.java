package mobi.zishun.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 733. 图像渲染
有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
给你一个坐标(sr, sc)表示图像渲染开始的像素值（行 ，列）和一个新的颜色值newColor，让你重新上色这幅图像。
* 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
* 接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，
* 重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
最后返回经过上色渲染后的图像。
链接：https://leetcode-cn.com/problems/flood-fill
 */
public class FloodFill {

    // BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }
        int n = image.length;
        int m = image[0].length;
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }

    private boolean[][] visited;

    // 深度优先搜索（回溯思想）
    public int[][] floodFillV2(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        int length1 = image.length;
        int length2 = image[0].length;
        visited = new boolean[length1][length2];
        dfs(image, sr, sc, newColor, originalColor, length1, length2);
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int newColor, int originalColor, int length1, int length2) {
        if (visited[sr][sc]) {
            return;
        }
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        if ((sr - 1) >= 0 && image[sr - 1][sc] == originalColor) {
            dfs(image, sr - 1, sc, newColor, originalColor, length1, length2);
        }
        if ((sr + 1) < length1 && image[sr + 1][sc] == originalColor) {
            dfs(image, sr + 1, sc, newColor, originalColor, length1, length2);
        }
        if ((sc - 1) >= 0 && image[sr][sc - 1] == originalColor) {
            dfs(image, sr, sc - 1, newColor, originalColor, length1, length2);
        }
        if ((sc + 1) < length2 && image[sr][sc + 1] == originalColor) {
            dfs(image, sr, sc + 1, newColor, originalColor, length1, length2);
        }
    }

    public static void main(String[] args) {
        FloodFill m = new FloodFill();
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        int[][] res = m.floodFill(image, 1, 1, 1);
        System.out.println(Arrays.deepToString(res));
    }
}
