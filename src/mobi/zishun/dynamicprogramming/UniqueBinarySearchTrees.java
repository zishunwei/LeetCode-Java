package mobi.zishun.dynamicprogramming;

/*
 * 96. 不同的二叉搜索树
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
示例 1：
输入：n = 3
输出：5
示例 2：
输入：n = 1
输出：1
提示：
1 <= n <= 19
* https://leetcode-cn.com/problems/unique-binary-search-trees/
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 以j为中心节点，计算能构成不同bst的个数
            for (int j = 1; j <= i; j++) {
                // 给定一个有序序列 1⋯n，为了构建出一棵二叉搜索树，
                // 我们可以遍历每个数字 j，将该数字作为树根，将 1⋯(j−1) 序列作为左子树，
                // 将 n(j+1)⋯n 序列作为右子树。接着我们可以按照同样的方式递归构建左子树和右子树。
                // dp[j-1]为 1⋯(j−1) 序列（j-1个节点）的bst数量，dp[i-j]为 n(j+1)⋯n 序列（i-j个节点）的bst数量
                // 组合起来即相乘，结果代表以j为中心节点，所能构成不同bst的个数
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
