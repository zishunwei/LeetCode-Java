package mobi.zishun.recursion;

import mobi.zishun.model.ListNode;

/*
234. 回文链表
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
输入：head = [1,2,2,1]
输出：true
 */
public class IsPalindrome {
    // 递归写法：递归倒序获取节点-与正序节点比较
    ListNode temp;

    public boolean isPalindrome(ListNode head) {
        temp = head;
        return recursiveCheck(head);
    }

    private boolean recursiveCheck(ListNode head) {
        if (head == null) {
            return true;
        }
        // 调用时中断后序逻辑，函数入栈，触发回归时出栈倒序处理后序逻辑
        boolean result = recursiveCheck(head.next) && head.val == temp.val;
        temp = temp.next;
        return result;
    }

}