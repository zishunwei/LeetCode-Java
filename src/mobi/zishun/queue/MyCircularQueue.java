package mobi.zishun.queue;

/*
622. 设计循环队列
 */
public class MyCircularQueue {
    // 数组：items，数组大小：n
    private final int[] items;
    private final int capacity;
    // head表示队头下标
    private int head;
    private int count;

    // 申请一个大小为capacity的数组
    public MyCircularQueue(int n) {
        // 这里的capacity加1的原因是，在循环队列中， 我们会浪费一个空间，这样能存元素的数量会少一个，
        // 所以这里我们基于传进来的容量+1，这样就可以储存期望的元素数量
        items = new int[n];
        capacity = n;
        head = 0;
        count = 0;
    }

    // 入队
    public boolean enQueue(int item) {
        // 队列满了
        if (count == capacity) {
            return false;
        }
        items[(head + count) % capacity] = item;
        count++;
        return true;
    }

    // 出队
    public boolean deQueue() {
        if (count == 0) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }

    public int Front() {
        if (count == 0) {
            return -1;
        }
        return items[head];
    }

    public int Rear() {
        if (count == 0) {
            return -1;
        }
        return items[(head + count -1) % capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));

        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.isFull());

        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(4));

        System.out.println(myCircularQueue.Rear());
//        System.out.println(myCircularQueue.Front());

    }

}