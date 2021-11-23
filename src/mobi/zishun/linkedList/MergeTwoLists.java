package mobi.zishun.linkedList;

import mobi.zishun.model.ListNode;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //哨兵节点
        ListNode preHead = new ListNode(-1);
        // prehead头节点，prev是指向头节点的指针
        // prev在创建时，没有new只是把prehead赋给了它，所以prev没有开辟新地址，是与prehead指向同一地址的别名对象，
        // prev.next = l1;即prehead.next = l1; prehead对象所在地址的next被赋值了。
        // prev = prev.next;，这句让prev这个对象名指向了新的地址，但是prehead指向的还是最初的地址，
        // 所以最后能用prehead.next还可以返回头结点，不能用prev，因为prev已经移动到l1或者l2的末尾去了。大抵就是对象名和内存地址的关系
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

    public ListNode mergeTwoListsByRecusion(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        } else if (l2 == null){
            return l1;
        } else if (l1.val <= l2.val){
            l1.next = mergeTwoListsByRecusion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsByRecusion(l1,l2.next);
            return l2;
        }
    }

}
