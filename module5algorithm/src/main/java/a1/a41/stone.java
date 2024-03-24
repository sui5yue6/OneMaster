package a1.a41;

public class stone {

    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] s = new int[N + 1];
        s[0] = 0;
        for (int i = 0; i < N; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int w(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        return process1(0, N - 1, s);
    }

    public static int process1(int L, int R, int[] s) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process1(L, leftEnd, s) + process1(leftEnd + 1, R, s));
        }
        return next + w(s, L, R);
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N];
        // dp[i][i] = 0
        for (int L = N - 2; L >= 0; L--) {
            for (int R = L + 1; R < N; R++) {
                int next = Integer.MAX_VALUE;
                // dp(L..leftEnd)  + dp[leftEnd+1...R]  + 累加和[L...R]
                for (int leftEnd = L; leftEnd < R; leftEnd++) {
                    next = Math.min(next, dp[L][leftEnd] + dp[leftEnd + 1][R]);
                }
                dp[L][R] = next + w(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            best[i][i + 1] = i;
            dp[i][i + 1] = w(s, i, i + 1);
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                best[L][R] = choose;
                dp[L][R] = next + w(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
//            int ans1 = f(arr);
//            int ans1 = f2(arr);
            int ans1 = f3(arr);
//            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops! " + ans1 + "  " + ans2 + "  " + ans3);
            }
        }
        System.out.println("测试结束");
    }

    public static void main2(String[] args) {
        int f = f(new int[]{1, 2, 3, 4, 5});
    }

    public static int f(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 0;
        }
        int res = 0;
        int[] sumList = new int[arr.length + 1];
        for (int i = 1; i < sumList.length; i++) {
            sumList[i] = arr[i - 1] + sumList[i - 1];
        }
        res = p(0, arr.length - 1, sumList, arr);
        return res;
    }

    public static int p(int start, int end, int[] sumList, int[] arr) {
        // 切分的只有一个
        if (start == end) {
            return 0;
        }
        if (start > end) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int split = start; split < end; split++) {
            int left = p(start, split, sumList, arr);
            int right = p(split + 1, end, sumList, arr);
            // 两个之和最小
            ans = Math.min(ans, left + right);
        }
        ans += (sumList[end + 1] - sumList[start]);
        return ans;
    }

    public static int f2(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][arr.length + 1];

        int[] sumList = new int[arr.length + 1];
        for (int i = 1; i < sumList.length; i++) {
            sumList[i] = arr[i - 1] + sumList[i - 1];
        }
        // 0-10  依赖0到5 和5到10
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                int ans = Integer.MAX_VALUE;
                for (int split = i; split < j; split++) {
                    int left = dp[i][split];
                    int right = dp[split + 1][j];
                    ans = Math.min(ans, left + right);
                }
                dp[i][j] = ans + sumList[j + 1] - sumList[i];
            }
        }
        return dp[0][arr.length - 1];
    }

    public static int f3(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][arr.length + 1];
        int[][] best = new int[arr.length + 1][arr.length + 1];

        int[] sumList = new int[arr.length + 1];
        for (int i = 1; i < sumList.length; i++) {
            sumList[i] = arr[i - 1] + sumList[i - 1];
        }
        // 两个数之间，中间的切分是最优的。
        for (int i = 0; i < arr.length - 1; i++) {
            dp[i][i + 1] = sumList[i + 2] - sumList[i];
            best[i][i + 1] = i;
        }

        // 0-10  依赖0到5 和5到10
        for (int i = arr.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < arr.length; j++) { // 因为相邻一个的已经求过了，因此这里需要求的是相邻两个的。
                int ans = Integer.MAX_VALUE;
                int bestnum = 0;
                for (int split = best[i][j - 1]; split <= best[i + 1][j]; split++) {
                    int left = dp[i][split];
                    int right = dp[split + 1][j];
                    if (left + right <= ans) {
//                        ans = Math.min(ans, left + right);
                        ans = left + right;
                        bestnum = split;
                    }
                }
                dp[i][j] = ans + sumList[j + 1] - sumList[i];
                best[i][j] = bestnum;
            }
        }
        return dp[0][arr.length - 1];
    }
}
