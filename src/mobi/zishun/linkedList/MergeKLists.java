package mobi.zishun.linkedList;

import mobi.zishun.model.ListNode;

/*
23. 合并K个升序链表

给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKLists {

    //递归合并
    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return lists[0];
        }
        ListNode[] newLists = new ListNode[size - 1];
        for (int i = 1; i < size; i++) {
            newLists[i - 1] = lists[i];
        }
        return mergeTwoLists(lists[0], mergeKLists(newLists));

    }

    //遍历合并
    public ListNode mergeKListsV2(ListNode[] lists) {
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = mergeTwoListsV2(res, lists[i]);
        }
        return res;
    }


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    private ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        if (l1 == null) {
            prev.next = l2;
        } else {
            prev.next = l1;
        }
        return preHead.next;
    }
}