package mobi.zishun.dynamicprogramming;

import mobi.zishun.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
 * 337. 打家劫舍 III
小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
*  如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。

示例 1:
输入: root = [3,2,3,null,3,null,1]
输出: 7
解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
示例 2:
输入: root = [3,4,5,1,3,null,1]
输出: 9
解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
提示：
树的节点数在 [1, 10^4] 范围内
0 <= Node.val <= 10^4
* https://leetcode-cn.com/problems/house-robber-iii/
 */
public class HouseRobberIII {
    // 树形dp
    public int rob(TreeNode root) {
        int[] res = robDP(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robDP(TreeNode root) {
        int[] res = new int[2];
        if (root == null){
            return res;
        }
        // 左右子树的结果
        int[] leftRes = robDP(root.left);
        int[] rightRes = robDP(root.right);

        res[0] = root.val + leftRes[1] + rightRes[1]; // 选择偷当前节点的（当前节点 + 左右子树选择不偷根节点的子结果）
        res[1] = Math.max(leftRes[0], leftRes[1]) + Math.max(rightRes[0], rightRes[1]); // 选择不偷当前节点的 (左右子树的和的最大值相加)
        return res;
    }

    // 记忆化回溯
    public int robV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        // 根节点 + 孙子树
        int sum1 = root.val;
        if (root.left != null) {
            sum1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            sum1 += rob(root.right.left) + rob(root.right.right);
        }
        // 左右子树
        int sum2 = rob(root.left) + rob(root.right);

        int res = Math.max(sum1, sum2);
        cache.put(root, res);
        return res;
    }

    private final Map<TreeNode, Integer> cache = new HashMap<>();


}
