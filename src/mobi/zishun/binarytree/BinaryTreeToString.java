package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

/*
 * 606. 根据二叉树创建字符串
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
输入: 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \
      4
输出: "1(2()(4))(3)"
解释: 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
* https://leetcode-cn.com/problems/construct-string-from-binary-tree/
 */
public class BinaryTreeToString {
    public String tree2str(TreeNode root) {
        String res = String.valueOf(root.val);
        if (root.left != null && root.right != null) {
            res += "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
        } else if (root.left != null) {
            res += "(" + tree2str(root.left) + ")";
        } else if (root.right != null) {
            res += "()" + "(" + tree2str(root.right) + ")";
        }
        return res;
    }
}
