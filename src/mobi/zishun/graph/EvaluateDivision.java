package mobi.zishun.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 399. 除法求值
给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
* 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
* 每个 Ai 或 Bi 是一个表示单个变量的字符串。
另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
返回 所有问题的答案 。
* 如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
* 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
* 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
*
示例 1：
输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
Ai, Bi, Cj, Dj 由小写英文字母与数字组成
* https://leetcode-cn.com/problems/evaluate-division/
 */
public class EvaluateDivision {
    // 优化版 - Floyd算法（预处理） +  邻接数组 + hashmap存储字符串与数组下标的映射关系
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = values.length;
        Map<String, Integer> strMap = new HashMap<>();
        // 记录String和int的映射关系
        int counter = 0;
        for (int i = 0; i < n; i++) {
            String first = equations.get(i).get(0);
            String second = equations.get(i).get(1);
            if (!strMap.containsKey(first)) {
                strMap.put(first, counter++);
            }
            if (!strMap.containsKey(second)) {
                strMap.put(second, counter++);
            }
        }
        // 处理graph邻接数组
        double[][] graph = new double[counter][counter];
        // 初始化，给嵌套数组赋初始值-1.0
        for (int i = 0; i < counter; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        for (int i = 0; i < n; i++) {
            int first = strMap.get(equations.get(i).get(0));
            int second = strMap.get(equations.get(i).get(1));
            double value = values[i];
            graph[first][second] = value;
            graph[second][first] = 1.0 / value;
        }
        // Floyd算法（预处理），把可能存在的连接记录完全
        for (int k = 0; k < counter; k++) { // k作为中间值，放在循环最外层
            // 逐个遍历处理以k为中间值可能存在的情况
            for (int i = 0; i < counter; i++) {
                for (int j = 0; j < counter; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }
        // 查询处理好的图，输出答案
        int m = queries.size();
        double[] res = new double[m];
        for (int i = 0; i < m; i++) {
            double curRes = -1.0;
            if (strMap.containsKey(queries.get(i).get(0)) && strMap.containsKey(queries.get(i).get(1))) {
                curRes = graph[strMap.get(queries.get(i).get(0))][strMap.get(queries.get(i).get(1))];
            }
            res[i] = curRes;
        }
        return res;
    }

    // 初版 - Floyd算法（预处理） + 邻接数组 + 手动hash - 时间复杂度高，有哈希冲突的风险
    public double[] calcEquationV2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = values.length;
        double[][] graph = new double[251][251];
        for (int i = 0; i < n; i++) {
            int first = equations.get(i).get(0).hashCode() % 251;
            int second = equations.get(i).get(1).hashCode() % 251;
            double value = values[i];
            graph[first][second] = value;
            graph[second][first] = 1.0 / value;
        }
        for (int k = 0; k < 251; k++) {
            for (int i = 0; i < 251; i++) {
                for (int j = 0; j < 251; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        int m = queries.size();
        double[] res = new double[m];
        for (int i = 0; i < m; i++) {
            double result = -1.0;
            int first = queries.get(i).get(0).hashCode() % 251;
            int second = queries.get(i).get(1).hashCode() % 251;
            if (graph[first][second] > 0) {
                if (graph[first][second] > 0) {
                    result = graph[first][second];
                }
            }
            res[i] = result;
        }
        return res;
    }

    public static void main(String[] args) {
//        String[] strings = {"x1", "x2", "x3", "x4", "x5", "ab", "bc", "cd", "a", "b", "c"};
//        for (String str : strings) {
//            System.out.println(str.hashCode() % 251);
//        }
        EvaluateDivision m = new EvaluateDivision();
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.add(new ArrayList<>());
        equations.get(0).add("a");
        equations.get(0).add("b");
        equations.get(1).add("b");
        equations.get(1).add("c");
        equations.get(2).add("a");
        equations.get(2).add("c");

        double[] values = {2.0, 3.0, 6.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>());
        queries.add(new ArrayList<>());
//        queries.add(new ArrayList<>());
        queries.get(0).add("a");
        queries.get(0).add("c");
        queries.get(1).add("b");
        queries.get(1).add("a");
//        queries.get(2).add("a");
//        queries.get(2).add("e");
        System.out.println(Arrays.toString(m.calcEquation(equations, values, queries)));
        // [1.13333,8.40000,1.50000,1.00000,0.05952]
        // [1.13333,16.8,1.5,1.0,0.05952]
    }

}
