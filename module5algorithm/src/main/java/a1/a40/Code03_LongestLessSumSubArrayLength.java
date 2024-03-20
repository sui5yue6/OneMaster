package a1.a40;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/20 22:50
 */
public class Code03_LongestLessSumSubArrayLength {
    public int f(int[] arr, int k) {
        int length = arr.length;
        int[] maxnum = new int[length - 1];
        int[] maxnumIndex = new int[length - 1];
        maxnum[length - 1] = arr[length - 1];
        maxnumIndex[length - 1] = length - 1;
        for (int i = length - 2; i >= 0; i--) {

        }
        int ans = 0;
        return ans;
    }
}
