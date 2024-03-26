package a1.a43;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/26 1:55
 */
public class Code03_PavingTile {
    // 不管是啥题目，递归的暴力解法，是要憋出来的。不然无从优化
    public static int f(int N, int M) {
        if (N < 1 || M < 1 || N * M % 2 != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int[] pre = new int[M];
        // 1就是代码都是满的。
        for (int i = 0; i < M; i++) {
            pre[i] = 1;
        }
        return p(pre, 0, N);
    }

    public static int p(int[] pre, int cur, int N) {
        if (cur == N) {  // 这里是决定了是否分支有有效
            // 遍历pre
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] == 0) {
                    return 0;
                }
            }
            return 1;
        }

        // 这里每一行都需要进行深度的递归
        int[] op = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            op[i] = pre[i] ^ 1;
        }
        return pp(op, 0, cur, N);
    }

    // 这里的op状态可以进行位信息的缓存
    public static int pp(int[] op, int start, int cur, int N) {
        if (start == op.length) {
            return p(op, cur + 1, N);
        }
        int ans = 0;
        // cur位置竖摆
        // 加入上一个是 0,那就是可以摆放，这个值就是1说明已经摆放了。
        // 如果上一个不能摆放1，这个值就是0，说明下一个可以摆放。这就说明了。这里的可能性被锁死，自动传递下去了
        ans += pp(op, start + 1, cur, N);
        if (start + 1 < op.length && op[start] == 0 & op[start + 1] == 0) {
            op[start] = 1;
            op[start + 1] = 1;
            ans += pp(op, start + 2, cur, N);
            op[start] = 0;
            op[start + 1] = 0;
        }
        return ans;
    }


    //===

    // 不管是啥题目，递归的暴力解法，是要憋出来的。不然无从优化
    public static int f2(int N, int M) {
        if (N < 1 || M < 1 || (N * M) % 2 != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M);

        int state = (1 << min) - 1;
        // 1 1 1 1 1 1
        return p2(state, 0, max, min);
    }

    public static int p2(int state, int cur, int N, int M) {
        if (cur == N) {  // 这里是决定了是否分支有有效
            // 要求state全部都位1  直接用数值进行判断
            int stateNeed = (1 << M) - 1;
            return stateNeed == state ? 1 : 0;
        }

        // 这里每一行都需要进行深度的递归
        int op = (~state & (1 << M - 1));// 一个是所有位数取反，一个是需要的位数取反
        // op，就是state取反。然后&
        return pp2(op, 0, cur, N, M);
    }

    // 这里的op状态可以进行位信息的缓存
    public static int pp2(int op, int start, int cur, int N, int M) {
        if (start == M) {
            return p2(op, cur + 1, N, M);
        }
        int ans = 0;
        // cur位置竖摆
        // 加入上一个是 0,那就是可以摆放，这个值就是1说明已经摆放了。
        // 如果上一个不能摆放1，这个值就是0，说明下一个可以摆放。这就说明了。这里的可能性被锁死，自动传递下去了
        ans += pp2(op, start + 1, cur, N, M);
        if (
                (op & (1 << start)) == 0 &&
                        start + 1 < M &&
                        (op & (1 << (start + 1))) == 0
        ) {
            int newop = op;
            newop |= (1 << start);
            newop |= (1 << (start + 1));
            ans += pp2(newop, start + 2, cur, N, M);
        }
        return ans;
    }


    /*
     * 2*M铺地的问题非常简单，这个是解决N*M铺地的问题
     */

    public static int ways1(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int[] pre = new int[M]; // pre代表-1行的状况
        for (int i = 0; i < pre.length; i++) {
            pre[i] = 1;
        }
        return process(pre, 0, N);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    public static int process(int[] pre, int level, int N) {
        if (level == N) { // base case
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] == 0) {
                    return 0;
                }
            }
            return 1;
        }

        // 没到终止行，可以选择在当前的level行摆瓷砖
        int[] op = getOp(pre);
        return dfs(op, 0, level, N);
    }

    // op[i] == 0 可以考虑摆砖
    // op[i] == 1 只能竖着向上
    public static int dfs(int[] op, int col, int level, int N) {
        // 在列上自由发挥，玩深度优先遍历，当col来到终止列，i行的决定做完了
        // 轮到i+1行，做决定
        if (col == op.length) {
            return process(op, level + 1, N);
        }
        int ans = 0;
        // col位置不横摆
        ans += dfs(op, col + 1, level, N); // col位置上不摆横转
        // col位置横摆, 向右
        if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
            op[col] = 1;
            op[col + 1] = 1;
            ans += dfs(op, col + 2, level, N);
            op[col] = 0;
            op[col + 1] = 0;
        }
        return ans;
    }

    public static int[] getOp(int[] pre) {
        int[] cur = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            cur[i] = pre[i] ^ 1;
        }
        return cur;
    }

    // Min (N,M) 不超过 32
    public static int ways2(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    // 上一行的状态，是pre，limit是用来对齐的，固定参数不用管
    // 当前来到i行，一共N行，返回填满的方法数
    public static int process2(int pre, int i, int N, int M) {
        if (i == N) { // base case
            return pre == ((1 << M) - 1) ? 1 : 0;
        }
        int op = ((~pre) & ((1 << M) - 1));
        return dfs2(op, M - 1, i, N, M);
    }

    public static int dfs2(int op, int col, int level, int N, int M) {
        if (col == -1) {
            return process2(op, level + 1, N, M);
        }
        int ans = 0;
        ans += dfs2(op, col - 1, level, N, M);
        if ((op & (1 << col)) == 0 && col - 1 >= 0 && (op & (1 << (col - 1))) == 0) {
            ans += dfs2((op | (3 << (col - 1))), col - 2, level, N, M);
        }
        return ans;
    }

    // 记忆化搜索的解
    // Min(N,M) 不超过 32
    public static int ways3(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        int[][] dp = new int[1 << min][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, min, dp);
    }

    public static int process3(int pre, int i, int N, int M, int[][] dp) {
        if (dp[pre][i] != -1) {
            return dp[pre][i];
        }
        int ans = 0;
        if (i == N) {
            ans = pre == ((1 << M) - 1) ? 1 : 0;
        } else {
            int op = ((~pre) & ((1 << M) - 1));
            ans = dfs3(op, M - 1, i, N, M, dp);
        }
        dp[pre][i] = ans;
        return ans;
    }

    public static int dfs3(int op, int col, int level, int N, int M, int[][] dp) {
        if (col == -1) {
            return process3(op, level + 1, N, M, dp);
        }
        int ans = 0;
        ans += dfs3(op, col - 1, level, N, M, dp);
        if (col > 0 && (op & (3 << (col - 1))) == 0) {
            ans += dfs3((op | (3 << (col - 1))), col - 2, level, N, M, dp);
        }
        return ans;
    }

    // 严格位置依赖的动态规划解
    public static int ways4(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int big = N > M ? N : M;
        int small = big == N ? M : N;
        int sn = 1 << small;
        int limit = sn - 1;
        int[] dp = new int[sn];
        dp[limit] = 1;
        int[] cur = new int[sn];
        for (int level = 0; level < big; level++) {
            for (int status = 0; status < sn; status++) {
                if (dp[status] != 0) {
                    int op = (~status) & limit;
                    dfs4(dp[status], op, 0, small - 1, cur);
                }
            }
            for (int i = 0; i < sn; i++) {
                dp[i] = 0;
            }
            int[] tmp = dp;
            dp = cur;
            cur = tmp;
        }
        return dp[limit];
    }

    public static void dfs4(int way, int op, int index, int end, int[] cur) {
        if (index == end) {
            cur[op] += way;
        } else {
            dfs4(way, op, index + 1, end, cur);
            if (((3 << index) & op) == 0) { // 11 << index 可以放砖
                dfs4(way, op | (3 << index), index + 1, end, cur);
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int M = 6;
        System.out.println(f2(N, M));
        System.out.println(ways1(N, M));
//        System.out.println(ways2(N, M));
//        System.out.println(ways3(N, M));
//        System.out.println(ways4(N, M));
//
//        N = 10;
//        M = 10;
//        System.out.println("=========");
//        System.out.println(ways3(N, M));
//        System.out.println(ways4(N, M));
    }
}
