package a1.a25;

import java.util.Stack;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/19 2:13
 */                                               //   0  1  2  3     4   4-1-1
public class Code03_LargestRectangleInHistogram { //   6 8 10  10   12
    // 2,1,2
    // 上面一道题是找数组中的最大值，这里是找数组中的最小值
    public int largestRectangleArea(int[] arr) {
        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) { // 这里是否需要等于，等待商榷
                Integer pop = stack.pop(); // 5,6,2 比如这里5 弹出来了
                // 前面没有数值，就是-1
                int k = stack.empty() ? -1 : stack.peek();
                int f1 = (i - k - 1) * arr[pop]; // 如果这个弹出之后，就没有数据了，说明，从一开始都是要计算进去的。否则，就是找到上一个相同的数字
                ans = Math.max(ans, f1);

            }
            stack.add(i);

        }
        while (!stack.empty()) { // 这里是否需要等于，等待商榷
            Integer pop = stack.pop(); // 5,6,2 比如这里5 弹出来了
            int k = stack.empty() ? -1 : stack.peek();
            int f1 = (arr.length - k - 1) * arr[pop]; // 如果这个弹出之后，就没有数据了，说明，从一开始都是要计算进去的。否则，就是找到上一个相同的数字
            ans = Math.max(ans, f1);

        }
        return ans;
    }
}
