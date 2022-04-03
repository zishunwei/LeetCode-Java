package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.HashSet;

/*
 * 142. 环形链表 II
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
不允许修改 链表。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
示例 3：
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。
提示：
链表中节点的数目范围在范围 [0, 104] 内
-105 <= Node.val <= 105
pos 的值为 -1 或者链表中的一个有效索引
进阶：你是否可以使用 O(1) 空间解决此题？
* https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LinkedListHasCycleII {
    // 快慢指针 - 空间复杂度O(1)
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 从相遇点到入环点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离。
                ListNode start = head;
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start;
            }
        }
        return null;
    }

    // HashSet - 空间复杂度O(n)
    public ListNode detectCycleV2(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<ListNode>();
        while (head != null) {
            if (!hashSet.add(head)) {
                break;
            }
            head = head.next;
        }
        return head;
    }

}
