package a1.a39;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/18 23:11
 */
public class Code03_MSumToN {
    public static void main(String[] args) {
        Code03_MSumToN code03_mSumToN = new Code03_MSumToN();

        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
            System.out.println(num + " : " + code03_mSumToN.f(num));
        }
        System.out.println("test begin");
        for (int num = 1; num < 5000; num++) {
            if (code03_mSumToN.f(num) != isMSum2(num)) {
//            if (isMSum1(num) != isMSum2(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

    }

    public boolean f(int n) {
        // 需要的是连续证书
        for (int i = 1; i <= n / 2; i++) {
            boolean p = p(i, n - i);
            if (p) {
                return p;
            }
        }
        return false;
    }

    public boolean p(int start, int rest) {
        if (start + 1 == rest) {
            return true;
        }
//        if (rest < 0) {
//            return false;
//        }
        if (start > rest) {
            return false;
        }
        return p(start + 1, rest - start - 1);
    }

    public static boolean isMSum1(int num) {
        for (int start = 1; start <= num; start++) {
            int sum = start;
            for (int j = start + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    public static boolean isMSum2(int num) {
        // 如何判断一个数的二进制是否只有一个1
        // 就是将最右侧的1提取出来，是不是和数字本身一样大
        // 如果提取最右边的1    与上取反+1
//
//		return num == (num & (~num + 1));
//
//		return num == (num & (-num));
//
//
        return (num & (num - 1)) != 0;
    }

}
