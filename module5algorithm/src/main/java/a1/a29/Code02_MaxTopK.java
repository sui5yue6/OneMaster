package a1.a29;

import java.util.Arrays;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/10 14:19
 */
public class Code02_MaxTopK {

    // 时间复杂度O(N*logN)
    // 排序+收集
    public static int[] maxTopK1(int[] arr, int k) {  // 从大到小
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = N - 1, j = 0; j < k; i--, j++) {
            ans[j] = arr[i];
        }
        return ans;
    }

    // 方法二，时间复杂度O(N + K*logN)
    // 解释：堆
    public static int[] maxTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        // 从底向上建堆，时间复杂度O(N)
        for (int i = N - 1; i >= 0; i--) {   // 建堆。
            heapify(arr, i, N);
        }
        // 只把前K个数放在arr末尾，然后收集，O(K*logN)
        int heapSize = N;
        swap(arr, 0, --heapSize);
        int count = 1;
        while (heapSize > 0 && count < k) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
            count++;
        }
        int[] ans = new int[k];
        for (int i = N - 1, j = 0; j < k; i--, j++) {
            ans[j] = arr[i];
        }
        return ans;
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 方法三，时间复杂度O(n + k * logk)
    public static int[] maxTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        k = Math.min(N, k);
        // O(N)
        int num = minKth(arr, N - k);
        int[] ans = new int[k];
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > num) {
                ans[index++] = arr[i];
            }
        }
        for (; index < k; index++) {
            ans[index] = num;
        }
        // O(k*logk)
        Arrays.sort(ans);
        for (int L = 0, R = k - 1; L < R; L++, R--) {
            swap(ans, L, R);
        }
        return ans;
    }

    // 时间复杂度O(N)
    public static int minKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 生成随机数组测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean pass = true;
        System.out.println("测试开始，没有打印出错信息说明测试通过");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = generateRandomArray(maxSize, maxValue);

            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);

            int[] ans1 = f3(arr1, k);
            int[] ans2 = maxTopK2(arr2, k);
            int[] ans3 = maxTopK3(arr3, k);
            if (!isEqual(ans1, ans2) || !isEqual(ans1, ans3)) {
                pass = false;
                System.out.println("出错了！");
                printArray(ans1);
                printArray(ans2);
                printArray(ans3);
                break;
            }
        }
        System.out.println("测试结束了，测试了" + testTime + "组，是否所有测试用例都通过？" + (pass ? "是" : "否"));
    }

    // 从小到大的排序

    public static int[] f1(int[] arr, int k) {
        // 排序，然后去除最后的几个  1 2 3 4 5 6      6 5 4 3
        // 快速排序
        int length = arr.length;
        k = Math.min(length, k);
        int[] res = new int[k];
        Arrays.sort(arr);
        int num = 0;
        for (int i = arr.length - 1; i > arr.length - 1 - k; i--) { // 这样的写法最好理解。
            res[num] = arr[i];
            num++;
        }
        return res;
    }

    // 写一个堆。然后不断的弹出元素。
    public static int[] f2(int[] arr, int k) {
        // 构建堆。小的会下沉，大的会上浮。所以，从下往上放
        int N = arr.length; // 这个也是堆的大小
        for (int i = N - 1; i >= 0; i--) {
            heapifynew(arr, i, N);
        }
        k = Math.min(k, N);
        int[] res = new int[k];
        int heapSize = N;
        for (int i = 0; i < k; i++) {
            // 交换
            heapSize--;
            int tmp = arr[0];
            res[i] = tmp;
            swapnew(arr, 0, heapSize);
            heapifynew(arr, 0, heapSize);
        }
        return res;
    }

    public static void heapifynew(int[] arr, int index, int heapsize) {
        // 当前和下面的进行比较
        while (index * 2 + 1 < heapsize) {
            int largest = index * 2 + 1;
            if (index * 2 + 1 + 1 < heapsize) {
                if (arr[index * 2 + 1 + 1] > arr[index * 2 + 1]) {
                    largest = index * 2 + 1 + 1;
                }
            }
            if (arr[largest] < arr[index]) { // 如果index比较大，就不需要下沉了。
                break;
            }
            // 交换
            swapnew(arr, largest, index);
            index = largest;
        }
    }
//    public static void heapifynew(int[] arr, int index, int heapsize) {
//        // 当前和下面的进行比较
//        int left = index * 2 + 1;
//        while (left < heapsize) {
//            int largest = left;
//            if (left + 1 < heapsize) {
//                if (arr[left + 1] > arr[left]) {
//                    largest = left + 1;
//                }
//            }
//            if (arr[largest] < arr[index]) { // 如果index比较大，就不需要下沉了。
//                break;
//            }
//            // 交换
//            swapnew(arr, largest, index);
//            index = largest;
//            left = index * 2 + 1;
//        }
//    }

    public static void swapnew(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static int[] f3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        k = Math.min(arr.length, k);
        int[] res = new int[k];
        // 需要一个纯粹的minK的函数
        // 1 2 3 4 5 6 7 8 9    k为 3   index 6
        // arr.length- k
        int num = mithk(arr, arr.length - k); // 找到第N-k小。这个数字右边的就是需要的
//        int num = minKth(arr, arr.length - k); // 找到第N-k小。这个数字右边的就是需要的
        int index = 0;
        for (int i = arr.length - k; i < arr.length; i++) { // 由此可见，这里我的逻辑是完全没有问题的。
            res[index] = arr[i];
            index++;
        }
        Arrays.sort(res);
        for (int i = 0; i < k / 2; i++) {
            swapnew(res, i, k - 1 - i);
        }
//        for (int i = 0; i < arr.length; i++) { // 这里的国旗问题，这里没有必要再次进行排序
//            if (arr[i] > num) {
//                res[index++] = arr[i];
//            }
//        }
//        for (; index < k; index++) {
//            res[index] = num;
//        }
//        Arrays.sort(res);
//        for (int L = 0, R = k - 1; L < R; L++, R--) {
//            swap(res, L, R);
//        }
        return res;
    }

    public static int mithk(int[] arr, int index) {

        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = p(arr, L, R, pivot);
//            range = partition(arr, L, R, pivot);
//            if (index >= arr[range[0]] && index <= arr[range[1]]) {
//                return aim;
//            } else
//            if (index < arr[range[0]]) {
//                R = range[0] - 1;
//            } else if (index > arr[range[1]]) {
//                L = range[1] + 1;
//            } else {
//                return pivot;
//            }
            if (index >= range[0] && index <= range[1]) {
                return pivot;
            } else if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) { // 这里只和下标有关
                L = range[1] + 1;
            }
//            else {
//                return pivot;
//            }
        }
        return arr[L];
    }

    public static int[] p(int[] arr, int L, int R, int aim) {
        int less = L;
        int more = R;
        int cur = L;
        while (cur <= more) {   //   2 5 3 6   目标值是4   现在是等于跳过，小于都向右边移动
            if (arr[cur] > aim) {
                // 和more交换
                swapnew(arr, cur, more);
                more--;
            } else if (arr[cur] < aim) {
                // 和less交换
                swapnew(arr, cur, less);
                less++; // 先加了，再交换
                cur++; //交换的是等值的，cur前进
            } else {
                cur++;
            }
        }
        return new int[]{less, more};
    }

}












