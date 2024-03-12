package a1.a32;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/11 23:04
 */
public class NumMatrix {
    public static void main(String[] args) {
//        int[][] ints = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
//        NumMatrix numMatrix = new NumMatrix(ints);
//        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
//        numMatrix.update(3, 2, 2);
//        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
//
//        IndexTree indexTree = new IndexTree(ints);
//        System.out.println(indexTree.sumRegion(2, 1, 4, 3));
//
        int[][] ints = {{2, 4}, {-3, 5}};
        NumMatrix numMatrix = new NumMatrix(ints);
        numMatrix.update(0, 1, 3);
        numMatrix.update(1, 1, -3);
        numMatrix.update(0, 1, 1);
        System.out.println(numMatrix.sumRegion(0, 0, 1, 1));

        IndexTree indexTree = new IndexTree(ints);
        indexTree.update(0, 1, 3);
        indexTree.update(1, 1, -3);
        indexTree.update(0, 1, 1);
        System.out.println(indexTree.sumRegion(0, 0, 1, 1));
        System.out.println();

    }

    int[][] tree;
    int[][] num;
    int N;
    int M;

    public NumMatrix(int[][] matrix) {
        N = matrix.length;
        M = matrix[0].length;
        tree = new int[N + 1][M + 1];
        num = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                num[i][j] = matrix[i][j];
                add(i + 1, j + 1, matrix[i][j]);
            }
        }
    }

    public void add(int row, int col, int val) {
        // 这里需要对col上的数值进行更新
        for (int i = row; i <= N; i += i & (-i)) {
            for (int j = col; j <= M; j += j & (-j)) {
                tree[i][j] += val;
            }
        }
//        for (; row <= N; row += row & (-row)) {
//            for (; col <= M; col += col & (-col)) {
//                tree[row][col] += val;
//            }
//        }
//        while (row <= N && col <= M) {
//            tree[row][col] += val;
//            col += col & (-col);
//            row += row & (-row);
//        }
    }

    public void update(int row, int col, int val) {
        int add = val - num[row][col]; // 需要加上的数值
        num[row][col] = val;
        add(row + 1, col + 1, add);
    }

    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
//        for (; row > 0; row -= row & (-row)) {
//            for (; col > 0; col -= col & (-col)) {
//                sum += tree[row][col];
//            }
//        }

        return sum;
    }

    //    public int sumRegion(int row1, int col1, int row2, int col2) {
//        return sum(row1 + 1, col1 + 1) + sum(row2 + 1, col2 + 1)
//                - sum(row1 + 1, col2 + 1) - sum(row2 + 1, col1 + 1);
//    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row1, col1) + sum(row2 + 1, col2 + 1)
                - sum(row1, col2 + 1) - sum(row2 + 1, col1);
    }
}

class IndexTree {
    private int[][] tree;
    private int[][] nums;
    private int N;
    private int M;

    public IndexTree(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        N = matrix.length;
        M = matrix[0].length;
        tree = new int[N + 1][M + 1];
        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    public void update(int row, int col, int val) {
        if (N == 0 || M == 0) {
            return;
        }
        int add = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= N; i += i & (-i)) {
            for (int j = col + 1; j <= M; j += j & (-j)) {
                tree[i][j] += add;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (N == 0 || M == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }

}
