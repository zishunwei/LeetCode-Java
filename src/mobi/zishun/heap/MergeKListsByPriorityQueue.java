package mobi.zishun.heap;

import mobi.zishun.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
23. 合并K个升序链表

给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKListsByPriorityQueue {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 1) {
            return null;
        }
        //哨兵节点
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        int k = lists.length;
        // 初始容量<1会抛出IllegalArgumentException异常
        Queue<ListNode> queue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a.val));
//        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(k, (a, b) -> (a.val - b.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode minNode = queue.remove();
            // 将堆顶元素添加进结果
            prev.next = minNode;
            prev = prev.next;

            if (minNode.next != null) {
                queue.add(minNode.next);
            }
        }

        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, new ListNode(3));
        ListNode node2 = new ListNode(2, new ListNode(4, new ListNode(7)));
        ListNode node3 = new ListNode(5, new ListNode(6, new ListNode(8)));
        ListNode[] nodeArr = {node2, node1, node3};
        MergeKListsByPriorityQueue method = new MergeKListsByPriorityQueue();
        ListNode res = method.mergeKLists(nodeArr);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(100);
//        for (int i = 0; i < 100; i++) {
//            priorityQueue.add((int) (Math.random() * 100));
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(priorityQueue.remove());
//        }
    }
}