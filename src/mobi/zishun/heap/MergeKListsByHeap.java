package mobi.zishun.heap;

import mobi.zishun.model.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
23. 合并K个升序链表

给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKListsByHeap {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 1) {
            return null;
        }
        //哨兵节点
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        int k = lists.length;
        List<ListNode> minHeap = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                minHeap.add(lists[i]);
            }
        }
        buildHeap(minHeap);

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.remove(0);

            prev.next = minNode;
            prev = prev.next;

            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
            buildHeap(minHeap);
        }
        // 解决边界条件有误的情况
//        while (lists.length > 0) {
//            for (int i = 0; i < k; i++) {
//                if (lists[i] == null){
//                    continue;
//                }
//                lists[i] = lists[i].next;
//                // 将堆顶元素添加进结果
//                prev.next = minHeap[0];
//                prev = prev.next;
//
//                minHeap[0] = lists[i];
//                heapify(minHeap, k - 1, 0);
//            }
//        }
//        int count = minHeap.length;
//        while (count-- > 0) {
//            prev.next = removeMin(minHeap, count);
//            prev = prev.next;
//        }
        return preHead.next;
    }

    private void buildHeap(List<ListNode> list) {
        for (int i = (list.size() - 1) / 2; i >= 0; i--) {
            heapify(list, list.size() - 1, i);
        }
    }

    private void heapify(List<ListNode> heap, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 + 1 <= n && heap.get(i).val > heap.get(i * 2 + 1).val) {
                maxPos = i * 2 + 1;
            }
            if (i * 2 + 2 <= n && heap.get(maxPos).val > heap.get(i * 2 + 2).val) {
                maxPos = i * 2 + 2;
            }
            if (maxPos == i) {
                break;
            }
            Collections.swap(heap, i, maxPos);
            i = maxPos;
        }
    }

    public static void main(String[] args) {
//        test();
        ListNode node1 = new ListNode(1, new ListNode(3));
        ListNode node2 = new ListNode(2, new ListNode(4, new ListNode(7)));
        ListNode node3 = new ListNode(5, new ListNode(6, new ListNode(8)));
        ListNode[] nodeArr = {node2, node1, node3};

        MergeKListsByHeap method = new MergeKListsByHeap();
        ListNode res = method.mergeKLists(nodeArr);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    private static void test() {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        ListNode node1 = new ListNode(1, new ListNode(3));
        ListNode node2 = new ListNode(2, new ListNode(4, new ListNode(5)));

        ListNode[] nodeArr = {node1, node2};
        for (int i = 0; i < 2; i++) {
            while (nodeArr[i] != null) {
                prev.next = nodeArr[i];
                nodeArr[i] = nodeArr[i].next;
                prev = prev.next;
            }
        }
        ListNode res = preHead.next;
        System.out.println(res.val);
    }

}