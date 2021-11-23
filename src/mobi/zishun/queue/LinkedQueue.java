package mobi.zishun.queue;

import mobi.zishun.model.ListNode;

public class LinkedQueue {
    // 队列的队首和队尾
    private ListNode head = null;
    private ListNode tail = null;

    public void enqueue(int item) {
        if (tail == null) {
            ListNode newNode = new ListNode(item, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new ListNode(item, null);
            tail = tail.next;
        }
    }

    public Integer dequeue() {
        if (head == null) {
            return null;
        }
        int ret = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return ret;
    }
}