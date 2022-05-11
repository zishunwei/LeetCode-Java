package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
* 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

示例 1：
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
示例 2：
输入：head = [5], left = 1, right = 1
输出：[5]
提示：
链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n
进阶： 你可以使用一趟扫描完成反转吗？
* https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class ReverseListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode preNode = new ListNode(-1, head);
        ListNode prev = preNode;
        ListNode cur = head;
        int index = 1; // 记录遍历的位置
        while (index < right) {
            if (index >= left) { // 从cur指向第left个元素开始
                ListNode next = cur.next;
                // 翻转 cur 和 next
                cur.next = next.next;
                // prev一直指向left前的元素
                next.next = prev.next;
                prev.next = next;
            } else { // 没到开始翻转的时候，正常遍历链表
                prev = prev.next;
                cur = cur.next;
            }
            index++;
        }
        return preNode.next;
    }

}
