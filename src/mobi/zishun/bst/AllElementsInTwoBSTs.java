package mobi.zishun.bst;

import mobi.zishun.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * 1305. 两棵二叉搜索树中的所有元素
给你 root1 和 root2 这两棵二叉搜索树。
* 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
示例 1：
输入：root1 = [2,1,4], root2 = [1,0,3]
输出：[0,1,1,2,3,4]
示例 2：
输入：root1 = [1,null,8], root2 = [8,1]
输出：[1,1,8,8]
提示：
每棵树的节点数在 [0, 5000] 范围内
-10^5 <= Node.val <= 10^5
* https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 */
public class AllElementsInTwoBSTs {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        traverse(root1, list1);
        traverse(root2, list2);
        return merge(list1, list2);
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        int m = list1.size();
        int n = list2.size();
        List<Integer> result = new ArrayList<>(m + n);
        int i = 0;
        int j = 0;
        while (i < m || j < n) {
            if (i == m){
                result.add(list2.get(j));
                j++;
            } else if (j == n){
                result.add(list1.get(i));
                i++;
            } else if (list1.get(i) <= list2.get(j)){
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }
        return result;
    }


}
