package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
 * 剑指 Offer 06. 从尾到头打印链表
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：
输入：head = [1,3,2]
输出：[2,3,1]
* https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class ReversePrintList {
    public int[] reversePrint(ListNode head) {
        List<Integer> resList = recursion(head);
        int n = resList.size();
        int[] res = new int[n];
        for (int i =0; i<n;i++){
            res[i] = resList.get(i);
        }
        return res;
    }

    public List<Integer> recursion(ListNode head) {
        if (head == null) {
            return new ArrayList<>();
        }
        List<Integer> res;
        res = recursion(head.next);
        res.add(head.val);
        return res;
    }

    public int[] reversePrintV2(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        cur = head;
        int[] res = new int[count];
        while (cur != null) {
            count--;
            res[count] = cur.val;
            cur = cur.next;
        }
        return res;
    }

}
