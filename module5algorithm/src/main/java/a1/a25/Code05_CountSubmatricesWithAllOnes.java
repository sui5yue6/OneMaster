package a1.a25;

import java.util.Stack;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/19 22:56
 */
public class Code05_CountSubmatricesWithAllOnes {
    public static void main(String[] args) {
        Code05_CountSubmatricesWithAllOnes code = new Code05_CountSubmatricesWithAllOnes();
        int i = code.numSubmat(new int[][]{{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}});
    }

    public int numSubmat(int[][] mat) {
        // 比如连续场长度为4，有几种排列组合  1，1 1，2，1，3  1，4    4+3+2+1  还需要计算高度差
        int n = mat.length;
        int m = mat[0].length;
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    arr[i][j] += mat[i][j];
                    if (i > 0) {
                        arr[i][j] += arr[i - 1][j];   // 这里的逻辑是对的，但是因为这个卡住了。一个小时
                    }
                }
            }
        }



        int ans = 0;
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < m; j++) {
                while (!stack.empty() && arr[i][stack.peek()] >= arr[i][j]) {
                    Integer pop = stack.pop();
                    if (arr[i][pop] > arr[i][j]) {
                        int k = stack.empty() ? -1 : stack.peek();
                        // 高度差，就是
                        int h1 = stack.empty() ? 0 : arr[i][stack.peek()];   // 左边的高度。
                        int h2 = arr[i][j];   // 右边的高度。
                        // 宽度差
                        int weight = j - k - 1;
                        int height = arr[i][pop] - Math.max(h1, h2);
                        ans += ((1 + weight) * weight) / 2 * height;
                    }
                }
                stack.add(j);
            }
            while (!stack.empty()) {
                Integer pop = stack.pop();
                int k = stack.empty() ? -1 : stack.peek();
                // 高度差，就是
                int h1 = stack.empty() ? 0 : arr[i][stack.peek()];   // 左边的高度。
                // 宽度差
                int weight = m - k - 1;
                int height = arr[i][pop] - h1;
                ans += ((1 + weight) * weight) / 2 * height;
            }
        }
        return ans;
    }


}
