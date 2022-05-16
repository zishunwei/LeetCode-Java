package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 面试题 04.06. 后继者 (同285. 二叉搜索树中的中序后继）
设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
如果指定节点没有对应的“下一个”节点，则返回null。
示例 1:
输入: root = [2,1,3], p = 1
  2
 / \
1   3
输出: 2
示例 2:
输入: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /
1

输出: null
* https://leetcode.cn/problems/successor-lcci/
 */
public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        int target = p.val;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.addFirst(root);
        boolean flag = false;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            while (cur.left != null) {
                stack.addFirst(cur.left);
                cur = cur.left;
            }
            while (!stack.isEmpty()) {
                cur = stack.removeFirst();
                if (flag) {
                    return cur;
                }
                if (cur.val == target) {
                    flag = true;
                }
                if (cur.right != null) {
                    stack.addFirst(cur.right);
                    break;
                }
            }
        }
        return null;
    }
}
