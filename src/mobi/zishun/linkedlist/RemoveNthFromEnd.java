package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
进阶：你能尝试使用一趟扫描实现吗？
* https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {
    //方法一：计算链表长度
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        int size = 0;
        while (first != null) {
            size++;
            first = first.next;
        }
        int m = size - n + 1;

        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        for (int i = 1; i < m; i++) {
            prev.next = head;
            head = head.next;
            prev = prev.next;
        }
        prev.next = head.next;
//        while (head != null) {
//            count++;
//            if (count == m) {
//                prev.next = head.next;
//                if (head.next != null){
//                    head = head.next.next;
//                } else {
//                    head = null;
//                }
//            } else {
//                prev.next = head;
//                head = head.next;
//            }
//            prev = prev.next;
//        }
        return preHead.next;
    }

    // 方法二：栈
    public ListNode removeNthFromEndByStack(ListNode head, int n) {
        ListNode preHead = new ListNode(-1, head);
        ListNode cur = preHead;

        Deque<ListNode> nodeStack = new LinkedList<ListNode>();
        while (cur != null) {
            nodeStack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            nodeStack.pop();
        }
        ListNode preHeadOfNth = nodeStack.peek();
        preHeadOfNth.next = preHeadOfNth.next.next;
        return preHead.next;
    }

    // 方法三：双指针
    public ListNode removeNthFromEndByTwoPointers(ListNode head, int n) {
        // 哨兵节点解决head.length == n的情况
        ListNode preHead = new ListNode(-1, head);
        ListNode prev = preHead;

        ListNode second = head;
        for (int i = 0; i < n; i++) {
            second = second.next;
        }
        while (second != null) {
            second = second.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return preHead.next;


    }

    // 双指针V2 - 无哨兵节点
    public ListNode removeNthFromEndByTwoPointersV2(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 如果fast已为空，说明是删除第一个节点，直接return
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
