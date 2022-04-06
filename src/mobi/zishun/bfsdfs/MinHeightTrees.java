package mobi.zishun.bfsdfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 310. 最小高度树
树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。
* 给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），
* 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
* 在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。

示例 1：
输入：n = 4, edges = [[1,0],[1,2],[1,3]]
输出：[1]
解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
提示：
1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
所有 (ai, bi) 互不相同
给定的输入 保证 是一棵树，并且 不会有重复的边
* https://leetcode-cn.com/problems/minimum-height-trees/
 */
public class MinHeightTrees {
    // 首先，我们看了样例，发现这个树并不是二叉树，是多叉树。
    // 然后，我们可能想到的解法是：根据题目的意思，就挨个节点遍历bfs，统计下每个节点的高度，然后用map存储起来，后面查询这个高度的集合里最小的就可以了。
    // 但是这样会超时的。
    // 于是我们看图（题目介绍里面的图）分析一下，发现，越是靠里面的节点越有可能是最小高度树。
    // 所以，我们可以这样想，我们可以倒着来。
    // 我们从边缘开始，先找到所有出度为1的节点，然后把所有出度为1的节点进队列，然后不断地bfs，最后找到的就是两边同时向中间靠近的节点，那么这个中间节点就相当于把整个距离二分了，那么它当然就是到两边距离最小的点啦，也就是到其他叶子节点最近的节点了。
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            List<Integer> list1 = hashMap.getOrDefault(edge[0], new ArrayList<>());
            list1.add(edge[1]);
            hashMap.put(edge[0], list1);
            List<Integer> list2 = hashMap.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            hashMap.put(edge[1], list2);
        }

        /*建立队列*/
        Queue<Integer> queue = new ArrayDeque<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        // bfs
        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = hashMap.get(cur);
                /*这里就是经典的bfs了，把当前节点的相邻接点都拿出来，
                 * 把它们的出度都减1，因为当前节点已经不存在了，所以，
                 * 它的相邻节点们就有可能变成叶子节点*/
                for (int neighber : neighbors) {
                    degree[neighber]--;
                    if (degree[neighber] == 1) { /*如果是叶子节点我们就入队*/
                        queue.offer(neighber);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinHeightTrees m = new MinHeightTrees();
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        int n = 6;
        System.out.println(m.findMinHeightTrees(n, edges)); // [3,4]
    }

}
