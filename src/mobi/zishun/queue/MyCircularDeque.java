package mobi.zishun.queue;

/*
 * 641. 设计循环双端队列
设计实现双端队列。
实现 MyCircularDeque 类:
MyCircularDeque(int k)：构造函数,双端队列最大为 k 。
boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回 false 。
boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false 。
boolean deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true，否则返回 false 。
boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false 。
int getFront())：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
int getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1 。
boolean isEmpty()：若双端队列为空，则返回true，否则返回 false 。
boolean isFull()：若双端队列满了，则返回true，否则返回 false 。
链接：https://leetcode-cn.com/problems/design-circular-deque
 */
public class MyCircularDeque {
    private final int[] nums;
    // 指向头部元素索引
    private int head;
    // 指向尾部下一个插入的位置
    private int tail;
    private int count;
    private final int capacity;

    public MyCircularDeque(int k) {
        nums = new int[k];
        head = 0;
        tail = 0;
        count = 0;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (count == capacity) {
            return false;
        }
        head = (head + capacity - 1) % capacity;
        nums[head] = value;
        count++;
        return true;
    }

    public boolean insertLast(int value) {
        if (count == capacity) {
            return false;
        }
        nums[tail] = value;
        tail = (tail + 1) % capacity;
        count++;
        return true;
    }

    public boolean deleteFront() {
        if (count == 0) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }

    public boolean deleteLast() {
        if (count == 0) {
            return false;
        }
        tail = (tail + capacity - 1) % capacity;
        count--;
        return true;
    }

    public int getFront() {
        if (count == 0) {
            return -1;
        }
        return nums[head];
    }

    public int getRear() {
        if (count == 0) {
            return -1;
        }
        return nums[(tail + capacity - 1) % capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

}
