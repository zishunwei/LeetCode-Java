package mobi.zishun.graph;

/*
 * 990. 等式方程的可满足性
给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
* 并采用两种不同的形式之一："a==b" 或 "a!=b"。
* 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。

示例 1：
输入：["a==b","b!=a"]
输出：false
解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
示例 2：
输入：["b==a","a==b"]
输出：true
解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
示例 3：
输入：["a==b","b==c","a==c"]
输出：true
示例 4：
输入：["a==b","b!=c","c==a"]
输出：false
示例 5：
输入：["c==c","b==d","x!=z"]
输出：true
提示：
1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] 和 equations[i][3] 是小写字母
equations[i][1] 要么是 '='，要么是 '!'
equations[i][2] 是 '='
* https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 */
public class EquationsPossible {

    // 并查集
    // 具体实现方面，使用一个数组 parent 存储每个变量的连通分量信息，
    // 其中的每个元素表示当前变量所在的连通分量的父节点信息，
    // 如果父节点是自身，说明该变量为所在的连通分量的根节点。
    // 一开始所有变量的父节点都是它们自身。
    // 对于合并操作，我们将第一个变量的根节点的父节点指向第二个变量的根节点；
    // 对于查找操作，我们沿着当前变量的父节点一路向上查找，直到找到根节点。
    public boolean equationsPossible(String[] equations) {
        // parent 存储每个变量的连通分量信息
        int[] parent = new int[26];
        // 初始化parent每个索引指向本身，
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        // 先记录==的情况
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                // 将index2的根节点 与 index1的根节点建立联系，因为整条链路上的变量都相等
                parent[find(parent, index1)] = find(parent, index2);
            }
        }
        // 遍历!=的情况，寻找有没有冲突
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                // 判断index2的根节点 与 index1的根节点是否相等，如果相等，说明和当前的不等式产生冲突
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 找到根节点（parent[index] == index即为初始状态，说明是根节点）
    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }


    // 初版 - 图 + Floyd算法预处理 - 代码复杂，时间复杂度高
    public boolean equationsPossibleV2(String[] equations) {
        // graph值意义： 0：无关系， 1：==， 2：!=
        int[][] graph = new int[26][26];
        // 初始赋值，出现冲突时直接返回false
        for (String equation : equations) {
            int first = equation.charAt(0) - 'a';
            int second = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '=') {
                if (graph[first][second] == 0) {
                    graph[first][second] = 1;
                } else if (graph[first][second] == 2) {
                    return false;
                }
                if (graph[second][first] == 0) {
                    graph[second][first] = 1;
                } else if (graph[second][first] == 2) {
                    return false;
                }
            } else {
                if (first == second) {
                    return false;
                }
                if (graph[first][second] == 0) {
                    graph[first][second] = 2;
                } else if (graph[first][second] == 1) {
                    return false;
                }
                if (graph[second][first] == 0) {
                    graph[second][first] = 2;
                } else if (graph[second][first] == 1) {
                    return false;
                }
            }
        }
        // Floyd算法，处理图，更新传递的关系
        for (int k = 0; k < 26; k++) { // 中间值
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        // a==b && b==c 说明a==c
                        if (graph[i][k] == 1 && graph[k][j] == 1) {
                            if (graph[i][j] == 2) {
                                return false; // 出现冲突时直接返回false

                            } else {
                                graph[i][j] = 1; //更新a和c关系
                            }
                            // a==b && b!=c 或者 a!=b && b==c 说明a!=c
                        } else if ((graph[i][k] == 1 && graph[k][j] == 2) || (graph[i][k] == 2 && graph[k][j] == 1)) {
                            if (graph[i][j] == 1) {
                                return false; // 出现冲突时直接返回false

                            } else {
                                graph[i][j] = 2; // 更新a和c关系
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
