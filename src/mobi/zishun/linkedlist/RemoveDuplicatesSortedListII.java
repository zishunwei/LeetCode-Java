package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 82. 删除排序链表中的重复元素 II
给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。
* 返回 已排序的链表 。

示例 1：
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
示例 2：
输入：head = [1,1,1,2,3]
输出：[2,3]
提示：
链表中节点数目在范围 [0, 300] 内
-100 <= Node.val <= 100
题目数据保证链表已经按升序 排列
* https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesSortedListII {
    // 递归
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) { // 头节点的值不等于下一个节点的值
            // 当前的 head节点必须保留；对 head.next进行递归
            head.next = deleteDuplicates(head.next);
        } else { // 头节点的值等于下一个节点的值
            ListNode cur = head.next; // 和当前值相同的节点
            // 找到下一个和当前值不同的节点
            while (cur != null && head.val == cur.val) {
                cur = cur.next;
            }
            // 删除之前的所有值 = 接着递归剩下的节点（赋值给head）
            head = deleteDuplicates(cur);
        }
        return head;
    }

    // 一次遍历
    public ListNode deleteDuplicatesV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preNode = new ListNode(-1);
        preNode.next = head;
        ListNode pre = preNode;
        ListNode cur = head;
        while (cur != null) {
            // 跳过当前的重复节点，使得cur指向当前重复元素的最后一个位置
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) { // pre和cur之间没有重复节点，pre后移
                pre = pre.next;
            } else {
                // pre->next指向cur的下一个位置（相当于跳过了当前的重复元素）
                // 但是pre不移动，仍然指向已经遍历的链表结尾
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return preNode.next;
    }
}