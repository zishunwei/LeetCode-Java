package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 61. 旋转链表
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

示例 1：
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
示例 2：
输入：head = [0,1,2], k = 4
输出：[2,0,1]
提示：
链表中节点的数目在范围 [0, 500] 内
-100 <= Node.val <= 100
0 <= k <= 2 * 10^9
* https://leetcode.cn/problems/rotate-list/
 */
public class RotateList {
    // 优化版 - 先成环，再断开
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 找到链表长度 + head成环
        int length = 1;
        ListNode cur = head;
        while (cur.next != null) {
            length++;
            cur = cur.next;
        }
        cur.next = head;

        // 计算出新链表头节点在老链表中的位置
        int start = length - k % length;
        // 在start位置前断开
        cur = head;
        for (int i = 0; i < start - 1; i++) {
            cur = cur.next;
        }
        // 将start位置的节点先保存下来作为结果
        ListNode res = cur.next;
        // 断开环
        cur.next = null;
        return res;
    }

    // 初版 - 单独存储两截链表，再拼起来，代码稍显冗余
    public ListNode rotateRightV2(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 找到链表长度
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        // 计算出新链表头节点在老链表中的位置
        int start = length - k % length;
        // 保存原链表 1到start的节点，作为新链表的尾部
        // 使用哨兵节点简化
        ListNode tail = new ListNode(-1);
        ListNode prev = tail;
        int i;
        for (i = 0; i < start; i++) {
            prev.next = head;
            head = head.next;
            prev = prev.next;
        }
        prev.next = null;

        // 保存原链表 start到length 的节点，作为新链表的头部
        ListNode newHead = new ListNode(-1);
        prev = newHead;
        for (; i < length; i++) {
            prev.next = head;
            head = head.next;
            prev = prev.next;
        }
        // 头尾相连
        prev.next = tail.next;
        return newHead.next;
    }
}
