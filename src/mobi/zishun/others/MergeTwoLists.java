package mobi.zishun.others;

import mobi.zishun.model.ListNode;

/*
21. 合并两个有序链表
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(-1);

        while (l1!=null && l2!=null) {
            if(l1.val >= l2.val) {
                pre.next = l2;
                l2 = l2.next;
            } else {
                pre.next = l1;
                l1 = l1.next;
            }
        }
        return pre.next;
    }

}
