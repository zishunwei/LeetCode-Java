package mobi.zishun.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/*
 * 692. 前K个高频单词
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        // 小顶堆
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(k, (entry1, entry2) ->
                // compareTo-不同的单词有相同出现频率，按字母顺序排序
                Objects.equals(entry1.getValue(), entry2.getValue()) ? entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue());

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            queue.add(entry);
            if (queue.size() > k) {
                queue.remove();
            }
        }

        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            result.addFirst(queue.remove().getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        TopKFrequentWords method = new TopKFrequentWords();
        System.out.println(method.topKFrequent(words, 3));
    }

}
