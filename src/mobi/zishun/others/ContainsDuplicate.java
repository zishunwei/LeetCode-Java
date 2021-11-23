package mobi.zishun.others;

import java.util.HashSet;
import java.util.Set;

/*
217. 存在重复元素
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;

    }

}
