package a1;

/**
 * @author Eirk
 * @Description
 * @Date 2024/2/6 0:07
 */
public class t2 {


    public static int p(int[] w, int[] v, int bag) {
        if (w == null || v == null || bag == 0) {
            return 0;
        }
        int res = f(0, 0, w, v, bag);
        return res;
        // 背包的价值无非是取和不取。还有超重返回，   还是说列举出每个重量的可能性
    }

    public static int f(int index, int cur, int[] w, int[] v, int bag) {
        if (index == w.length) {
            return 0;
        }
        int ans = 0;
        if (cur + w[index] <= bag) {
            ans = Math.max(ans, f(index + 1, cur + w[index], w, v, bag) + v[index]);
        }
        ans = Math.max(ans, f(index + 1, cur, w, v, bag));

        return ans;
    }

    public static int dp1(int[] w, int[] v, int bag) {
        // 0到index  依赖于 1到index  。 依赖于index-1，index
        int length = w.length;
        int[][] dp = new int[length + 1][bag + 1]; //
        dp[length][0] = 0; // 这个好像不需要初始化

        for (int index = length - 1; index >= 0; index--) {
            for (int cur = 0; cur <= bag; cur++) {
                int ans = 0;
                if (cur + w[index] <= bag) {
                    ans = Math.max(ans, dp[index + 1][cur + w[index]] + v[index]);
                }
                ans = Math.max(ans, dp[index + 1][cur]);
                dp[index][cur] = ans;
            }

        }
        return dp[0][0];
    }


    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7, 2};
        int[] values = {5, 6, 3, 19, 12, 4, 2, 9};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
        System.out.println(p(weights, values, bag));
        System.out.println(dp1(weights, values, bag));
    }
}
