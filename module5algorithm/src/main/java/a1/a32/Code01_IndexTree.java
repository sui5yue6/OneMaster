package a1.a32;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/11 16:47
 */
public class Code01_IndexTree {

    public static class IndexTree2 {
        private int[] tree;
        private int N;

        public IndexTree2(int num) {
            N = num;
            tree = new int[N + 1];
        }

        // 计算累加和
        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }
        // 增加
        public void add(int index, int n) {
            while (index <= N) {
                tree[index] += n;
                index += (index & -index);
            }
        }

    }

    // 下标从1开始！
    public static class IndexTree {

        private int[] tree;
        private int N;

        // 0位置弃而不用！
        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        // 1~index 累加和是多少？
        public int sum(int index) {  // 就是把1剥掉。然后拿出来。然后再把1剥掉 拿出来。
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        // index & -index : 提取出index最右侧的1出来
        // index :           0011001000
        // index & -index :  0000001000
        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree2 tree = new IndexTree2(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }
}
