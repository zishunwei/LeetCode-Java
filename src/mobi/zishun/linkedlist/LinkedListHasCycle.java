package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/*
 * 141. 环形链表
题目：链表中环的检测
给定一个链表，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 pos为环的起点位置。
如果链表中存在环，则返回 true 。否则，返回 false 。
进阶：你能用 O(1)（即，常量）内存解决此问题吗？
* https://leetcode.cn/problems/linked-list-cycle/
 */
public class LinkedListHasCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<ListNode>();
        while (head != null) {
            // 有重复值即返回false
            if (!nodeSet.add(head.next)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // 空间复杂度为O(1)
    // 快慢指针
    public boolean hasCycleV2(ListNode head) {
        if (head == null || head.next ==null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
}