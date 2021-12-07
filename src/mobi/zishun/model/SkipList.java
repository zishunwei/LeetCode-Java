package mobi.zishun.model;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 */
public class SkipList {

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private final Node head = new Node();  // 带头链表


    /**
     * 查找
     */
    public Node find(int value) {
        Node p = head;
        //从最高层开始找,外层循环，遍历向下的指针
        for (int i = levelCount - 1; i >= 0; --i) {
            //内层循环，遍历向右的指针,找到每一层最后一个小于value的位置
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        //判断原始链表对于的值是否等于 value，如果找到了，返回这个Node
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 插入的时候就维护了索引，每次经过对应的层级的时候就插入对应的索引，直到到达原链表
     */
    public void insert(int value) {
        //获取索引级别
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        // 记录每一层级中update[i]该在的位置，a < value <= b
        Node p = head;
        //外层循环，遍历向下的指针
        for (int i = level - 1; i >= 0; --i) {
            //内层循环，遍历向右的指针，找到每一层最后一个小于value的位置
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;// update[i]就是i层级下newNode应该插入的位置，即a的位置
        }

        // 开始在每层对应的位置插入newNode ，原本指针指向 a -> b
        for (int i = 0; i < level; ++i) {
            //修改该层链表指向为 newNode.next -> a.next
            newNode.forwards[i] = update[i].forwards[i];
            //修改该层链表指向为 a.next -> newNode
            update[i].forwards[i] = newNode;
        }
        //修改完以后，每层的链表指向变为了 a -> newNode -> b

        // 更新最大层高
        if (levelCount < level) levelCount = level;
    }

    /**
     * 删除
     */
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        //和添加一样，找到每层要删除的索引的对应的位置
        for (int i = levelCount - 1; i >= 0; --i) {
            //内层循环退出的条件是p的下一个节点的值大于等于value，即找到每一层最后一个小于value的位置
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            //把p赋值给update[i]
            update[i] = p;
        }
        //if条件确保最下层原始链表存在要删除的该值
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            //从最上层开始删,一直删到原始链表
            for (int i = levelCount - 1; i >= 0; --i) {
                //如果update[i]的下一个节点等于value，即 b == value ，则删除该节点
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    //直接让a.next指向要删除节点的下一个节点，此时要删除的节点就不在链表中了
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
        //修改该跳表的层高，因为删除了一些索引节点，有可能层高变小
        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }

    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;
        //Math.random()会生成一个0到1之间的Double类型的数,SKIPLIST_P越大，那么晋升的概率越大，Redis里概率为0.25。
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    private static class Node {
        private int data = -1;
        //一个Node Level数组，数组里的值是i层级指向前一个Node的指针，即每个Node节点向前的指针
        //通过修改数组索引i的值，逻辑上替代了层级之间向下或者向上的指针
        private final Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {

            return "{ data: " +
                    data +
                    "; levels: " +
                    maxLevel +
                    " }";
        }
    }

}