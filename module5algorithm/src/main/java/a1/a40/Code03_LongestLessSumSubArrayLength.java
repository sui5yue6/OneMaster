package a1.a40;

import sun.nio.cs.ext.MS874;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/20 22:50
 */
public class Code03_LongestLessSumSubArrayLength {
    public static int f(int[] arr, int k) {
        int length = arr.length;
        int[] minSums = new int[length];
        int[] minSumEnds = new int[length];
        minSums[length - 1] = arr[length - 1];
        minSumEnds[length - 1] = length - 1;
        for (int i = length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) { // 如果上一个数，是有利可图的，就加上
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        // 滑动窗口的代码虽然短，但是临界的判断及其难，非常的考验代码功底
        int sum = 0;
        int ans = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            // 这里的右移动，和i是没有关系的，只有有本事，可以一直移动
            // 这里就是防止越界？？ end < length &&
            while (sum + minSums[maxIndex] <= k) { // 什么时候窗口可以往右边移动。
                sum += minSums[maxIndex];
                maxIndex = minSumEnds[maxIndex] + 1; // 原来表示最右边是7，那么7的最右边就是14，就可以更新成14
                // 因为这里有加1 的操作，所以可以越界
                if (maxIndex == length) {
                    break;
                }
            }
            ans = Math.max(ans, maxIndex - i);
            if (maxIndex == length) {
                return ans;
            }
            if (maxIndex > i) {
                sum -= arr[i];
            } else if (maxIndex == i) { //
                maxIndex = i + 1;
            }
        }

        return ans;
    }

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        // 迟迟扩不进来那一块儿的开头位置
        int end = 0;
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];            // 指向3   3下面又会指向7
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end > i) { // 还有窗口，哪怕窗口没有数字 [i~end) [4,4)
                sum -= arr[i];
            } else { // i == end,  即将 i++, i > end, 此时窗口概念维持不住了，所以end跟着i一起走
                end = i + 1; // 这里end等于i+1没有任何影响，因为待会i也会变成i+1，除非，sum+minSums[end]有移动
            }
        }
        return ans;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
//            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
            int f2 = maxLength(arr, k);
            int f = f(arr, k);
            if (f != f2) {
                System.out.println("Oops! " + f + " " + f2);
            }
        }
        System.out.println("test finish");
    }
    // 如果是子数组，就一定是窗口的问题。 如果是平均值，累加和。
    // 平均值为v。就是累加和小于等于0的
}
