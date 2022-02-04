package mobi.zishun.binarysearch;

/*
 * 278. 第一个错误的版本
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。
实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
链接：https://leetcode-cn.com/problems/first-bad-version
 */
public class FirstBadVersion {
    private final int badVersion;

    // 构造函数-随机生成bad version
    public FirstBadVersion(int n) {
        badVersion = (int) (Math.random() * n) + 1;
        System.out.println("bad version: " + badVersion);
    }

    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            int medium = (high - low) / 2 + low;
            if (isBadVersion(medium)) {
                high = medium;
            } else {
                low = medium + 1;
            }
        }
        return high;
    }

    // 初版-代码逻辑过于冗余+无效调用过多
    public int firstBadVersionV2(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            if (low == high) {
                if (isBadVersion(low)) {
                    return low;
                } else {
                    return low + 1;
                }
            }
            int medium = (high - low) / 2 + low;
            if (isBadVersion(medium) && isBadVersion(medium + 1)) {
                high = medium;
            } else if (!isBadVersion(medium) && isBadVersion(medium + 1)) {
                return medium + 1;
            } else if (!isBadVersion(medium) && !isBadVersion(medium + 1)) {
                low = medium + 1;
            }
        }
        return -1;
    }

    // The isBadVersion API is defined in the parent class VersionControl.
    private boolean isBadVersion(int version) {
        return version >= badVersion;
    }

    public static void main(String[] args) {
        FirstBadVersion firstBadVersion = new FirstBadVersion(100);
        System.out.println(firstBadVersion.firstBadVersion(100));
    }
}
