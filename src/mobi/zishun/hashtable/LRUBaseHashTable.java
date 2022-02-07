package mobi.zishun.hashtable;

import java.util.HashMap;

/**
 * @Description:基于散列表的LRU算法
 */
public class LRUBaseHashTable {

    /**
     * 头结点
     */
    private final DNode<Integer, Integer> headNode;

    /**
     * 尾节点
     */
    private final DNode<Integer, Integer> tailNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private final Integer capacity;

    /**
     * 散列表存储key
     */
    private final HashMap<Integer, DNode<Integer, Integer>> table;

    public LRUBaseHashTable(int capacity) {
        // 当成员变量和局部变量重名时,可以用关键字 this 来区分
        this.capacity = capacity;
        length = 0;

        headNode = new DNode<>(-1, -1);
        tailNode = new DNode<>(-2, -2);

        headNode.next = tailNode;
        tailNode.prev = headNode;

        table = new HashMap<>();
    }

    public int get(int key) {
        if (table.containsKey(key)) {
            DNode<Integer, Integer> node = table.get(key);

            // 将节点移动到头部
            //1. 删除节点
            removeNode(node);
            //2. 重新添加节点到链表头部 (画图看很清晰)
            // 先把node的前后指针调整好
            node.next = headNode.next;
            node.prev = headNode;
            // 再 把原第一个指针的前指针和headNode的后指针调整好
            headNode.next.prev = node;
            headNode.next = node;
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DNode<Integer, Integer> newNode = new DNode<>(key, value);
        if (table.containsKey(key)) {
            DNode<Integer, Integer> node = table.get(key);
            // 将节点移动到头部
            //1. 删除节点
            removeNode(node);
            table.remove(key);

            //2. 重新添加节点到链表头部 (画图看很清晰)
            addNodeToHead(newNode);
            table.put(key, newNode);
        } else {
            if (length >= capacity) {
                // 删除尾节点后再加入
                DNode<Integer, Integer> lastNode = tailNode.prev;
                table.remove(lastNode.key);
                removeNode(lastNode);

                addNodeToHead(newNode);
                table.put(key, newNode);
            } else {
                // 直接加入
                addNodeToHead(newNode);

                table.put(key, newNode);
                length++;
            }
        }

    }

    private void removeNode(DNode<Integer, Integer> node) {
        //1. 删除节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(DNode<Integer, Integer> node) {
        //2. 添加节点到链表头部 (画图看很清晰)
        // 先把node的前后指针调整好
        node.next = headNode.next;
        node.prev = headNode;
        // 再把原第一个指针的前指针和headNode的后指针调整好
        headNode.next.prev = node;
        headNode.next = node;
    }

    /**
     * 双向链表
     */
    static class DNode<K, V> {

        /**
         * key
         */
        private K key;

        /**
         * 数据
         */
        private V value;

        /**
         * 前驱指针
         */
        private DNode<K, V> prev;

        /**
         * 后继指针
         */
        private DNode<K, V> next;

        DNode() {
        }

        DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) {
        LRUBaseHashTable lruBaseLinkedList = new LRUBaseHashTable(2);
        lruBaseLinkedList.put(2, 3);
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