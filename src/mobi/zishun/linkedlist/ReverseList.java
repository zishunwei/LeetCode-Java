package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 206. 反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
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
        ListNode reversedNext = reverseListByRecursion(head.next);
        // head == 1 -> 2 <- 3 <- 4 <- 5
        // 反转head与head.next节点
        head.next.next = head;
        head.next = null;
        return reversedNext;
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3, new ListNode(4))));
        ReverseList method = new ReverseList();
        ListNode result = method.reverseList(head);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

}
