package mobi.zishun.tree;

import mobi.zishun.model.QuadTreeNode;

/*
 * 427. 建立四叉树
给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
你需要返回能表示矩阵的 四叉树 的根结点。
注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
我们可以按以下步骤为二维区域构建四叉树：
如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
使用适当的子网格递归每个子节点。
* 四叉树格式：
输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
示例 1：
输入：grid = [[0,1],[1,0]]
输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
提示：
n == grid.length == grid[i].length
n == 2^x 其中 0 <= x <= 6
* https://leetcode-cn.com/problems/construct-quad-tree/
 */
public class ConstructQuadTree {
    public QuadTreeNode construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    // 以 (a,b) 为左上角，(c,d) 为右下角边界
    private QuadTreeNode dfs(int[][] grid, int a, int b, int c, int d) {
        int val = grid[a][b];
        boolean isLeaf = true;
        // 暴力法遍历当前网格的值是否相同
        loop1:
        for (int i = a; i < c; i++) {
            for (int j = b; j < d; j++) {
                if (grid[i][j] != val) {
                    isLeaf = false;
                    break loop1;
                }
            }
        }
        // 当前节点为子节点
        if (isLeaf) {
            return val == 1 ? new QuadTreeNode(true, true) : new QuadTreeNode(false, true);
        }
        // 当前节点非子节点，继续向下递归
        return new QuadTreeNode(true, false,
                dfs(grid, a, b, (a + c) / 2, (b + d) / 2), //topLeft, c,d会变
                dfs(grid, a, (b + d) / 2, (a + c) / 2, d), //topRight，b,c会变
                dfs(grid, (a + c) / 2, b, c, (b + d) / 2), //bottomLeft，a,d会变
                dfs(grid, (a + c) / 2, (b + d) / 2, c, d)); //bottomRight，a,b会变
    }

}

