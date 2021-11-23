package mobi.zishun.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // 逆序打印单向链表
    private static void printListNode(ListNode head) {
        if (head == null){
            return;
        }
        printListNode(head.next);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        printListNode(node);
    }

}
