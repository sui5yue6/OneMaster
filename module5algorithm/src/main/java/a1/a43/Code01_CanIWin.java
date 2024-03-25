package a1.a43;

import java.util.HashMap;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/25 20:36
 */
public class Code01_CanIWin {
    public static void main(String[] args) {
        System.out.println(canIWin2(10, 11));
    }

    public static boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        int[] arr = new int[maxChoosableInteger + 1];
        // 比如30位。随意选择。2的30次方。这个数量级还是很大的。
        for (int i = 0; i < maxChoosableInteger; i++) {
            arr[i] = i + 1;
        }
        HashMap<Integer, Boolean> map = new HashMap<>();
        return p(arr, desiredTotal, maxChoosableInteger, map);
    }

    private static boolean p(int[] arr, int desiredTotal, int maxChoosableInteger, HashMap<Integer, Boolean> map) {
        if (desiredTotal <= 0) {
            return false;
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            int tmp = arr[i];
            if (tmp != -1) {
                arr[i] = -1;
                // 通过现在的arr组装int
                int key = get(arr);
                boolean res;
                if (map.containsKey(key)) {
                    res = map.get(key);
                } else {
                    res = p(arr, desiredTotal - tmp, maxChoosableInteger, map);
                }
                map.put(key, res);
                arr[i] = tmp;
                if (!res) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int get(int[] arr) {
        int tmp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                tmp += 1 << i;
            }
        }
        return tmp;
    }

    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        int[] dp = new int[1 << (maxChoosableInteger + 1)];
        int status = 0;
        return p2(status, desiredTotal, maxChoosableInteger, dp);
    }

    private static boolean p2(int status, int desiredTotal, int maxChoosableInteger, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        if (desiredTotal <= 0) {
            dp[status] = -1;
            return false;
        }
        boolean ans = false;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((1 << i) & status) == 0) {
                if (!p2((status | (1 << i)), desiredTotal - i, maxChoosableInteger, dp)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
