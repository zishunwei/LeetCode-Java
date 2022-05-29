package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 199. 二叉树的右视图
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例 1:
输入: [1,2,3,null,5,null,4]
输出: [1,3,4]
示例 2:
输入: [1,null,3]
输出: [1,3]
示例 3:
输入: []
输出: []
提示:
二叉树的节点个数的范围是 [0,100]
-100 <= Node.val <= 100
* https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {
    // 层序遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
                if (i == size -1){
                    result.add(cur.val);
                }
            }
        }
        return result;
    }

}
