package mobi.zishun.linkedlist;

import mobi.zishun.model.ListNode;

public class LRUBaseLinkedList {

    /**
     * 头结点(哨兵节点)
     */
    private final ListNode head;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private final Integer capacity;

    public LRUBaseLinkedList(int capacity) {
        this.head = new ListNode(-1, -1, null);
        this.length = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.key == key) {
                // 将查询元素重新插入表头
                int value = prev.next.val;
                ListNode newNode = new ListNode(key, value, null);
                newNode.next = head.next;
                head.next = newNode;
                // 删除原节点
                if (prev.next.next == null) {
                    prev.next = null;
                } else {
                    prev.next = prev.next.next;
                }
                return value;
            }
            // return提前终止了循环、不用考虑下次循环空指针
            prev = prev.next;
        }
        return -1;
    }

    public void put(int key, int value) {
        //插入到表头
        ListNode newNode = new ListNode(key, value, null);
        newNode.next = head.next;
        head.next = newNode;
        length++;

        ListNode prev = head.next;

        while (prev.next != null) {
            // 已存在此元素，删除原节点
            if (prev.next.key == key) {
                prev.next = prev.next.next;
                length--;
            }
            // 超过容量->删除尾节点
            if (length > capacity && prev.next.next == null) {
                prev.next = null;
                length--;
                break;
            }
            // 避免下个判断循环条件时空指针
            if (prev.next == null) {
                break;
            }
            prev = prev.next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList lruBaseLinkedList = new LRUBaseLinkedList(2);
        lruBaseLinkedList.put(2, 1);
        lruBaseLinkedList.put(2, 2);
        System.out.println(lruBaseLinkedList.get(2));

        lruBaseLinkedList.put(1, 1);
        lruBaseLinkedList.put(4, 1);

        System.out.println(lruBaseLinkedList.get(2));

        lruBaseLinkedList.put(4, 4);

        System.out.println(lruBaseLinkedList.get(1));
        System.out.println(lruBaseLinkedList.get(3));
        System.out.println(lruBaseLinkedList.get(4));
    }
}
