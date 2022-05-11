package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 297. 二叉树的序列化与反序列化
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
* 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

示例 1：
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
提示：
树中结点数在范围 [0, 10^4] 内
-1000 <= Node.val <= 1000
* https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeDeserializeBinaryTree {
    private static final String NULL = "null";

    private static final String SEPARATOR = ",";

    // DFS - 前序遍历
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        rserialize(root, result);
        return result.toString();
    }

    private void rserialize(TreeNode root, StringBuilder result) {
        if (root == null) {
            result.append(NULL).append(SEPARATOR);
            return;
        }
        result.append(root.val).append(SEPARATOR);
        rserialize(root.left, result);
        rserialize(root.right, result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null){
            return null;
        }
        // 根据","把原先的序列分割开来得到先序遍历的元素列表，然后从左向右遍历这个序列：
        String[] nodes = data.split(SEPARATOR);
        Queue<String> nodeList = new LinkedList<>(Arrays.asList(nodes));
        return rdeserialize(nodeList);
    }

    // 如果当前的元素为 None，则当前为空树
    // 否则先解析这棵树的左子树，再解析它的右子树
    private TreeNode rdeserialize(Queue<String> nodeList) {
        String fisrtNode = nodeList.remove();
        if (fisrtNode.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(fisrtNode));
        root.left = rdeserialize(nodeList);
        root.right = rdeserialize(nodeList);
        return root;
    }


    /*
     * BFS - 层序遍历版本
     */
    // Encodes a tree to a single string.
    public static String serializeV2(TreeNode root) {
        if (root == null) {
            return NULL;
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                result.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                result.append(NULL);
            }
            result.append(SEPARATOR);
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserializeV2(String data) {
        if (NULL.equals(data)) {
            return null;
        }
        String[] nodes = data.split(SEPARATOR);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1; // 初始指向list第二项
        while (index < nodes.length) {
            TreeNode cur = queue.poll();

            String leftVal = nodes[index];
            String rightVal = nodes[index + 1];

            if (!leftVal.equals(NULL)) {
                TreeNode left = new TreeNode(Integer.parseInt(leftVal));
                queue.offer(left);
                cur.left = left;
            }
            if (!rightVal.equals(NULL)) {
                TreeNode right = new TreeNode(Integer.parseInt(rightVal));
                queue.offer(right);
                cur.right = right;
            }
            index += 2;
        }
        return root;
    }


    public static void main(String[] args) {
        SerializeDeserializeBinaryTree m = new SerializeDeserializeBinaryTree();
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(m.serialize(root));
        TreeNode result = m.deserialize(m.serialize(root));
        System.out.println(m.serialize(result));
    }
}
