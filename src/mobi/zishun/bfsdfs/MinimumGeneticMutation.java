package mobi.zishun.bfsdfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/*
 * 433. 最小基因变化
基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
* 如果无法完成此基因变化，返回 -1 。
注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。

示例 2：
输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
输出：2
示例 3：
输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
输出：3
提示：
start.length == 8
end.length == 8
0 <= bank.length <= 10
bank[i].length == 8
start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
* https://leetcode-cn.com/problems/minimum-genetic-mutation/
 */
public class MinimumGeneticMutation {
    // bfs + 哈希表
    // 最小路径则想到bfs，哈希表用于快速检测基因合法性
    public int minMutation(String start, String end, String[] bank) {
        // 初始化bankSet + 初始判断
        Set<String> bankSet = new HashSet<String>(Arrays.asList(bank));
        if (!bankSet.contains(end)) {
            return -1;
        }
        if (Objects.equals(start, end)) {
            return 0;
        }
        // 准备字符数组，visted记录访问过的基因字符串
        char[] chars = {'A', 'C', 'G', 'T'};
        Set<String> visted = new HashSet<String>();
        // 初始化队列用于bfs
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visted.add(start);
        int step = 1; // 步数，也是当前遍历的层数
        // bfs
        while (!queue.isEmpty()) {
            // 层序遍历搜索树
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curGene = queue.poll();
                // 枚举每个可能变化后的基因（改变一位，3 * 8种，不变的会在visted校验上自动过滤）
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        StringBuilder stringBuilder = new StringBuilder(curGene);
                        stringBuilder.setCharAt(j, chars[k]);
                        String newGene = stringBuilder.toString();
                        // 校验newGene是否合法 && 是否访问过
                        if (bankSet.contains(newGene) && !visted.contains(newGene)) {
                            // 当前层数有==end的，返回当前步数
                            if (newGene.equals(end)) {
                                return step;
                            }
                            // 入队并记录到下一层
                            queue.offer(newGene);
                            visted.add(newGene);
                        }
                    }
                }
            }
            // 搜索完一层，则步数+1
            step++;
        }
        return -1;
    }
}
