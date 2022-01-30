package mobi.zishun.javabase;

import mobi.zishun.model.ListNode;
import mobi.zishun.model.TreeNode;

public class TypeTest {
    private void modify(int num) {
        num = 2;
    }

    private void modify(String str) {
        str = "b";
    }

    private void modify(ListNode node) {
        ListNode prev = node;
        prev.next = new ListNode(2);
        prev = prev.next;
        prev.next = new ListNode(3);
    }

    private void modify(TreeNode treeNode) {
        treeNode = new TreeNode(2);
    }

    public static void main(String[] args) {
        TypeTest typeTest = new TypeTest();

        int num = 1;
        typeTest.modify(num);
        System.out.println(num);

        String str = "a";
        typeTest.modify(str);
        System.out.println(str);

        TreeNode treeNode = new TreeNode(1);
        typeTest.modify(treeNode);
        System.out.println(treeNode.val);

        ListNode node = new ListNode(1);
        typeTest.modify(node);
        System.out.println(node.next.next.val);
    }

}
