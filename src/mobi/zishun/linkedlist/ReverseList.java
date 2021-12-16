package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
反转链表
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //保存cur节点之后的链路
            ListNode next = cur.next;
            //反转pre、cur
            cur.next = pre;
            //移动pre到已反转的cur
            pre = cur;
            //移动cur
            cur = next;
        }
        return pre;
    }

    public ListNode reverseListByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 假设head.next及之后部分已反转
        ListNode last = reverseListByRecursion(head.next);
        // 反转head与head.next节点
        head.next.next = head;
        head.next = null;
        return last;
    }

}
