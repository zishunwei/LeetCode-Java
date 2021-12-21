package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 二叉树遍历（基于栈的非递归实现）
 */
public class TraversalByStack {
    // 栈实现前序遍历较简单，由于每次先输出根节点，再输出左节点随后是右节点。
    //1、若栈非空输出根节点，并出栈
    //2、将右节点压栈（如果存在）
    //3、将左节点压栈（如果存在）
    //4、重复第1步直到栈空
    //注意：之所以先压右节点是考虑了栈的特性，这样在迭代过程中可以先拿到左节点处理。（栈的先入后出）
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }


    // 栈的中序遍历需要套两层循环，由于需要先输出左节点，因此必须向下查找直到左节点为空才能输出。处理逻辑如下：
    //1、如果栈顶元素非空且左节点存在，将其入栈，重复该过程。若不存在则进入第2步
    //2、若栈非空，输出栈顶元素并出栈。判断刚出栈的元素的右节点是否存在，不存在重复第2步，存在则将右节点入栈，跳至第1步
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);

        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            // 第1步：如果栈顶元素非空且左节点存在，将其入栈
            while (stack.getFirst().left != null) {
                stack.addFirst(stack.getFirst().left);
            }

            // 第二步：若栈非空，输出栈顶元素并出栈。
            while (!stack.isEmpty()) {
                TreeNode cur = stack.removeFirst();
                res.add(cur.val);
                // 判断刚出栈的元素的右节点是否存在，不存在重复第2步
                if (cur.right != null) {
                    // 存在则将右节点入栈，跳至第1步 （break退出第二步子循环）
                    stack.addFirst(cur.right);
                    break;
                }
            }
        }
        return res;
    }

    // 后续遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        List<Integer> res = new ArrayList<>();

        TreeNode lastPop = null;

        // 第1步：如果栈顶元素非空且左节点存在，将其入栈
        while (!stack.isEmpty()) {
            while (stack.getFirst().left != null) {
                stack.addFirst(stack.getFirst().left);
            }

            // 第二步：若栈非空，输出栈顶元素并出栈。
            // 与中续遍历不同点：需要先于当前节点输出其右节点
            while (!stack.isEmpty()) {
                TreeNode cur = stack.getFirst();
                // 当前节点没有右节点或者其右节点已经访问完情况下-访问当前节点
                if (cur.right == null || lastPop == cur.right) {
                    res.add(cur.val);
                    lastPop = cur;
                    stack.removeFirst();
                } else {
//                else if (lastPop != cur.right) {
                    // 存在右节点且右节点没有被访问过-则将右节点入栈，跳至第1步 （break退出第二步子循环）
                    stack.addFirst(cur.right);
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4, new TreeNode(99), new TreeNode(100)));
        TreeNode right = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);

        TraversalByStack traversalByStack = new TraversalByStack();
        System.out.println(traversalByStack.postorderTraversal(root));
    }

}