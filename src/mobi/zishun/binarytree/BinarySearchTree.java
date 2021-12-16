package mobi.zishun.binarytree;

import mobi.zishun.model.TreeNode;

public class BinarySearchTree {
    private TreeNode tree;

    public BinarySearchTree() {
    }

    public BinarySearchTree(TreeNode tree) {
        this.tree = tree;
    }

    public void insert(int value) {
        if (tree == null) {
            tree = new TreeNode(value);
        }
        TreeNode cur = tree;
        while (true) {
            if (value == cur.val) {
                return;
            } else if (value < cur.val) {
                if (cur.left == null) {
                    cur.left = new TreeNode(value);
                    return;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(value);
                    return;
                } else {
                    cur = cur.right;
                }
            }
        }
    }

    public void delete(int value) {
        if (tree == null) {
            return;
        }
        TreeNode cur = tree;
        TreeNode curParent = null;
        // 查找对应值节点位置
        while (cur != null && cur.val != value) {
            curParent = cur;
            if (value < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur == null) {
            return;
        }

        // 删除节点有两个子节点
        // 1。将删除节点先与其右子节点的最小值交换
        // 2。删除右子节点的最小值节点（按子节点只有一个或者没有的情况）
        if (cur.left != null && cur.right != null) {
            TreeNode minNode = cur.right;
            TreeNode minNodeParent = cur;
            while (minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }
            cur.val = minNode.val;
            cur = minNode; // 下面就变成了删除minNode
            curParent = minNodeParent;
        }

        // 保存删除节点子节点（子节点只有一个或者没有）
        TreeNode child;
        if (cur.left != null) {
            child = cur.left;
        } else if (cur.right != null) {
            child = cur.right;
        } else {
            child = null;
        }

        if (curParent == null) {
            tree = child;
        } else if (curParent.left == cur) {
            curParent.left = child;
        } else {
            curParent.right = child;
        }
    }

    // 循环
    public TreeNode find(int value) {
        TreeNode cur = tree;
        if (cur.val == value) {
            return cur;
        }
        while (cur != null) {
            if (cur.val == value) {
                return cur;
            } else if (value < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    // 递归
    public TreeNode findV2(int value) {
        TreeNode cur = tree;
        return findRecursion(value, cur);
    }

    private TreeNode findRecursion(int value, TreeNode cur) {
        if (cur == null) {
            return null;
        }
        if (cur.val == value) {
            return cur;
        } else if (value < cur.val) {
            cur = cur.left;
            return findRecursion(value, cur);
        } else {
            cur = cur.right;
            return findRecursion(value, cur);
        }

    }

    public static void main(String[] args) {

        TreeNode left = new TreeNode(17, new TreeNode(13), new TreeNode(21));
        TreeNode right = new TreeNode(50, new TreeNode(34), new TreeNode(58));
        TreeNode root = new TreeNode(33, left, right);

        BinarySearchTree binarySearchTree = new BinarySearchTree(root);

        binarySearchTree.insert(20);
        binarySearchTree.insert(15);
        binarySearchTree.insert(57);
        binarySearchTree.insert(16);
        binarySearchTree.insert(18);
        binarySearchTree.insert(19);


        TreeNode res = binarySearchTree.find(15);

        binarySearchTree.delete(17);

        if (res != null) {
            System.out.println(res.val);
        } else {
            System.out.println("nothing");
        }
    }
}
