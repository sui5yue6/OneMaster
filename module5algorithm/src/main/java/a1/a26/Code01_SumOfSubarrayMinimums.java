package a1.a26;

import java.util.Stack;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/20 0:32
 */
public class Code01_SumOfSubarrayMinimums {
    public static void main(String[] args) {
        int i = new Code01_SumOfSubarrayMinimums().sumSubarrayMins(new int[]{3, 1, 2, 4});
        System.out.println(i);
    }

    public int sumSubarrayMins(int[] arr) {
        // 这个题目相当的难。也终于知道为啥前面要把左边最小和右边最小写在一起了。
        // 这个题目还是先做出来。单调栈的也太难了
        // 核心还是从遍历每个数组的思维转换到
        int[] arrLeft = new int[arr.length];
        int[] arrRight = new int[arr.length];
        // 记录左边比它小的值所在的位置
        Stack<Integer> stack = new Stack<>();
        // 3 4 5 6 2 3 4  就是这个范围上，2 就是最小的。

        //           1    5  4   3   2 3 4   // 这里的3可以记录到左边比它小的位置在1 。左边比他大的位置是4
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                arrLeft[pop] = i;  // 一直找到可以延申的位置
            }
            stack.add(i);
        }
        while (!stack.empty()) {
            Integer pop = stack.pop();
            arrLeft[pop] =-1;  // 这里应该就没有必要写了
        }
        //  2 3 4 5 6   如果突然来个很小的，就都要弹出来 比如1 .
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack2.empty() && arr[stack2.peek()] > arr[i]) {
                Integer pop = stack2.pop();
                arrRight[pop] = i;  // 一直找到可以延申的位置
            }
            stack2.add(i);
        }
        while (!stack2.empty()) {
            Integer pop = stack2.pop();
            arrRight[pop] = arr.length;
        }
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = arrLeft[i];
            long right = arrRight[i];
            res += (i - left) * (right - i) *(long) arr[i]; // 这里涉及到爆值，统一使用long来处理
            res %= 1000000007;
        }
        return (int) res;
    }

}
