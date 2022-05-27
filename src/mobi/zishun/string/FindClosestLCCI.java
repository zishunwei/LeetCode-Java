package mobi.zishun.string;

/*
 * 面试题 17.11. 单词距离
有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
* 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
示例：
输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
输出：1
提示：
words.length <= 100000
* https://leetcode.cn/problems/find-closest-lcci/
 */
class FindClosestLCCI {
    public int findClosest(String[] words, String word1, String word2) {
        int n = words.length;
        int ans = n;
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                index1 = i;
            } else if (word.equals(word2)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }
        return ans;
    }

}