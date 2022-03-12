package mobi.zishun.binarytree;

import mobi.zishun.model.Node;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 590. N 叉树的后序遍历
给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
输入：root = [1,null,3,2,4,null,5,6]
输出：[5,6,3,2,4,1]
 */
public class NTreePostorderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node c : root.children) {
            dfs(c, res);
        }
        res.add(root.val);
    }

    // stack
    public List<Integer> postorderV2(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.peekLast();
            List<Node> children = node.children;
            int size = children.size();
            // 如果当前节点为叶子节点或者当前节点的子节点已经遍历过
            if (size == 0 || visited.contains(node)) {
                stack.removeLast();
                res.add(node.val);
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    stack.addLast(children.get(i));
                }
                visited.add(node);
            }
        }
        return res;
    }

}
