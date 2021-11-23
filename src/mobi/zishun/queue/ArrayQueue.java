package mobi.zishun.queue;

public class ArrayQueue {
    //数组 items；数组大小：n
    private final String[] items;
    private final int n;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean enqueue(String item) {
        if (tail == n) {
            if (head == 0) {
                return false;
            }
            // 搬移队列
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    //出队
    public String dequeue() {
        if (head == tail) return null;
        String ret = items[head];
        head++;
        return ret;
    }
}
