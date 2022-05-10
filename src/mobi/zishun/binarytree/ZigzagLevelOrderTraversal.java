package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 103. 二叉树的锯齿形层序遍历
给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
* （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]
示例 3：
输入：root = []
输出：[]
提示：
树中节点数目在范围 [0, 2000] 内
-100 <= Node.val <= 100
* https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 */
public class ZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
//            List<Integer> oneLayer = new ArrayList<>();
            // 可以用双端队列变化方向，也可以添加完成后反转列表
            Deque<Integer> oneLayer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (flag){
                    oneLayer.offerLast(cur.val);
                } else {
                    oneLayer.offerFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            flag = !flag;
//            if (flag) {
//                Collections.reverse(oneLayer);
//            }
//            flag = !flag;
            result.add(new LinkedList<Integer>(oneLayer));
        }
        return result;
    }

}
