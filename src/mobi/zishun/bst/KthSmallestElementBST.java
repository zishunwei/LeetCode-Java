package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

/*
 * 230. 二叉搜索树中第K小的元素
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

示例 2：
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3
提示：
树中的节点数为 n 。
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4
进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
* https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementBST {
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    int counter = 0;
    int res = -1;

    private void dfs(TreeNode root, int k) {
        if (root == null || res != -1) {
            return;
        }
        dfs(root.left, k);
        counter++;
        if (counter == k) {
            res = root.val;
        }
        dfs(root.right, k);
    }

    public static void main(String[] args){
        KthSmallestElementBST m = new KthSmallestElementBST();
        TreeNode root = new TreeNode(2,new TreeNode(1), new TreeNode(3));
        System.out.println(m.kthSmallest(root,2));
    }

}
