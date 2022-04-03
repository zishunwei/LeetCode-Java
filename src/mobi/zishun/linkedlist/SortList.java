package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 148. 排序链表
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]
示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
示例 3：
输入：head = []
输出：[]
提示：
链表中节点的数目在范围 [0, 5 * 10^4] 内
-10^5 <= Node.val <= 10^5
进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
* https://leetcode-cn.com/problems/sort-list/
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        return mergeSort(head, null);
    }

    public ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode list1 = mergeSort(head, slow);
        ListNode list2 = mergeSort(slow, tail);
        return merge(list1, list2);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode preHead = new ListNode();
        ListNode prev = preHead;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                prev.next = list2;
                break;
            } else if (list2 == null) {
                prev.next = list1;
                break;
            } else {
                if (list1.val <= list2.val) {
                    prev.next = list1;
                    list1 = list1.next;
                } else {
                    prev.next = list2;
                    list2 = list2.next;
                }
            }
            prev = prev.next;
        }
        return preHead.next;
    }


}
