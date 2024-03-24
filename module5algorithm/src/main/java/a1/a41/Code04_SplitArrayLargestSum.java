package a1.a41;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/24 16:15
 */
public class Code04_SplitArrayLargestSum {
    public static void main(String[] args) {
//        int f = f(new int[]{2, 3, 1, 2, 4, 3}, 5);
//        int f2 = f2(new int[]{2, 3, 1, 2, 4, 3}, 5);
//        int f5 = f3(new int[]{2, 3, 1, 2, 4, 3}, 5);
//        System.out.println(f + "   " + f2 + "  " + f5);
        //10,2,3
        int f3 = f(new int[]{10, 2, 3}, 2);
        int f4 = f2(new int[]{10, 2, 3}, 2);
        int f6 = f3(new int[]{10, 2, 3}, 2);
        System.out.println(f3 + "   " + f4 + "  " + f6);
    }

    public static int f(int[] arr, int k) {
        int[][] dp = new int[arr.length + 1][k + 1];
        int[] totalsum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            totalsum[i] = totalsum[i - 1] + arr[i - 1];
        }
        int[][] best = new int[arr.length + 1][k + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][1] = totalsum[i + 1];
            best[i][1] = arr.length - 1;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 2; j <= k; j++) {
                int ans = Integer.MAX_VALUE;
                for (int leftend = 0; leftend <= i; leftend++) {
                    int leftcost = dp[leftend][j - 1];
                    int rightcost = totalsum[i + 1] - totalsum[leftend + 1];
                    int cur = Math.max(leftcost, rightcost);
                    if (ans > cur) {
                        ans = cur;
                        best[i][j] = leftend;
                    }
                }
                dp[i][j] = ans;
            }
        }

        return dp[arr.length - 1][k];
    }

    public static int f2(int[] arr, int k) {
        int[][] dp = new int[arr.length + 1][k + 1];
        int[][] best = new int[arr.length + 1][k + 1];


        int[] totalsum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            totalsum[i] = totalsum[i - 1] + arr[i - 1];
        }
        // 这里的best为什么可以有-1？
        for (int i = 0; i < arr.length; i++) {
            dp[i][1] = totalsum[i + 1];
            best[i][1] = 0;
        }
        // 当i是0的时候，不管是几个人。都是arr[0]
        for (int i = 1; i <= k; i++) {
            dp[0][i] = arr[0];
            best[0][i] = 0;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 2; j <= k; j++) {
                int ans = Integer.MAX_VALUE;
                int start1 = best[i - 1][j]; // leftend 肯定肯定在它的
                int start2 = best[i][j - 1]; // 这里比左边的小没有毛病。
                int start = Math.max(start1, start2);
                int end = i;

//                if (j == 1) {
//                    index2 = 0;
//                }
                for (int leftend = start; leftend <= end; leftend++) {
                    int leftcost = dp[leftend][j - 1];
                    int rightcost = totalsum[i + 1] - totalsum[leftend + 1];
                    int cur = Math.max(leftcost, rightcost);
                    if (ans > cur) {
                        ans = cur;
                        best[i][j] = leftend;
                    }
                }
                dp[i][j] = ans;
            }
        }

        return dp[arr.length - 1][k];
    }

    public static int f3(int[] arr, int k) {
        // 用二分法找至少需要几个画家
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long l = 0;
        long r = sum;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            long cur = p3(arr, mid, k);
            if (cur > k) { // 比需要的画家数量多，肯定是不行的
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return (int) ans;
    }

    public static long p3(int[] arr, long mid, int k) {
        int part = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] > mid) {
                if (arr[i] > mid) {
                    return Integer.MAX_VALUE;
                }
                part++;
                sum = arr[i];
            } else {
                sum += arr[i];
            }
            if (part >= k) {
                return k + 1;
            }
        }
        part++;
        return part;
    }
}
