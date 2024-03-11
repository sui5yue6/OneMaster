package a1.a32;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/11 23:04
 */
public class NumMatrix {
    public static void main(String[] args) {
        int[][] ints = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(ints);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));

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
        while (row <= N && col <= M) {
            tree[row][col] += val;
            col += col & (-col);
            row += row & (-row);
        }
    }

    public void update(int row, int col, int val) {
        row++;
        col++;
        int add = val - num[row][col]; // 需要加上的数值
        add(row + 1, col + 1, add);
    }

    public int sum(int row, int col) {
        int sum = 0;
        while (row > 0 && col > 0) {
            sum += tree[row][col];
            col -= col & (-col);
            row -= row & (-row);

        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row1 + 1, col1 + 1) + sum(row2 + 1, col2 + 1)
                - sum(row1 + 1, col2 + 1) - sum(row2 + 1, col1 + 1);
    }
}
