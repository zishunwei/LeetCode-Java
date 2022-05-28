package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 1448. 统计二叉树中好节点的数目
给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。

* 示例 1：
输入：root = [3,1,4,3,null,1,5]
输出：4
解释：图中蓝色节点为好节点。
根节点 (3) 永远是个好节点。
节点 4 -> (3,4) 是路径中的最大值。
节点 5 -> (3,4,5) 是路径中的最大值。
节点 3 -> (3,1,3) 是路径中的最大值。
* 示例 3：
输入：root = [1]
输出：1
解释：根节点是好节点。
提示：
二叉树中节点数目范围是 [1, 10^5] 。
每个节点权值的范围是 [-10^4, 10^4] 。
通过次数14,193提交次数19,767
* https://leetcode.cn/problems/count-good-nodes-in-binary-tree/
 */
public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode root, int max) {
        int res = 0;
        if (root.val >= max) {
            res++;
            max = root.val;
        }
        if (root.right != null) {
            res += dfs(root.right, max);
        }
        if (root.left != null) {
            res += dfs(root.left, max);
        }
        return res;
    }

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree m = new CountGoodNodesInBinaryTree();
        SerializeDeserializeBinaryTree deserializer = new SerializeDeserializeBinaryTree();
        TreeNode root = deserializer.deserialize("3,1,4,3,null,1,5");
        System.out.println(m.goodNodes(root));
    }

}
