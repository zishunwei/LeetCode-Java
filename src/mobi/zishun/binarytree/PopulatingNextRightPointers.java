package mobi.zishun.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 116. 填充每个节点的下一个右侧节点指针
给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。
输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点
序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
* https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PopulatingNextRightPointers {
    // 层序遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> nodeList = new LinkedList<>();
        nodeList.offer(root);
        while (!nodeList.isEmpty()) {
            int curLayerSize = nodeList.size();
            for (int i = 0; i < curLayerSize; i++) {
                Node cur = nodeList.poll();
                if (cur.left != null) {
                    nodeList.add(cur.left);
                }
                if (cur.right != null) {
                    nodeList.add(cur.right);
                }
                if (i == curLayerSize - 1) {
                    cur.next = null;
                } else {
                    cur.next = nodeList.peek();
                }
            }
        }
        return root;
    }

    /*
     * 完美二叉树
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     */
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static void main(String[] args) {
        PopulatingNextRightPointers m = new PopulatingNextRightPointers();
        Node root = new Node(1, new Node(2), new Node(3), null);
        Node res = m.connect(root);
        System.out.println(res);
    }
}