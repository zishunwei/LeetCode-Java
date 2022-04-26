package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

/*
 * 24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
* 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
示例 1：
输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：
输入：head = []
输出：[]
示例 3：
输入：head = [1]
输出：[1]
提示：
链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
* https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 存储第3个节点及后面的节点
        ListNode third = head.next.next;
        // 先翻转第1个和第2个节点
        ListNode second = head.next;
        head.next = head.next.next;
        second.next = head;
        head = second;
        // 把翻转好的第1个第2个节点，与递归完成的第三个节点及后面的节点连在一起
        head.next.next = swapPairs(third);
        return head;
    }

}
