package a1.a41;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/22 22:47
 */
public class a41 {
    public static void main(String[] args) {
        f(new int[]{1, 2, 3, 4}); // 0 1 3 6 10
    }

    // 如果要找到信心，就徒手撸代码
    public static int[] f(int[] arr) {
        int[] res = new int[arr.length];
        int mid = 0;
//        int sumleft = 0;
//        int sumright = 0;
        int[] totalSum = new int[arr.length + 1];

        for (int i = 1; i <= arr.length; i++) {
            totalSum[i] = arr[i - 1] + totalSum[i - 1];
        }
        res[0] = 0;
        // 一开始mid在0的位置
        for (int i = 1; i < arr.length; i++) {
            int sumleft = totalSum[mid+1];
            int sumright = 0;
        }
        return res;
    }
}

















