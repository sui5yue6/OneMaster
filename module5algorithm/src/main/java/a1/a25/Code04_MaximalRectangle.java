package a1.a25;

import java.util.Stack;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/19 22:39
 */
public class Code04_MaximalRectangle {


    // 这个题目每年挡住了很多面试人


    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] cur = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    cur[i][j] += 1;
                    if (i > 0) {
                        cur[i][j] += cur[i - 1][j];
                    }
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 这里需要用到 栈窗口的做法
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < m; j++) {  //1, 2,3,4  2
                while (!stack.empty() && cur[i][stack.peek()] >= cur[i][j]) { // 大于等于的时候就可以弹出来
                    Integer pop = stack.pop();
                    // 计算面积，考虑为空的情况
                    int k = stack.empty() ? -1 : stack.peek();
                    int f = (j - k - 1) * cur[i][pop];
                    ans = Math.max(ans, f);

                }
                stack.add(j);

            }
            while (!stack.empty()) { // 大于等于的时候就可以弹出来
                Integer pop = stack.pop();
                // 计算面积，考虑为空的情况
                int k = stack.empty() ? -1 : stack.peek();
                int f = (m - k - 1) * cur[i][pop];
                ans = Math.max(ans, f);

            }

        }

        return ans;
    }
}























