package mobi.zishun.model;

import lombok.Data;

@Data
public class DoublyListNode<K, V> {
    private K key;

    /**
     * 数据
     */
    private V value;

    /**
     * 前驱指针
     */
    private DoublyListNode<K, V> prev;

    /**
     * 后继指针
     */
    private DoublyListNode<K, V> next;

    public DoublyListNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
