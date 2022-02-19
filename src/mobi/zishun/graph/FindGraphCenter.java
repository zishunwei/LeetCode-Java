package mobi.zishun.graph;

/*
 * 1791. 找出星型图的中心节点
有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
给你一个二维整数数组 edges ，其中edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回edges 所表示星型图的中心节点。
输入：edges = [[1,2],[2,3],[4,2]]
输出：2
解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
链接：https://leetcode-cn.com/problems/find-center-of-star-graph
 */
public class FindGraphCenter {
    public int findCenter(int[][] edges) {
        int res = edges[0][0];
        if (edges[1][0] == res || edges[1][1] == res) {
            return res;
        }
        return edges[0][1];
    }

    public static void main(String[] args) {
        FindGraphCenter m = new FindGraphCenter();
        int[][] edges = {{1, 2}, {2, 3}, {4, 2}};
        System.out.println(m.findCenter(edges));
    }
}
