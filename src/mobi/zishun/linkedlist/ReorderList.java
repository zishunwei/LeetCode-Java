package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
 * 143. 重排链表
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
L0 → L1 → … → Ln - 1 → Ln
请将其重新排列后变为：
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1：
输入：head = [1,2,3,4]
输出：[1,4,2,3]
示例 2：
输入：head = [1,2,3,4,5]
输出：[1,5,2,4,3]

提示：
链表的长度范围为 [1, 5 * 10^4]
1 <= node.val <= 1000
* https://leetcode.cn/problems/reorder-list/
 */
public class ReorderList {
    // ArrayList存储节点, 空间复杂度O(n)
    public void reorderList(ListNode head) {
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head);
            head = head.next;
        }
        head = new ListNode(-1);
        ListNode prev = head;
        int n = nodeList.size();
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            prev.next = nodeList.get(i);
            prev = prev.next;
            i++;
            prev.next = nodeList.get(j);
            prev = prev.next;
            j--;
        }
        prev.next = null;
        head = head.next;
//        while (head != null){
//            System.out.println(head.val);
//            head = head.next;
//        }
    }

    public static void main(String[] args) {
        ReorderList m = new ReorderList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        m.reorderList(head);
    }

}
