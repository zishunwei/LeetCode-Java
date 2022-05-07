package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * 501. 二叉搜索树中的众数
给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
如果树中有不止一个众数，可以按 任意顺序 返回。
假定 BST 满足如下定义：
结点左子树中所含节点的值 小于等于 当前节点的值
结点右子树中所含节点的值 大于等于 当前节点的值
左子树和右子树都是二叉搜索树
示例 1：
输入：root = [1,null,2,2]
输出：[2]
示例 2：
输入：root = [0]
输出：[0]
提示：
树中节点的数目在范围 [1, 10^4] 内
-10^5 <= Node.val <= 10^5
进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
* https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 */
public class FindModeBST {
    public int[] findMode(TreeNode root) {
        traverse(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    List<Integer> resList = new ArrayList<>();
    int base = 100001; // 基准值，初始赋值避免和根节点值相同
    int curCount = 0;
    int maxCount = 0;

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        int curVal = root.val;
        if (curVal == base) { // 当前值等于基准值
            curCount++;
        } else {
            // 重置当前计数器，更新基准值
            curCount = 1;
            base = curVal;
        }
        if (curCount == maxCount) { // 当前值出现次数 等于 众数的出现次数
            // 添加到结果数组
            resList.add(base);
        } else if (curCount > maxCount) { //当前值出现次数 已经大于 众数的出现次数
            // 说明有出现次数更多的值，之前统计的结果数组应该被重置
            maxCount = curCount;
            resList = new ArrayList<>();
            resList.add(base);
        }
        traverse(root.right);
    }


}
