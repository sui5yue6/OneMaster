package a1.a42;

import java.util.Arrays;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/25 0:20
 */
public class Code01_PostOfficeProblem {
    // 邮局数量。
    // 不管如何，肯定要先考虑如何抽象到动态规划
    public static int f(int[] arr, int num) {
        // 生成预处理结构
        int length = arr.length;
        int[][] pre = new int[length + 1][length + 1];
//        for (int i = 0; i < length; i++) {
//            // 一个的时候，中心点在0
//            // 0 1    中心点0
//            // 0 1 2  中心点 1   2/2
//            // 0 1 2 3 中心点1   3/2
//            // 0 1 2 3 4 中心点2
//            pre[0][i] = arr[i] - arr[(i - 1) / 2];
//        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                pre[i][j] = pre[i][j - 1] + arr[j] - arr[(j + i) / 2];
            }
        }
        // 在写一个邮局问题
        int[][] dp = new int[length + 1][num + 1];
        for (int i = 0; i < length; i++) {
            dp[i][1] = pre[0][i];
        }

        // 画家问题。
        for (int i = 0; i < length; i++) {
            for (int j = 2; j <= num; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    int left = dp[k][j - 1];
                    int right = pre[k + 1][i];            // 从k+1到i需要的代价
                    ans = Math.min(ans, left + right);
                }
                dp[i][j] = ans;
            }
        }
        return dp[length - 1][num];

//        int ans = 0;
//        return ans;
    }

    public static int f2(int[] arr, int num) {
        // 生成预处理结构
        int length = arr.length;
        int[][] pre = new int[length + 1][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                pre[i][j] = pre[i][j - 1] + arr[j] - arr[(j + i) / 2];
            }
        }
        // 在写一个邮局问题
        int[][] dp = new int[length + 1][num + 1];
        int[][] best = new int[length + 1][num + 1];
        for (int i = 0; i < length; i++) {
            dp[i][1] = pre[0][i];
            best[i][1] = 0; // 最优的点。随和邮局的数量增加，这个点只会往右边移动。这样可以让最大值尽可能小
        }
        for (int j = 0; j <= num; j++) {
            dp[0][j] = 0;
            best[0][j] = 0;
        }

        // 画家问题。
        for (int i = 1; i < length; i++) {
            for (int j = 2; j <= num; j++) {
                int ans = Integer.MAX_VALUE;
                int bestindex = 0;
                int l1 = best[i - 1][j];  // 这个数量越少，邮局肯能越是靠右边
                int l2 = best[i][j - 1];
                int l = Math.max(l1, l2);
                for (int k = l; k <= i; k++) {
                    int left = dp[k][j - 1]; //(因为这里只依赖k，因此i从大到小和从小到大，是没有关系的)
                    int right = pre[k + 1][i];            // 从k+1到i需要的代价
//                    ans = Math.min(ans, left + right);
                    if (left + right < ans) {
                        ans = left + right;
                        bestindex = k;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestindex;
            }
        }
        return dp[length - 1][num];

//        int ans = 0;
//        return ans;
    }

    public static int f3(int[] arr, int num) {
        // 生成预处理结构
        int length = arr.length;
        int[][] pre = new int[length + 1][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                pre[i][j] = pre[i][j - 1] + arr[j] - arr[(j + i) / 2];
            }
        }
        // 在写一个邮局问题
        int[][] dp = new int[length + 1][num + 1];
        int[][] best = new int[length + 1][num + 1];
        for (int i = 0; i < length; i++) {
            dp[i][1] = pre[0][i];
            best[i][1] = -1; // 最优的点。随和邮局的数量增加，这个点只会往右边移动。这样可以让最大值尽可能小
        }
        for (int j = 0; j <= num; j++) {
            dp[0][j] = 0;
            best[0][j] = 0;
        }
        // 画家问题。
        for (int j = 2; j <= num; j++) {
            for (int i = length - 1; i >= 0; i--) {
                int ans = Integer.MAX_VALUE;
                int bestindex = 0;
                int up = i == length - 1 ? length - 1 : best[i + 1][j];  // 居明数减少——最优点左边移动
                int down = best[i][j - 1];  // 邮局数量减少。
                for (int k = down; k <= up; k++) {
                    int left = k == -1 ? 0 : dp[k][j - 1]; //(因为这里只依赖k，因此i从大到小和从小到大，是没有关系的)
                    int right = pre[k + 1][i];            // 从k+1到i需要的代价
                    if (left + right < ans) {
                        ans = left + right;
                        bestindex = k;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestindex;
            }
        }
        return dp[length - 1][num];

//        int ans = 0;
//        return ans;
    }

    public static int f4(int[] arr, int num) {
        // 生成预处理结构
        int length = arr.length;
        int[][] pre = new int[length + 1][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                pre[i][j] = pre[i][j - 1] + arr[j] - arr[(j + i) / 2];
            }
        }
        // 在写一个邮局问题
        int[][] dp = new int[length + 1][num + 1];
        int[][] best = new int[length + 1][num + 1];
        for (int i = 0; i < length; i++) {
            dp[i][1] = pre[0][i];
            best[i][1] = 0; // 最优的点。随和邮局的数量增加，这个点只会往右边移动。这样可以让最大值尽可能小
        }
        for (int j = 0; j <= num; j++) {
            dp[0][j] = 0;
            best[0][j] = 0;
        }
        // 画家问题。
        for (int j = 2; j <= num; j++) {
            for (int i = length - 1; i >= 0; i--) { // 这里的遍历顺序是有讲究的

                int ans = Integer.MAX_VALUE;
                int bestindex = 0;
                int up = i == length - 1 ? length - 1 : best[i + 1][j];  // 居明数减少——最优点左边移动
//                int up = best[i + 1][j];
                int down = best[i][j - 1];  // 邮局数量减少。
                for (int k = down; k <= up; k++) {
                    int left = dp[k][j - 1]; //(因为这里只依赖k，因此i从大到小和从小到大，是没有关系的)
                    int right = pre[k + 1][i];            // 从k+1到i需要的代价
                    if (left + right < ans) {
                        ans = left + right;
                        bestindex = k;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestindex;
            }
        }
        return dp[length - 1][num];

//        int ans = 0;
//        return ans;
    }

    public static int min1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }

    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1;
        }
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= j; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[N - 1][num];
    }

    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
//            int ans2 = min2(arr, num);
//            int ans2 = f(arr, num);
//            int ans2 = f2(arr, num);
//            int ans2 = f3(arr, num);
            int ans2 = f4(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
