package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 2. 两数相加
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
* https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        // flag为true代表此次进1，下次计算需要sum+1
        boolean flag = false;
        while (l1 != null || l2 != null) {
            // 当前节点的和
            int sum;
            // 不同情况给sum赋值
            if (l1 == null) {
                sum = l2.val;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val;
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val;
                l2 = l2.next;
                l1 = l1.next;
            }
            // 如果上次计算有进1，此次sum+1
            if (flag) {
                sum++;
            }
            // 输出sum，改变flag值
            if (sum < 10) {
                prev.next = new ListNode(sum);
                prev = prev.next;
                flag = false;
            } else {
                prev.next = new ListNode(sum % 10);
                prev = prev.next;
                flag = true;
            }
        }
        // 如果遍历结束flag为true，说明最后一次计算有进一位，给结果链表加一个值为1的节点（最大位）
        if (flag) {
            prev.next = new ListNode(1);
        }
        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        AddTwoNumbers m = new AddTwoNumbers();
        ListNode res = m.addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

}
