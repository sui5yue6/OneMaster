package a1;

import java.util.Arrays;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/9 22:53
 */
public class Code02_AddShortestEnd {

    public static String f(String s) {
        char[] chars = s.toCharArray();
        char[] charsArray = new char[s.length() * 2 + 1];
        int[] index = new int[s.length() * 2 + 1];
        int c = 0;
        for (int i = 0; i < chars.length; i++) {
            charsArray[c] = '#';
            c++;
            charsArray[c] = chars[i];
            c++;
            if (i == chars.length - 1) {
                charsArray[c] = '#';
            }
        }
        int C = -1;
        int R = -1;
        int res = 0;
        for (int i = 0; i < s.length() * 2 + 1; i++) {
            if (i < R) {
                int tmp1 = index[2 * C - i];
                int tmp2 = R - i;
                index[i] = Math.min(tmp1, tmp2);

            } else {
                index[i] = 1;
            }

            while (i + index[i] < charsArray.length && i - index[i] >= 0) {
                if (charsArray[i + index[i]] == charsArray[i - index[i]]) {
                    index[i]++;
                } else {
                    break;
                }
            }
            // 更新C和R
            if (i + index[i] > R) {
                R = i + index[i];
                C = i;
            }
            if (i + index[i] == charsArray.length) {
                res = i - index[i]; //# 5 # 1 # 2 # 3 # 2 # 1 #   7 + 5  7-5 =2
                break;
            }

        }
        //
        String replace = "";
        for (int i = res; i >= 0; i--) {
            if (charsArray[i] != '#') {
                replace += charsArray[i];
            }
        }

        return replace;
    }


    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {   // 补齐后缀
        // 如果一个回文串到达了终点就可以退出了
        String str2 = "abcd123321";
        String str1 = "abcd123321112";
        System.out.println(shortestEnd(str1));
        System.out.println(f(str1));
    }

}
