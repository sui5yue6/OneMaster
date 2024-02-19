package a1.a25;

import java.util.Stack;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/19 1:22
 */
public class Code02_AllTimesMinToMax {

    public int maxSumMinProduct(int[] arr) {
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        long ans = Long.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) {
            // stack中保存的是范围上的最小值。
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) { // 2 3 4 5 然后突然来了一个1进来砸场子   比如，这里没有1进来砸场子，就一直往右边移动，因为这样可以尽可能的保罗数字
                // 因为每一个数字都会被弹出来，因此都会被计算到

                Integer pop = stack.pop();
                // 如果弹出来的是空，说明，就一个数字
                long f1 = Long.MIN_VALUE;
                ;
                if (stack.empty()) {
                    f1 = (long) sum[i - 1] * arr[pop]; // 这里全部算的都是i-1的范围上的。只是，这里的最大值有所改变。在范围和最大值上进行权衡
                } else {
                    f1 = (long) arr[pop] * (sum[i - 1] - sum[stack.peek()]); // 一次结算
                }
                ans = Math.max(ans, f1);
            }
            stack.add(i);
        }
        while (!stack.empty()) {
            Integer pop = stack.pop();
            long f1 = Long.MIN_VALUE;
            ;
            if (stack.empty()) {
                f1 = (long) sum[arr.length - 1] * arr[pop]; // 这里全部算的都是i-1的范围上的。只是，这里的最大值有所改变。在范围和最大值上进行权衡
            } else {
                f1 = (long) arr[pop] * (sum[arr.length - 1] - sum[stack.peek()]); // 一次结算
            }
            ans = Math.max(ans, f1);

        }
        return (int) (ans % 1000000007);
    }

}
