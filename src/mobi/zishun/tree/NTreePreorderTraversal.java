package mobi.zishun.tree;

import mobi.zishun.model.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 589. N 叉树的前序遍历
给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
输入：root = [1,null,3,2,4,null,5,6]
输出：[1,3,5,6,2,4]
 */
public class NTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node c : root.children) {
            dfs(c, res);
        }
    }

    public List<Integer> preorderV2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node cur = stack.removeLast();
            res.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.addLast(cur.children.get(i));
            }
        }
        return res;
    }
}
