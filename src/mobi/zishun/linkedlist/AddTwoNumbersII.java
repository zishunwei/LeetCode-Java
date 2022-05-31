package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 445. 两数相加 II
给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
* 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
你可以假设除了数字 0 之外，这两个数字都不会以零开头。
* 示例1：
输入：l1 = [7,2,4,3], l2 = [5,6,4]
输出：[7,8,0,7]
* 示例2：
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[8,0,7]
* 示例3：
输入：l1 = [0], l2 = [0]
输出：[0]
* 提示：
链表的长度范围为 [1, 100]
0 <= node.val <= 9
输入数据保证链表代表的数字无前导 0
* 进阶：如果输入链表不能翻转该如何解决？
* https://leetcode.cn/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.addFirst(l1.val);
                l1 = l1.next;
            } else {
                stack1.addLast(0);
            }
            if (l2 != null) {
                stack2.addFirst(l2.val);
                l2 = l2.next;
            } else {
                stack2.addLast(0);
            }
        }

        Deque<Integer> resStack = new LinkedList<>();
        int flag = 0;
        while (!stack1.isEmpty()) {
            int sum = 0;
            sum += stack1.removeFirst();
            sum += stack2.removeFirst();
            sum += flag;
            resStack.addFirst(sum % 10);
            flag = sum / 10;
        }

        ListNode preNode = new ListNode(-1);
        ListNode prev = preNode;
        if (flag == 1) {
            prev.next = new ListNode(1);
            prev = prev.next;
        }
        while (!resStack.isEmpty()) {
            prev.next = new ListNode(resStack.removeFirst());
            prev = prev.next;
        }
        return preNode.next;
    }

    public static void main(String[] args) {
        AddTwoNumbersII m = new AddTwoNumbersII();
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = m.addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

}
