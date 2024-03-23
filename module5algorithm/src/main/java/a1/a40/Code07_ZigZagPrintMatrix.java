package a1.a40;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/21 22:47
 */
public class Code07_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);
        System.out.println();
        f(matrix);

    }


    public static void f(int[][] matrix) {  // 真的是shit
        int a = matrix.length;
        int b = matrix[0].length;
        // 0,0    0,0   // 先上，后下
        // 1,0    0,1
        // 2,0    0,2
        // 加入 x没有到底，y到底了。
        // 3,0    1,2    就同步增加x
        // 加入 x也到底了
        // 3,1    2,2   无脑增加y就对了
        // 3,2    3,2

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        boolean flag = true;
        while (y1 < b) {
            p(x1, y1, x2, y2, matrix, flag);
            flag = !flag;

            //x1走到底了，就开始走y1
            y1 = x1 == a - 1 ? y1 + 1 : y1;
            // 当x1没有走到底的时候就++
            x1 = x1 == a - 1 ? x1 : x1 + 1;

            // 如果y2走到底了，就可以增加x2
            x2 = y2 == b - 1 ? x2 + 1 : x2;
            y2 = y2 == b - 1 ? y2 : y2 + 1;


        }


        // 右上就是++，左下就是--
    }

    // 3,0  2,1 1,2
    public static void p(int x1, int y1, int x2, int y2, int[][] matrix, boolean flag) {
        if (flag) { // 右上
            while (x1 >= x2) {
                System.out.print(matrix[x1][y1] + " ");
                x1--;
                y1++;
            }
        } else {
            while (x1 >= x2) { // 左下
                System.out.print(matrix[x2][y2] + " ");
                x2++;
                y2--;
            }
        }

    }

}
