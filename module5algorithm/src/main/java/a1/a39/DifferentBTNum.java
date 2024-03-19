package a1.a39;

import java.util.LinkedList;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/20 0:18
 */
public class DifferentBTNum {
    public static long f(int n) {
        // n-1 0
        // n-2 1
        // n-3 2
        if (n == 1 || n == 0) {
            return 1;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            long ans = 0;
            for (int j = 0; j < i; j++) {
                ans += dp[j] * dp[i - j - 1];
            }
            dp[i] = ans;

        }
        return dp[n];
    }

    public static long num1(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int leftSize = 0; leftSize < i; leftSize++) {
                dp[i] += dp[leftSize] * dp[i - 1 - leftSize];
            }
        }
        return dp[N];
    }


    // //	k(n) = c(2n, n) - c(2n, n-1)
    public static long num2(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        for (int i = 1, j = N + 1; i <= N; i++, j++) {
            a *= i;
            b *= j;
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        return (b / a) / (N + 1);
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    //    public static void main(String[] args) {
//        System.out.println("test begin");
//        for (int i = 0; i < 15; i++) {
//            long ans1 = num1(i);
//            long ans1 = num2(i);
//            long ans2 = f(i);
//            if (ans1 != ans2) {
//                System.out.println("Oops!" + i);
//            }
//        }
//        System.out.println("test finish");
//    }
    public static long ways1(int N) {
        int zero = N;
        int one = N;
        LinkedList<Integer> path = new LinkedList<>();
        LinkedList<LinkedList<Integer>> ans = new LinkedList<>();
        process(zero, one, path, ans);
        long count = 0;
        for (LinkedList<Integer> cur : ans) {
            int status = 0;
            for (Integer num : cur) {
                if (num == 0) {
                    status++;
                } else {
                    status--;
                }
                if (status < 0) {
                    break;
                }
            }
            if (status == 0) {
                count++;
            }
        }
        return count;
    }

    public static void process(int zero, int one, LinkedList<Integer> path, LinkedList<LinkedList<Integer>> ans) {
        if (zero == 0 && one == 0) {
            LinkedList<Integer> cur = new LinkedList<>();
            for (Integer num : path) {
                cur.add(num);
            }
            ans.add(cur);
        } else {
            if (zero == 0) {
                path.addLast(1);
                process(zero, one - 1, path, ans);
                path.removeLast();
            } else if (one == 0) {
                path.addLast(0);
                process(zero - 1, one, path, ans);
                path.removeLast();
            } else {
                path.addLast(1);
                process(zero, one - 1, path, ans);
                path.removeLast();
                path.addLast(0);
                process(zero - 1, one, path, ans);
                path.removeLast();
            }
        }
    }

    public static long ways2(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long limit = N << 1;
        for (long i = 1; i <= limit; i++) {
            if (i <= N) {
                a *= i;
            } else {
                b *= i;
            }
        }
        return (b / a) / (N + 1);
    }

    public static long ways3(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long a = 1;
        long b = 1;

        for (long i = N + 1; i <= 2 * N; i++) {

            a *= i;

        }
        return a / (N + 1);
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10; i++) {
            long ans1 = ways1(i);
//            long ans2 = ways2(i);
            long ans2 = ways3(i);
            if (ans1 != ans2) {
                System.out.println("Oops! "+i);
            }
        }
        System.out.println("test finish");
    }


}
