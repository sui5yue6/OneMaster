package a1.a42;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/25 9:54
 */
public class Code02_ThrowChessPiecesProblem {

    public int f(int n, int k) {

        int res = p(1, n, k);
        return res;
    }

    public int p(int start, int end, int k) {
        if (k == 1) {
            return end - start + 1;
        }
        if (start >= end) {
            return 0;
        }
        // 碎了是一种，没有碎是一种
        int ans = 0;
        for (int i = start; i < end; i++) {
            //  碎了的情况                    没有碎的情况
            int tmp = Math.max(p(start, i - 1, k - 1), p(i, end, k)); // 碎了就是0，没有碎就是要求剩下的
            ans = Math.max(ans, tmp);
        }
        return ans;
    }


    // 不需要关心从第几层到第几层，只要关心，剩下的楼层数，和鸡蛋数就可以了
    public static int f2(int n, int k) {
        return p2(n, k);
    }

    public static int p2(int n, int k) { // 10
        if (n == 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) { // 7楼碎了  1-6 7楼 8到10
            int tmp = Math.max(p2(i - 1, k - 1), p2(n - i, k));

            ans = Math.min(ans, tmp);
        }

        return ans + 1;
    }

    public static void main(String[] args) {
//        System.out.println(f2(2, 1));
        System.out.println(f2(14, 3));
        System.out.println(superEggDrop(8, 10000));
        System.out.println(superEggDrop3(8, 10000));
    }

    public static int superEggDrop(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int egg = 2; egg <= k; egg++) {
                int ans = Integer.MAX_VALUE;
                for (int i = 1; i <= rest; i++) {
                    int tmp = Math.max(dp[i - 1][egg - 1], dp[rest - i][egg]);
                    ans = Math.min(ans, tmp);
                }
                dp[rest][egg] = ans + 1;
            }
        }
        return dp[n][k];
    }

    public static int superEggDrop2(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int[][] best = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            best[i][1] = 1;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int egg = 2; egg <= k; egg++) {
                int ans = Integer.MAX_VALUE;
                int left = best[rest - 1][egg] == 0 ? 1 : best[rest - 1][egg]; // egg 80个 现在是100个。80个的时候x楼开始，100个的时候需要x+1楼
                int right = best[rest][egg - 1];
                // 记录最好的降落点
//                for (int i = 1; i <= rest; i++) {
                for (int i = Math.max(left, right); i <= rest; i++) {
                    int tmp = Math.max(dp[i - 1][egg - 1], dp[rest - i][egg]); // 上面的，和左边的，都需要
                    if (tmp < ans) {
                        ans = tmp;
                        best[rest][egg] = i;
                    }
                }
                dp[rest][egg] = ans + 1;
            }
        }
        return dp[n][k];
    }


    public static int superEggDrop3(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int[][] best = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            best[i][1] = 1;
        }
        for (int i = 1; i <= k; i++) {
            dp[1][i] = 1;
            best[1][i] = 1;
        }
        for (int rest = 2; rest <= n; rest++) {
            for (int egg = k; egg >= 2; egg--) {
                int ans = Integer.MAX_VALUE;
                int left = best[rest - 1][egg]; // egg 80个 现在是100个。80个的时候x楼开始，100个的时候需要x+1楼
                int right = egg == k ? rest : best[rest][egg + 1];  // 如果是鸡蛋最多的位置，那么
                // 记录最好的降落点
//                for (int i = 1; i <= rest; i++) {
                int bestchoose = -1;
                for (int i = left; i <= right; i++) {   // 这里的楼层是死的。我只会拿上一个楼层，因此我的鸡蛋从大到小和从小到大是不影响的
                    int tmp = Math.max(dp[i - 1][egg - 1], dp[rest - i][egg]); // 上面的，和左边的，都需要
                    if (tmp <= ans) {
                        ans = tmp;
                        bestchoose = i;
                    }
                }
                dp[rest][egg] = ans + 1;
                best[rest][egg] = bestchoose;
            }
        }
        return dp[n][k];
    }

    public static int superEggDrop4(int k, int n) {
        if(n==1){
            return 1;
        }
        int[] dp = new int[k + 1];
        int step = 1;
        // 只有一步的时候
        // 这么多个鸡蛋。这么多步骤，一定可以解决的问题。如果是一步。那么再多的鸡蛋也只能解决一层楼
        for (int i = 1; i <= k; i++) {
            dp[i] = 1;
        }
        while (true) {
            step = step + 1;
            // 只有一颗棋子的时候，dp[i] 能解决的，就是楼层的高度。
            int pre = 0;
            for (int i = 1; i <= k; i++) {  // 3，5   3，6  4，6  （如果是0个棋子的话）
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= n) {
                    return step;
                }
            }
        }
    }
}
