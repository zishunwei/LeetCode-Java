package mobi.zishun.hashmap;

import java.util.HashMap;
import java.util.Map;

/*
 * 1189. “气球” 的最大数量
给你一个字符串text，你需要使用 text 中的字母来拼凑尽可能多的单词"balloon"（气球）。
字符串text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词"balloon"。
链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
 */
public class MaxNumberOfBalloons {
    public int maxNumberOfBalloonsV2(String text) {
        int length = text.length();
        if (length < 7) {
            return 0;
        }
        int[] freq = new int[5];
        for (int i = 0; i < length; i++) {
            char cur = text.charAt(i);
            if (cur == 'l') {
                freq[0]++;
            } else if (cur == 'o') {
                freq[1]++;
            } else if (cur == 'a') {
                freq[2]++;
            } else if (cur == 'b') {
                freq[3]++;
            } else if (cur == 'n') {
                freq[4]++;
            }
        }
        freq[0] = freq[0] / 2;
        freq[1] = freq[1] / 2;
        int ans = freq[0];
        for (int i = 0; i < 5; i++) {
            ans = Math.min(ans, freq[i]);
        }
        return ans;
    }

    public int maxNumberOfBalloons(String text) {
        int length = text.length();
        if (length < 7) {
            return 0;
        }
        Map<Character, Integer> hashMap = new HashMap<>(5);
        hashMap.put('b', 0);
        hashMap.put('a', 0);
        hashMap.put('l', 0);
        hashMap.put('o', 0);
        hashMap.put('n', 0);
        for (int i = 0; i < length; i++) {
            char cur = text.charAt(i);
            if (hashMap.containsKey(cur)) {
                hashMap.replace(cur, hashMap.get(cur) + 1);
            }
        }
        int res = 0;
        while (!(hashMap.containsValue(0) || hashMap.containsValue(-1))) {
            hashMap.replace('b', hashMap.get('b') - 1);
            hashMap.replace('a', hashMap.get('a') - 1);
            hashMap.replace('l', hashMap.get('l') - 2);
            hashMap.replace('o', hashMap.get('o') - 2);
            hashMap.replace('n', hashMap.get('n') - 1);
            res++;
        }
        if (hashMap.containsValue(-1)) {
            res--;
        }
        return res;
    }

    public static void main(String[] args) {
        MaxNumberOfBalloons m = new MaxNumberOfBalloons();
        System.out.println(m.maxNumberOfBalloonsV2("krhizmmgmcrecekgyljqkldocicziihtgpqwbticmvuyznragqoyrukzopfmjhjjxemsxmrsxuqmnkrzhgvtgdgtykhcglurvppvcwhrhrjoislonvvglhdciilduvuiebmffaagxerjeewmtcwmhmtwlxtvlbocczlrppmpjbpnifqtlninyzjtmazxdbzwxthpvrfulvrspycqcghuopjirzoeuqhetnbrcdakilzmklxwudxxhwilasbjjhhfgghogqoofsufysmcqeilaivtmfziumjloewbkjvaahsaaggteppqyuoylgpbdwqubaalfwcqrjeycjbbpifjbpigjdnnswocusuprydgrtxuaojeriigwumlovafxnpibjopjfqzrwemoinmptxddgcszmfprdrichjeqcvikynzigleaajcysusqasqadjemgnyvmzmbcfrttrzonwafrnedglhpudovigwvpimttiketopkvqw"));
    }
}
